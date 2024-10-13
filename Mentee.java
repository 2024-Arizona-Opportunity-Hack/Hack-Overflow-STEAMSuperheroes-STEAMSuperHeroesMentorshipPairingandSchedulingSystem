package com.STEAMSUPERHEROES;

public class Mentee {
    //page 1
    private String name;
    private String age;
    private String email;
    private String phone;
    private String city;
    private String state;
    private String sessionPreference;
    private String ethnicity;
    private String ethnicityPreference;
    private String gender;
    private String genderPreference;
    private String mentorMethod;
    private String role;
    
    //page 2
    private String grade;
    private String reasons;
    private String interests;

    //page 3
    private String calendarAvailability;
    private String specifiedDate;

    // Constructor with all parameters
    public Mentee(String name, String age, String email, String phone, String city, String state, 
                  String sessionPreference, String ethnicity, String ethnicityPreference, 
                  String gender, String genderPreference, String mentorMethod, String role,
                  String grade, String reasons, String interests, String calendarAvailability, 
                  String specifiedDate) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.state = state;
        this.sessionPreference = sessionPreference;
        this.ethnicity = ethnicity;
        this.ethnicityPreference = ethnicityPreference;
        this.gender = gender;
        this.genderPreference = genderPreference;
        this.mentorMethod = mentorMethod;
        this.role = role;
        this.grade = grade;
        this.reasons = reasons;
        this.interests = interests;
        this.calendarAvailability = calendarAvailability;
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
               "Session Preference: \"" + sessionPreference + "\",\n" +
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
