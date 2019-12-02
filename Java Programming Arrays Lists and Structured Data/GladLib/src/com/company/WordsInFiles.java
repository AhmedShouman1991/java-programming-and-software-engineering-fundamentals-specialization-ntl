package com.company;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class WordsInFiles {
   private HashMap<String, ArrayList<String>> map;

    public WordsInFiles() {
        this.map = new HashMap<>();
    }

    private void addWordsFromFile (File file) {
        FileResource f = new FileResource(file);
        for (String word : f.words()) {
            //word = word.toLowerCase();
            if (!map.containsKey(word)) {
                map.put(word, new ArrayList<>());
                map.get(word).add(file.getName());
            } else {
                if (!map.get(word).contains(file.getName())) {
                    map.get(word).add(file.getName());
                }
            }
        }
    }

    public void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    public int maxNumber() {
        int maxFilesNum = 0;
        for (String word : map.keySet()) {
            ArrayList<String> array = map.get(word);
            if (array.size() > maxFilesNum) {
                maxFilesNum = array.size();
            }
        }
        return maxFilesNum;
    }

    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> arrayList = new ArrayList<>();
        int count = 0;
        for (String key : map.keySet()) {
            if (map.get(key).size() == number) {
                arrayList.add(key);
                count++;
            }
        }
        System.out.println("count =============" + count);
        return arrayList;
    }

    public void printFilesIn(String word) {
        ArrayList<String> arrayList = null;
        if (map.containsKey(word)) {
            arrayList = map.get(word);
        }
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                System.out.println(arrayList.get(i));
            }
        }
    }

    public void tester() {
        buildWordFileMap();
//        for (String k : map.keySet()) {
//            System.out.println(k + "     "+ Arrays.toString(map.get(k).toArray()));
//            System.out.println("=================================================================");
//        }
        int maxNumber = maxNumber();
        System.out.println("Maximum num of files = " + maxNumber);
        ArrayList<String> arrayList = wordsInNumFiles(4);
        System.out.println(Arrays.toString(arrayList.toArray()));
        System.out.println("array lenth = " + arrayList.size());
        for (String word : arrayList) {
            System.out.println("Word is =   " + word);
            printFilesIn("tree");
            System.out.println("<___________>");
        }

    }

}
