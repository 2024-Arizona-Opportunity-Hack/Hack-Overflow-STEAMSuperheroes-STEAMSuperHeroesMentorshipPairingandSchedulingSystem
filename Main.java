package com.STEAMSUPERHEROES;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/com/SUPERHEROES/sample_mentee_mentor_mixed_data.csv"; // Path to your CSV file
        ArrayList<Mentor> mentors = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            // Read the file line by line
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Skip the header
                    continue;
                }

                // Split the line by commas
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                // Create a Mentor object from the CSV data
                Mentor mentor = new Mentor(
                    data[0],  // Name
                    data[1],  // Age
                    data[2],  // Email
                    data[3],  // Phone
                    data[4],  // City
                    data[5],  // State
                    data[6],  // Session Preference
                    data[7],  // Ethnicity
                    data[8],  // Ethnicity Preference
                    data[9],  // Gender
                    data[10], // Gender Preference
                    data[11], // Mentor Method
                    data[12], // Role
                    data[13], // STEAM Background
                    data[14], // Academic Level
                    data[15], // Profession
                    data[16], // Current Employer
                    data[17], // Mentor Reasons
                    data[18], // Mentees to Advice
                    data[19], // Calendar Availability
                    data[20]  // Specified Date
                );

                // Add the mentor object to the list
                mentors.add(mentor);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print all the mentor objects to verify
        for (Mentor mentor : mentors) {
            System.out.println(mentor.toString());
        }
    }
}
