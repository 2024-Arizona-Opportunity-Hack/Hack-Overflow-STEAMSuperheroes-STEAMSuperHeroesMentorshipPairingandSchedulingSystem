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

        item.put("Matched", AttributeValue.builder().s(mentor.getIsMatched()).build());


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

        item.put("Matched", AttributeValue.builder().s(mentee.getIsMatched()).build());

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


    public List<String> getValidMentees(String tableName) {
        List<String> resultArray = new ArrayList<>();
        try {
            String filterExpression = "Matched = :matchedVal";
            Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
            expressionAttributeValues.put(":matchedVal", AttributeValue.builder().s("False").build());

            ScanRequest scanRequest = ScanRequest.builder()
                    .tableName(tableName)
                    .filterExpression(filterExpression)
                    .expressionAttributeValues(expressionAttributeValues)
                    .build();

            ScanResponse scanResponse = dynamoDbClient.scan(scanRequest);



            for (Map<String, AttributeValue> item : scanResponse.items()) {
                for (Map.Entry<String, AttributeValue> entry : item.entrySet()) {
                    AttributeValue attributeValue = entry.getValue();

                    if (attributeValue.s() != null) {
                        resultArray.add(attributeValue.s());
                    }
                    else if (attributeValue.l() != null) {
                        for (AttributeValue listItem : attributeValue.l()) {
                            resultArray.add(listItem.s());
                        }
                    }
                }
            }

        } catch (DynamoDbException e) {
            System.err.println("Failed to query DynamoDB: " + e.getMessage());
        }
        return resultArray;

    }
}
