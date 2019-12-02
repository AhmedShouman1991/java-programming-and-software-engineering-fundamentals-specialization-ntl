package com.company;
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

import com.company.LogEntry;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         this.records = new ArrayList<>();
     }
        
     public void readFile(String filename) {
         String path = "data\\" + filename;
         FileResource f = new FileResource(path);
         for (String line : f.lines()) {
            LogEntry log = WebLogParser.parseEntry(line);
            records.add(log);
         }
     }

     public int countUniqueIPs() {
         ArrayList<String> uniqueAddress = new ArrayList<>();
         for (LogEntry le : records) {
             if (!uniqueAddress.contains(le.getIpAddress())) {
                 uniqueAddress.add(le.getIpAddress());
             }
         }
         return uniqueAddress.size();
     }

     public void printAllHigherThanNum(int num) {
         for (LogEntry le : records) {
             if (le.getStatusCode() > num) {
                 System.out.println(le);
             }
         }
     }

     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueAddresses = new ArrayList<>();
         for (LogEntry le : records) {
            String date = le.getAccessTime().toString();
            if (date.contains(someday) && !uniqueAddresses.contains(le.getIpAddress())) {
                System.out.println("data = " + date);
                uniqueAddresses.add(le.getIpAddress());
            }
         }
         System.out.println("number of IPs with same date = " + uniqueAddresses.size());
         return uniqueAddresses;
     }

    public ArrayList<String> iPVisitsOnDay(String someday) {
        ArrayList<String> uniqueAddresses = new ArrayList<>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString();
            if (date.contains(someday)) {
                uniqueAddresses.add(le.getIpAddress());
            }
        }
        return uniqueAddresses;
    }


     public int countUniqueIPsInRange(int low, int high) {
         int count = 0;
         ArrayList<String> IPsList = new ArrayList<>();
         for (LogEntry le : records) {
             if (le.getStatusCode() >= low && le.getStatusCode() <= high) {
                 if (!IPsList.contains(le.getIpAddress())) {
                     count++;
                     IPsList.add(le.getIpAddress());

                 }
             }
         }
         return count;
     }

     public HashMap<String, Integer> countsVisitsPerIP() {
         HashMap<String,Integer> counts = new HashMap<>();
         for (LogEntry le : records) {
             String address = le.getIpAddress();
             if (!counts.containsKey(address)) {
                 counts.put(address,1);
             } else {
                 counts.put(address, counts.get(address) + 1);
             }
         }
         return counts;
     }

     public int mostNumberVisitsByIP(HashMap<String,Integer> map) {
         int most = 0;
         for (String key : map.keySet()) {
             if (map.get(key) > most) {
                 most = map.get(key);
             }
         }
         return most;
     }

    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> map) {
        ArrayList<String> IPs = new ArrayList<>();
        int most = mostNumberVisitsByIP(map);
        for (String key : map.keySet()) {
            int value = map.get(key);
            if (value == most) {
                IPs.add(key);
            }
        }
        return IPs;
    }


    public HashMap<String,ArrayList<String>> iPsForDays() {
         HashMap<String,ArrayList<String>> map = new HashMap<>();
         for (LogEntry le : records) {
             String dateKey = le.getAccessTime().toString();
             dateKey = dateKey.substring(4,10);
             ArrayList<String> IpsOnDay = iPVisitsOnDay(dateKey);
             if (!map.containsKey(dateKey)) {
                map.put(dateKey, IpsOnDay);
             }
         }
         return map;
    }

    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map) {
         int mostIpCount = 0;
         String mostDay = null;

         for (String day : map.keySet()) {
             int IpVisit = map.get(day).size();
             if (IpVisit > mostIpCount) {
                 mostIpCount = IpVisit;
                 mostDay = day;
             }
         }
         return mostDay;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> map, String day) {
         ArrayList<String> list = map.get(day);
         HashMap<String,Integer> visits = new HashMap<>();

         for (String ip : list) {
             if (!visits.containsKey(ip)) {
                 visits.put(ip, 1);
             } else {
                 visits.put(ip, visits.get(ip) + 1);
             }
         }
         return iPsMostVisits(visits);
    }


    public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
