package com.company;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import com.company.LogEntry;

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    public void testUniqueIP() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        System.out.println("number of unique IPs = " + logAnalyzer.countUniqueIPs());
    }

    public void testPrintAllHigherThanNum(int num) {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog1_log");
        System.out.println("list of IPs has status code greater than " + num + " =" );
        logAnalyzer.printAllHigherThanNum(num);
    }

    public void testUniqueIPVisitsOnDay (String someDay) {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        System.out.println("list of IPs in " + someDay + ":-");
        for (String ip : logAnalyzer.uniqueIPVisitsOnDay(someDay)) {
            System.out.println(ip);
        }
        System.out.println("==================================");
    }

    public void countUniqueIPsInRange(int low, int high) {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        System.out.println(" number of IPs from low to high = " + logAnalyzer.countUniqueIPsInRange(low,high));
    }

    public void testLogAnalyzer() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("short-test_log");
        logAnalyzer.printAll();
    }
    public void testcountUniqueIPsHashMap() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog1_log");
        System.out.println(logAnalyzer.countsVisitsPerIP());
    }

    public void testMostNumberVisitsByIP() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        int most = logAnalyzer.mostNumberVisitsByIP(logAnalyzer.countsVisitsPerIP());
        System.out.println("most number of visits =   " + most );
    }

    public void testIPMostVisits() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        HashMap<String,Integer> IPsCount = logAnalyzer.countsVisitsPerIP();
        ArrayList<String> mostIPs = logAnalyzer.iPsMostVisits(IPsCount);
        System.out.println(Arrays.toString(mostIPs.toArray()));
    }


    public void testIPsForDays() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog3-short_log");
        HashMap<String,ArrayList<String>> map = logAnalyzer.iPsForDays();

        for (String key : map.keySet()) {
            System.out.println(key);
            for (int i = 0; i < map.get(key).size(); i++) {
                System.out.println(map.get(key).get(i));
            }
            System.out.println("______________________");
        }
    }

    public void testDayWithMostIPVisits() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> map = logAnalyzer.iPsForDays();
        String mostDay = logAnalyzer.dayWithMostIPVisits(map);
        System.out.println("most day ip visited = " + mostDay);
    }

    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> map = logAnalyzer.iPsForDays();
        ArrayList<String> list = logAnalyzer.iPsWithMostVisitsOnDay(map,"Sep 29");
        System.out.println(Arrays.toString(list.toArray()));
    }
}
