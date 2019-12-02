package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
//        String testText = "a b c d e b c g g";
//        String[] textTest = testText.split("\\s+");
//        String text = "b c e f";
//        String[] textarray = text.split("\\s+");
//        WordGram w = new WordGram(textarray, 0, textarray.length);
//        System.out.println(w.length());
//        System.out.println(w.toString());
//        MarkovWord m = new MarkovWord(4);
//        m.setTraining(testText);
//        System.out.println((Arrays.toString(m.getFollow(w).toArray())));
        MarkovRunner m = new MarkovRunner();
        //m.runMarkov();
        m.testHashMap();
    }
}
