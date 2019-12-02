package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> map;

    public EfficientMarkovWord(int myOrder) {
        this.myOrder = myOrder;
        myRandom = new Random();
        map = new HashMap<>();
    }

    @Override
    public void setTraining(String text) {
        this.myText = text.split("\\s+");
    }

    @Override
    public void setRandom(int seed) {
        this.myRandom = new Random(seed);
    }

    private void buildHashMap() {
        for (int i = 0; i <= myText.length - myOrder; i++) {
            WordGram key = new WordGram(myText, i, myOrder);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            if ((i + myOrder) < myText.length) {
                String follower = myText[i + myOrder];
                ArrayList<String> followers = map.get(key);
                followers.add(follower);
                map.put(key, followers);
            }
        }
    }

    public void printHashMapInfo() {
        buildHashMap();
        System.out.println("number os keys = " + map.size());
//        for (WordGram key : map.keySet()) {
//           System.out.println("Key = "+ key + "     " + Arrays.toString(map.get(key).toArray()));
//        }

        int largestSize = 0;
        for (WordGram key : map.keySet()) {
            int keySize = map.get(key).size();
            if (keySize > largestSize) {
                largestSize = keySize;
            }
        }
        System.out.println("The size of the largest ArrayList of characters: " + largestSize);


    }

    private int indexOf(String[] words, WordGram target, int start) {
        for (int i = start; i <= words.length - target.length(); i++) {
            if (words[i].equals(target.wordAt(0))) {
                int pos = i + 1;
                for (int k = 1; k < target.length(); k++) {
                    if (!target.wordAt(k).equals(words[pos])) break;
                    else pos++;
                    if ((k + 1) == target.length()) return i;
                }
            }
        }
        return -1;
    }

    public ArrayList<String> getFollow(WordGram kGram) {
//        ArrayList<String> follows = new ArrayList<>();
//        int pos = 0;
//        while (pos < myText.length) {
//            int index = indexOf(this.myText, kGram, pos);
//            if (index == -1 || index == myText.length - kGram.length()) break;
//            String follower = myText[index + kGram.length()];
//            follows.add(follower);
//            pos = index + kGram.length() + 1;
//        }
        return map.get(kGram);
    }

    @Override
    public String getRandomText(int numWords) {

        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-this.myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for(int k = 0; k < numWords - myOrder; k++){
            ArrayList<String> follows = getFollow(key);
            //System.out.println("key1 + 2 = " + key1 + " " +key2 + "   follows = " + Arrays.toString(follows.toArray()));
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }

        return sb.toString().trim();
    }
}

