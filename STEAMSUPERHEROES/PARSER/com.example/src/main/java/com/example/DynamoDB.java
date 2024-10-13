package com.example;

import org.slf4j.LoggerFactory;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;

import java.util.*;


public class DynamoDB {
    DynamoDbClient dynamoDbClient;

    public DynamoDB() {
        this.dynamoDbClient = DynamoDbClient.builder()
                .httpClientBuilder(ApacheHttpClient.builder())
                .region(Region.US_EAST_1)
                .build();
    }

    ;

    public void addMentorToTable(Mentor mentor) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("ID", AttributeValue.builder().s(mentor.getId()).build());
        item.put("Email", AttributeValue.builder().s(mentor.getEmail()).build());
        item.put("Name", AttributeValue.builder().s(mentor.getName()).build());
        item.put("Age", AttributeValue.builder().s(mentor.getAge()).build());
        item.put("Phone", AttributeValue.builder().s(mentor.getPhone()).build());
        item.put("City", AttributeValue.builder().s(mentor.getCity()).build());
        item.put("State", AttributeValue.builder().s(mentor.getState()).build());
        item.put("Session-Preferences", AttributeValue.builder().s(mentor.getSessionPreference()).build());
        item.put("Ethnicity", AttributeValue.builder().s(mentor.getEthnicity()).build());
        item.put("Ethnicity-Preferences", AttributeValue.builder().s(mentor.getEthnicityPreference()).build());
        item.put("Gender", AttributeValue.builder().s(mentor.getGender()).build());
        item.put("Gender-Preferences", AttributeValue.builder().s(mentor.getGenderPreference()).build());
        item.put("Mentor-Method", AttributeValue.builder().s(mentor.getMentorMethod()).build());
        item.put("Role", AttributeValue.builder().s(mentor.getRole()).build());
        item.put("Steam-Background", AttributeValue.builder().s(mentor.getSteamBackground()).build());
        item.put("Academic-Level", AttributeValue.builder().s(mentor.getAcademicLevel()).build());
        item.put("Profession", AttributeValue.builder().s(mentor.getProfession()).build());
        item.put("Current-Employer", AttributeValue.builder().s(mentor.getCurrentEmployer()).build());
        item.put("Mentor-Reasons", AttributeValue.builder().s(mentor.getMentorReasons()).build());
        item.put("MenteesToAdvise", AttributeValue.builder().s(mentor.getMenteesToAdvice()).build());
        List<AttributeValue> attributeValueList = new ArrayList<>();
        for (String availability : mentor.getCalendarAvailability()) {
            attributeValueList.add(AttributeValue.builder().s(availability).build());
        }
        item.put("Calendar-Availability", AttributeValue.builder().l(attributeValueList).build());

        List<AttributeValue> attributeValueList2 = new ArrayList<>();
        for (String dates : mentor.getSpecifiedDates()) {
            attributeValueList2.add(AttributeValue.builder().s(dates).build());
        }
        item.put("Specified-Dates", AttributeValue.builder().l(attributeValueList2).build());

        item.put("Matched", AttributeValue.builder().bool(mentor.getIsMatched()).build());


        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName("Mentor")  // Replace with your DynamoDB table name
                .item(item)            // Pass the item map with all mentor's attributes
                .build();

        try {
            dynamoDbClient.putItem(putItemRequest);
        } catch (DynamoDbException e) {
            System.err.println("Failed to insert mentor: " + e.getMessage());
        }
    }

    public void addMenteeToTable(Mentee mentee) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("ID", AttributeValue.builder().s(mentee.getId()).build());
        item.put("Email", AttributeValue.builder().s(mentee.getEmail()).build());
        item.put("Name", AttributeValue.builder().s(mentee.getName()).build());
        item.put("Age", AttributeValue.builder().s(mentee.getAge()).build());
        item.put("Phone", AttributeValue.builder().s(mentee.getPhone()).build());
        item.put("City", AttributeValue.builder().s(mentee.getCity()).build());
        item.put("State", AttributeValue.builder().s(mentee.getState()).build());
        item.put("Session-Preferences", AttributeValue.builder().s(mentee.getSessionTypePreference()).build());
        item.put("Ethnicity", AttributeValue.builder().s(mentee.getEthnicity()).build());
        item.put("Ethnicity-Preferences", AttributeValue.builder().s(mentee.getEthnicityPreference()).build());
        item.put("Gender", AttributeValue.builder().s(mentee.getGender()).build());
        item.put("Gender-Preferences", AttributeValue.builder().s(mentee.getGenderPreference()).build());
        item.put("Mentor-Method", AttributeValue.builder().s(mentee.getMentorMethod()).build());
        item.put("Role", AttributeValue.builder().s(mentee.getRole()).build());
        item.put("Steam-Background", AttributeValue.builder().s(mentee.getBackground()).build());
        item.put("Academic-Level", AttributeValue.builder().s(mentee.getAcademicLevel()).build());
        item.put("Grade", AttributeValue.builder().s(mentee.getGrade()).build());
        item.put("Mentee-Reasons", AttributeValue.builder().s(mentee.getReasons()).build());
        List<AttributeValue> attributeValueList = new ArrayList<>();
        for (String availability : mentee.getCalendarAvailability()) {
            attributeValueList.add(AttributeValue.builder().s(availability).build());
        }
        item.put("Calendar-Availability", AttributeValue.builder().l(attributeValueList).build());

        List<AttributeValue> attributeValueList2 = new ArrayList<>();
        for (String dates : mentee.getSpecifiedDate()) {
            attributeValueList2.add(AttributeValue.builder().s(dates).build());
        }
        item.put("Specified-Dates", AttributeValue.builder().l(attributeValueList2).build());

//
//
        item.put("Matched", AttributeValue.builder().bool(mentee.getIsMatched()).build());

        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName("Mentee")  // Replace with your DynamoDB table name
                .item(item)            // Pass the item map with all mentor's attributes
                .build();

        try {
            dynamoDbClient.putItem(putItemRequest);
        } catch (DynamoDbException e) {
            System.err.println("Failed to insert mentor: " + e.getMessage());
        }
    }


    public List<Mentee> getValidMentees(String tableName) {
        List<Mentee> resultArray = new ArrayList<>();
        try {
            String filterExpression = "Matched = :matchedVal";
            Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
            expressionAttributeValues.put(":matchedVal", AttributeValue.builder().bool(false).build());

            ScanRequest scanRequest = ScanRequest.builder()
                    .tableName(tableName)
                    .filterExpression(filterExpression)
                    .expressionAttributeValues(expressionAttributeValues)
                    .build();

            ScanResponse scanResponse = dynamoDbClient.scan(scanRequest);


            for (Map<String, AttributeValue> item : scanResponse.items()) {
                Mentee mentee = new Mentee();

                mentee.setId(item.get("ID").s());
                mentee.setName(item.get("Name").s());
                mentee.setEmail(item.get("Email").s());
                mentee.setAge(item.get("Age").s());
                mentee.setPhone(item.get("Phone").s());
                mentee.setCity(item.get("City").s());
                mentee.setState(item.get("State").s());
                mentee.setAcademicLevel(item.get("Academic-Level").s());
                mentee.setGender(item.get("Gender").s());
                mentee.setGenderPreference(item.get("Gender-Preferences").s());
                mentee.setEthnicity(item.get("Ethnicity").s());
                mentee.setEthnicityPreference(item.get("Ethnicity-Preferences").s());
                mentee.setGrade(item.get("Grade").s());
                mentee.setIsMatched(item.get("Matched").bool());
                mentee.setReasons(item.get("Mentee-Reasons").s());
                mentee.setMentorMethod(item.get("Mentor-Method").s());
                mentee.setRole(item.get("Role").s());
                mentee.setSessionTypePreference(item.get("Session-Preferences").s());
                mentee.setBackground(item.get("Steam-Background").s());

                List<AttributeValue> calendarAvailabilityList = item.get("Calendar-Availability").l();
                for (AttributeValue availabilityItem : calendarAvailabilityList) {
                    mentee.setCalendarAvailability(availabilityItem.s());  // Get the string value from each AttributeValue
                }

                List<AttributeValue> specificDates = item.get("Specified-Dates").l();
                List<String> specificDateList = new ArrayList<>();
                for (AttributeValue specificDateItem : specificDates) {
                    specificDateList.add(specificDateItem.s());
                }
                mentee.setSpecifiedDate(specificDateList);

                resultArray.add(mentee);
            }
        } catch (DynamoDbException e) {
            System.err.println("Failed to query DynamoDB: " + e.getMessage());
        }
        return resultArray;

    }


    public List<Mentor> getValidMentor(String tableName) {
        List<Mentor> resultArray = new ArrayList<>();
        try {
            String filterExpression = "Matched = :matchedVal";
            Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
            expressionAttributeValues.put(":matchedVal", AttributeValue.builder().bool(false).build());

            ScanRequest scanRequest = ScanRequest.builder()
                    .tableName(tableName)
                    .filterExpression(filterExpression)
                    .expressionAttributeValues(expressionAttributeValues)
                    .build();

            ScanResponse scanResponse = dynamoDbClient.scan(scanRequest);


            for (Map<String, AttributeValue> item : scanResponse.items()) {
                Mentor mentor = new Mentor();

                mentor.setId(item.get("ID").s());
                mentor.setName(item.get("Name").s());
                mentor.setEmail(item.get("Email").s());
                mentor.setPhone(item.get("Phone").s());
                mentor.setAge(item.get("Age").s());
                mentor.setCity(item.get("City").s());
                mentor.setState(item.get("State").s());
                mentor.setAcademicLevel(item.get("Academic-Level").s());
                mentor.setGender(item.get("Gender").s());
                mentor.setGenderPreference(item.get("Gender-Preferences").s());
                mentor.setEthnicity(item.get("Ethnicity").s());
                mentor.setEthnicityPreference(item.get("Ethnicity-Preferences").s());
                mentor.setCurrentEmployer(item.get("Current-Employer").s());
                mentor.setIsMatched(item.get("Matched").bool());
                mentor.setMenteesToAdvice(item.get("MenteesToAdvise").s());
                mentor.setMentorReasons(item.get("Mentor-Reasons").s());
                mentor.setMentorMethod(item.get("Mentor-Method").s());
                mentor.setRole(item.get("Role").s());
                mentor.setProfession(item.get("Profession").s());
                mentor.setSessionPreference(item.get("Session-Preferences").s());
                mentor.setSteamBackground(item.get("Steam-Background").s());

                List<AttributeValue> calendarAvailabilityList = item.get("Calendar-Availability").l();
                for (AttributeValue availabilityItem : calendarAvailabilityList) {
                    mentor.setCalendarAvailability(availabilityItem.s());  // Get the string value from each AttributeValue
                }

                List<AttributeValue> specificDates = item.get("Specified-Dates").l();
                List<String> specificDateList = new ArrayList<>();
                for (AttributeValue specificDateItem : specificDates) {
                    specificDateList.add(specificDateItem.s());
                }
                mentor.setSpecifiedDates(specificDateList);

                resultArray.add(mentor);
            }
        } catch (DynamoDbException e) {
            System.err.println("Failed to query DynamoDB: " + e.getMessage());
        }
        return resultArray;

    }

}
