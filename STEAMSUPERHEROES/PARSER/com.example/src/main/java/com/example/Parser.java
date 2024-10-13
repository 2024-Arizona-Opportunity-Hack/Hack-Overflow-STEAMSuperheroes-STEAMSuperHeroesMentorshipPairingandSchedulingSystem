package com.example;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Parser {

    private Workbook wb;
    private DataFormatter formatter;
    private Sheet sheet;
    private DynamoDB dynamoDB;

    public Parser() throws IOException {
        this.wb = WorkbookFactory.create(new File("./STEAM_Superheroes_Mentoring_Program_(Responses).xlsx"));
        this.formatter = new DataFormatter();
        this.sheet = wb.getSheetAt(0);
        this.dynamoDB = new DynamoDB();

    }


    public void parseExcel()
    {
        for (int i = 1; i < this.sheet.getPhysicalNumberOfRows() - 1; i++) {
            Row row = this.sheet.getRow(i);
            String role = row.getCell(13).getStringCellValue();
            role = role.substring(0, role.indexOf(" "));

            if (role.equals("Mentor")) {
                Mentor mentor = new Mentor(true);
                mentor.setTimeStamp(row.getCell(0).toString());
                mentor.setEmail(row.getCell(1).getStringCellValue());
                mentor.setName(row.getCell(2).getStringCellValue());
                mentor.setAge(row.getCell(3).getStringCellValue());
                long longValue = (long) row.getCell(4).getNumericCellValue();
                String phoneNum = String.valueOf(longValue);
                mentor.setPhone(phoneNum);  // Assuming 'Phone' is stored as a long in Mentor = row.getCell(4).getNumericCellValue();
                mentor.setCity(row.getCell(5).getStringCellValue());
                mentor.setState(row.getCell(6).getStringCellValue());
                mentor.setSessionPreference(row.getCell(7).getStringCellValue());
                mentor.setEthnicity(row.getCell(8).getStringCellValue());
                mentor.setEthnicityPreference(row.getCell(9).getStringCellValue());
                mentor.setGender(row.getCell(10).getStringCellValue());
                mentor.setGenderPreference(row.getCell(11).getStringCellValue());
                mentor.setMentorMethod(row.getCell(12).getStringCellValue());
                mentor.setRole(row.getCell(13).getStringCellValue());
                mentor.setSteamBackground(row.getCell(14).getStringCellValue());
                mentor.setAcademicLevel(row.getCell(15).getStringCellValue());
                mentor.setProfession(row.getCell(16).getStringCellValue());
                mentor.setCurrentEmployer(row.getCell(17).getStringCellValue());
                mentor.setMentorReasons(row.getCell(18).getStringCellValue());
                Cell c1 = row.getCell(19);
                if (c1 != null && c1.getCellType() != CellType.BLANK && c1.getCellType() != CellType.STRING)
                {
                    double mentees = c1.getNumericCellValue();
                    int newMentees = (int) mentees;
                    mentor.setMenteesToAdvice(String.valueOf(newMentees));
                }
                for (int j = 23; j < 30; j++) {  // Assuming the next 7 cells contain values
                    Cell cell = row.getCell(j);
                    if (cell != null && cell.getCellType() == CellType.STRING) {
                        String cellValue = cell.getStringCellValue().trim();
                        if (!cellValue.isEmpty()) {
                            // Split multiple timeframes by commas
                            String[] timeFrames = cellValue.split(",");
                            for (String timeFrame : timeFrames) {
                                // Add trimmed timeframes to the list
                                mentor.setCalendarAvailability(timeFrame.trim());
                            }
                        }
                    }
                }
                Cell cell = row.getCell(30);
                List<String> specifiedDates = new ArrayList<>();

                if (cell != null && cell.getCellType() == CellType.STRING) {
                    String cellValue = cell.getStringCellValue().trim();

                    // Split the cell content by commas (each could be a date or a range)
                    String[] dateEntries = cellValue.split(",");

                    for (String entry : dateEntries) {
                        // Trim extra spaces
                        entry = entry.trim();

                        if (entry.contains("-")) {
                            // It's a range, add as is after trimming spaces around the "-"
                            String[] dateRange = entry.split("-");
                            if (dateRange.length == 2) {
                                // Trim both dates and add the range to the list
                                String startDate = dateRange[0].trim();
                                String endDate = dateRange[1].trim();
                                specifiedDates.add(startDate + " - " + endDate);
                            }
                        } else {
                            specifiedDates.add(entry);
                        }
                    }
                }

                mentor.setSpecifiedDates(specifiedDates);

                Cell c2 = row.getCell(32);
                if (c2 == null || c2.getCellType() != CellType.STRING)
                {
                    mentor.setIsMatched(false);
                }
                System.out.println(mentor);
                dynamoDB.addMentorToTable(mentor);
            }
            else {
                Mentee mentee = new Mentee(true);
                mentee.setTimeStamp(row.getCell(0).toString());
                mentee.setEmail(row.getCell(1).getStringCellValue());
                mentee.setName(row.getCell(2).getStringCellValue());
                mentee.setAge(row.getCell(3).getStringCellValue());
                long longValue = (long) row.getCell(4).getNumericCellValue();
                String phoneNum = String.valueOf(longValue);
                mentee.setPhone(phoneNum);  // Assuming 'Phone' is stored as a long in Mentor = row.getCell(4).getNumericCellValue();
                mentee.setCity(row.getCell(5).getStringCellValue());
                mentee.setState(row.getCell(6).getStringCellValue());
                mentee.setSessionTypePreference(row.getCell(7).getStringCellValue());
                mentee.setEthnicity(row.getCell(8).getStringCellValue());
                mentee.setEthnicityPreference(row.getCell(9).getStringCellValue());
                mentee.setGender(row.getCell(10).getStringCellValue());
                mentee.setGenderPreference(row.getCell(11).getStringCellValue());
                mentee.setMentorMethod(row.getCell(12).getStringCellValue());
                mentee.setRole(row.getCell(13).getStringCellValue());
                mentee.setBackground(row.getCell(14).getStringCellValue());
                mentee.setAcademicLevel(row.getCell(15).getStringCellValue());
                double grade = row.getCell(20).getNumericCellValue();
                int newGrade = (int) grade;
                mentee.setGrade(String.valueOf(newGrade));
                mentee.setReasons(row.getCell(21).toString());
                for (int j = 23; j < 30; j++) {  // Assuming the next 7 cells contain values
                    Cell cell = row.getCell(j);
                    if (cell != null && cell.getCellType() == CellType.STRING) {
                        String cellValue = cell.getStringCellValue().trim();
                        if (!cellValue.isEmpty()) {
                            // Split multiple timeframes by commas
                            String[] timeFrames = cellValue.split(",");
                            for (String timeFrame : timeFrames) {
                                // Add trimmed timeframes to the list
                                mentee.setCalendarAvailability(timeFrame.trim());
                            }
                        }
                    }
                }
                Cell cell = row.getCell(30);
                List<String> specifiedDates = new ArrayList<>();

                if (cell != null && cell.getCellType() == CellType.STRING) {
                    String cellValue = cell.getStringCellValue().trim();

                    // Split the cell content by commas (each could be a date or a range)
                    String[] dateEntries = cellValue.split(",");

                    for (String entry : dateEntries) {
                        // Trim extra spaces
                        entry = entry.trim();

                        if (entry.contains("-")) {
                            // It's a range, add as is after trimming spaces around the "-"
                            String[] dateRange = entry.split("-");
                            if (dateRange.length == 2) {
                                // Trim both dates and add the range to the list
                                String startDate = dateRange[0].trim();
                                String endDate = dateRange[1].trim();
                                specifiedDates.add(startDate + " - " + endDate);
                            }
                        } else {
                            specifiedDates.add(entry);
                        }
                    }
                }
                mentee.setSpecifiedDate(specifiedDates);

                Cell c2 = row.getCell(32);
                if (c2 == null || c2.getCellType() != CellType.STRING)
                {
                    mentee.setIsMatched(false);
                }
                dynamoDB.addMenteeToTable(mentee);

            }
        }
    }
}
