package com.company;
import edu.duke.*;
import java.util.*;

public class GladLibMap {

    private ArrayList<String> temp;
    private ArrayList<String> tracking;
    private HashMap<String, ArrayList<String>> map;
    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap(){
        map = new HashMap<>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLibMap(String source){
        map = new HashMap<>();
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun","color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        for (String word : categories) {
           ArrayList<String> arrayList = readIt(source+"/" + word +".txt");
           map.put(word, arrayList);
        }

        temp = new ArrayList<>();
        tracking = new ArrayList<>();
    }

    private String randomFrom(ArrayList<String> source){
        while (true) {
            int index = myRandom.nextInt(source.size());
            System.out.println(source.get(index));
            if (temp.isEmpty()) {
                temp.add(source.get(index));
                return source.get(index);
            } if (!temp.contains(source.get(index))) {
                temp.add(source.get(index));
                return source.get(index);
            }
        }
    }

    private String getSubstitute(String label) {
        tracking.add(label);
        if (map.containsKey(label)) {
            ArrayList<String> list = map.get(label);
            return randomFrom(list);
        } else if (label.equals("number")) {
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    private int totalWordsInMap() {
        int sum = 0;
        for (String word : map.keySet()) {
            sum += map.get(word).size();
        }
        return sum;
    }

    public int totalWordsConsidered() {
        int sum = 0;
        for (String word : tracking) {
            sum += map.get(word).size();
        }
        System.out.println(Arrays.toString(tracking.toArray()));
        return sum;
    }

    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/xxx.txt");
        printOut(story, 60);
        System.out.println("total number of word in map = " + totalWordsInMap());
        totalWordsConsidered();

    }



}
