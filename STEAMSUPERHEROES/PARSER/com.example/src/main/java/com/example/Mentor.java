package com.example;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;



public class Mentor {
    //page 1
    private String id;
    private String timeStamp;
    public String name;
    public String age;
    private String email;
    private String phone;
    private String city;
    private String state;
    public String sessionPreference;
    public String ethnicity;
    public String ethnicityPreference;
    public String gender;
    public String genderPreference;
    private String mentorMethod;
    private String role;

    //page 2
    private String steamBackground;
    private String academicLevel;
    private String profession;
    private String currentEmployer;
    private String mentorReasons;
    private String menteesToAdvice;

    //page 3
    private List<String> calendarAvailability = new ArrayList<>();
    private List<String> specifiedDates;

    public String pairName;
    public boolean paired;

    public boolean getIsMatched() {
        return paired;
    }

    public void setIsMatched(boolean isMatched) {
        this.paired = isMatched;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
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

    public String getSessionPreference() {
        return sessionPreference;
    }

    public void setSessionPreference(String sessionPreference) {
        this.sessionPreference = sessionPreference;
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

    public String getSteamBackground() {
        return steamBackground;
    }

    public void setSteamBackground(String steamBackground) {
        this.steamBackground = steamBackground;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCurrentEmployer() {
        return currentEmployer;
    }

    public void setCurrentEmployer(String currentEmployer) {
        this.currentEmployer = currentEmployer;
    }

    public String getMentorReasons() {
        return mentorReasons;
    }

    public void setMentorReasons(String mentorReasons) {
        this.mentorReasons = mentorReasons;
    }

    public String getMenteesToAdvice() {
        return menteesToAdvice;
    }

    public void setMenteesToAdvice(String menteesToAdvice) {
        this.menteesToAdvice = menteesToAdvice;
    }

    public List<String> getCalendarAvailability() {
        return calendarAvailability;
    }

    public void setCalendarAvailability(String calendarAvailability) {
        this.calendarAvailability.add(calendarAvailability);
    }

    public List<String> getSpecifiedDates() {
        return specifiedDates;
    }

    public void setSpecifiedDates(List<String> specifiedDate) {
        this.specifiedDates = specifiedDate;
    }

    public Mentor(boolean generateUUID) {
        if (generateUUID) {
            this.id = UUID.randomUUID().toString();
        }
    }
    public Mentor() {}
}


