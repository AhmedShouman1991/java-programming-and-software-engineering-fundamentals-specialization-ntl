package com.company;
import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        testTotalBirths();
        //System.out.println("Name Rank = " + getRank(1971, "Frank", "M"));
        //System.out.println("Rank name = " + getName(1980,350,"M"));
        whatIsNameInYear("Susan" ,1972, 2014, "M");
        //System.out.println("Highest Ranked Year = " + yearOfHighestRank("Mich", "M"));
        //System.out.println("Average rank = " + getAverageRank("Susan", "F"));
        //System.out.println("Total Births above name rank = " + getTotalBirthsRankedHigher(1990, "Emily","F"));
    }

    public static void totalBirths(CSVParser parser) {
        int totalNames = 0;
        int totalGirls = 0;
        int totalBoys = 0;

        for (CSVRecord record : parser) {
            totalNames++;
            if (record.get(1).equals("M")) {
                totalBoys++;
            } else {
                totalGirls++;
            }
        }
        System.out.println("Total names count = " + totalNames);
        System.out.println("Total boys names count = " + totalBoys);
        System.out.println("Total girls names count = " + totalGirls);
    }

    public static void testTotalBirths() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        totalBirths(parser);
    }

    public static int getGirlsRecordCount(CSVParser parser) {
        int totalGirls = 0;

        for (CSVRecord record : parser) {
            if (record.get(1).equals("F")) {
                totalGirls++;
            }
        }
        return totalGirls;
    }

    public static int getRank(int year, String name, String gender) {
        int count = 0;

        String fileName = "data\\us_babynames_by_year\\" + "yob" + year + ".csv";
        System.out.println("File founded: " + fileName);
        FileResource fr = new FileResource(fileName);

        CSVParser parserA = fr.getCSVParser(false);
        int girlsCount = getGirlsRecordCount(parserA);
        System.out.println("Total girls records if file = " + girlsCount);

        CSVParser parserB = fr.getCSVParser(false);
        for (CSVRecord record : parserB) {
            count++;
            if (record.get(0).equals(name) && record.get(1).equals(gender)) {
                if (gender.equals("F")) return count;

                else if (gender.equals("M")) {
                    return count - girlsCount;
                }
            }
        }
        return -1;
    }


    public static String getName(int year, int rank, String gender) {
        int count = 0;
        String fileName = "data\\us_babynames_by_year\\" + "yob" + year + ".csv";
        System.out.println("File founded: " + fileName);
        FileResource fr = new FileResource(fileName);

        CSVParser parserA = fr.getCSVParser(false);
        int girlsCount = getGirlsRecordCount(parserA);
        System.out.println("Total girls records if file = " + girlsCount);

        CSVParser parser = fr.getCSVParser(false);

        if (gender.equals("M")) rank += girlsCount;

        for (CSVRecord record : parser) {
            count++;
            if (rank == count && record.get(1).equals(gender)) return record.get(0);
        }
        return "No Name";
    }

    public static void whatIsNameInYear(String name, int yearOfBerth, int newYear, String gender) {
        int rank = getRank(yearOfBerth, name, gender);
        String nameWas = getName(newYear, rank, gender);
        System.out.println("Your name should be = " + nameWas);
    }

    public static int getYearOfFile(String fileName) {
        String year = "";
        char[] nameArray = fileName.toCharArray();
        for (int i = 3; i <= 6; i++) {
            year += nameArray[i];
        }
        System.out.println(year);
        int intYear = Integer.parseInt(year);
        return intYear;
    }

    public static int yearOfHighestRank(String name, String gender) {

        int rank = Integer.MAX_VALUE;
        int highestRankedYear = 0;
        DirectoryResource dr = new DirectoryResource();

        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            String fileName = file.getName();
            int intYear = getYearOfFile(fileName);

            int currantRank = getRank(intYear, name, gender);
             if ( currantRank != -1 && currantRank < rank) {
                rank = currantRank;
                highestRankedYear = intYear;
            }

        }
        return highestRankedYear;
    }

    public static double getAverageRank(String name, String gender) {
        double count = 0.0;
        double sum = 0.0;

        DirectoryResource dr = new DirectoryResource();
        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);

            String fileName = file.getName();
//            String year = "";
//            char[] nameArray = fileName.toCharArray();
//            for (int i = 3; i <= 6; i++) {
//                year += nameArray[i];
//            }
//            System.out.println(year);
            int intYear = getYearOfFile(fileName);;

            int rank = getRank(intYear, name, gender);
            if (rank == -1) return -1.0;
            else {
                count++;
                sum += rank;
            }
        }
        return (double)(sum/count);
    }

    public static int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int sum = 0;
        int nameRank = getRank(year,name,gender);
        int count = 0;

        FileResource fr = new FileResource();

        CSVParser parserA = fr.getCSVParser(false);
        int girlsCount = getGirlsRecordCount(parserA);

        if (gender.equals("M")){
            count -= girlsCount;
        }

        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser) {
            count++;
            if (count < nameRank && record.get(1).equals(gender)) sum += Integer.parseInt(record.get(2));
        }
        return sum;
    }

}