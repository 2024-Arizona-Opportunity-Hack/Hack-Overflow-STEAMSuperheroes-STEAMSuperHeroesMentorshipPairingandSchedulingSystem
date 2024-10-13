package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
//        parser.parseExcel();
        Mentor mentor = new Mentor();
        Mentee mentee = new Mentee();
//        mentee.setName("Stella Bella");
//        mentee.setGrade("9");
//        mentee.setGender("Cisgender Female");
//        mentee.setGenderPreference("Prefer ONLY to be matched within that similarity");
//        mentee.setEthnicity("American Indian or Alaska Native");
//        mentee.setEthnicityPreference("Do not have a preference.  Either is fine.");
//
//        mentor.setName("Sammy Dog");
//        mentor.setAcademicLevel("High School Senior");
//        mentor.setGender("Cisgender Female");
//        mentor.setEthnicity("American Indian or Alaska Native");
//        mentor.setEthnicityPreference("Do not have a preference.  Either is fine ");
//        mentor.setGenderPreference("Do not have a preference.  Either is fine");

        List<Mentee> a = new ArrayList<>();
        a.add(mentee);
        List<Mentor> b = new ArrayList<>();
        b.add(mentor);


//        DynamoDB clientDB = new DynamoDB();
//        List<Mentee> validMenteeList = clientDB.getValidMentees("Mentee");
//        List<Mentor> validMentorList = clientDB.getValidMentor("Mentor");
        List<Pair> pairList = testMMMatching.matchMM(b, a);
        for (Pair p: pairList) {
            p.printPair();
        }

    }
}
