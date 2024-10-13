package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Mentee {
    //page 1
    public String id;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionTypePreference() {
        return sessionPreference;
    }

    public void setSessionTypePreference(String sessionTypePreference) {
        this.sessionPreference = sessionTypePreference;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String timeStamp;
    public String email;
    public String name;
    public String age;
    public String phone;
    public String city;
    public String state;
    public String sessionPreference;

    public String getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }

    private String academicLevel;
    public String ethnicity;
    public String ethnicityPreference;
    public String gender;
    public String genderPreference;
    private String mentorMethod;
    private String role;

    //page 2
    private String background;
    private String grade;
    private String reasons;
    private String interests;
    //
    //page 3
    private List<String> calendarAvailability = new ArrayList<>();
    private List<String> specifiedDate;

    public Boolean getIsMatched() {
        return paired;
    }

    public void setIsMatched(Boolean isMatched) {
        this.paired = isMatched;
    }

    public String pairName;

    public boolean paired;

    // Constructor with all parameters

    public Mentee(boolean genUUID) {
        if (genUUID) {
            this.id = UUID.randomUUID().toString();
        }
    }

    public Mentee()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getEthnicityPreference() {
        return ethnicityPreference;
    }

    public void setEthnicityPreference(String ethnicityPreference) {
        this.ethnicityPreference = ethnicityPreference;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGenderPreference() {
        return genderPreference;
    }

    public void setGenderPreference(String genderPreference) {
        this.genderPreference = genderPreference;
    }

    public String getMentorMethod() {
        return mentorMethod;
    }

    public void setMentorMethod(String mentorMethod) {
        this.mentorMethod = mentorMethod;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public List<String> getCalendarAvailability() {
        return calendarAvailability;
    }

    public void setCalendarAvailability(String calendarAvailability) {
        this.calendarAvailability.add(calendarAvailability);
    }

    public List<String> getSpecifiedDate() {
        return specifiedDate;
    }

    public void setSpecifiedDate(List<String> specifiedDate) {
        this.specifiedDate = specifiedDate;
    }

    // Updated toString method
    @Override
    public String toString() {
        return "{\n" +
                "Name: \"" + name + "\",\n" +
                "Age: \"" + age + "\",\n" +
                "Email: \"" + email + "\",\n" +
                "Phone: \"" + phone + "\",\n" +
                "City: \"" + city + "\",\n" +
                "State: \"" + state + "\",\n" +
//                "Session Preference: \"" + sessionPreference + "\",\n" +
                "Ethnicity: \"" + ethnicity + "\",\n" +
                "Ethnicity Preference: \"" + ethnicityPreference + "\",\n" +
                "Gender: \"" + gender + "\",\n" +
                "Gender Preference: \"" + genderPreference + "\",\n" +
                "Mentor Method: \"" + mentorMethod + "\",\n" +
                "Role: \"" + role + "\",\n" +
                "Grade: \"" + grade + "\",\n" +
                "Reasons: \"" + reasons + "\",\n" +
                "Interests: \"" + interests + "\",\n" +
                "Calendar Availability: \"" + calendarAvailability + "\",\n" +
                "Specified Date: \"" + specifiedDate + "\"\n" +
                "}";
    }
}
