package com.company;

import edu.duke.FileResource;

import java.util.Arrays;
import java.util.SortedMap;

public class Main {

    public static void main(String[] args) {
        WordPlay wp = new WordPlay();
        CaesarCipher c = new CaesarCipher();
        WordLength w = new WordLength();
//        System.out.println(wp.isVowel('i'));
//
//        System.out.println(wp.replaceVowels("hi every body",'*'));
//        System.out.println(wp.emphasize("dna ctgaaactga",'a'));
//        System.out.println(wp.emphasize("Mary Bella Abracadabra",'a'));
        //System.out.println(c.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?",15));
//        String encrepted = c.testEncryption(23);
//        System.out.println(encrepted);
//        String decrepted = c.encrypt(encrepted,26-23);
//        System.out.println(decrepted);
        System.out.println(c.encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?",21,8));
        //System.out.println(Arrays.toString(c.getLettersCounter("At noon be in the conference room with your hat on for a surprise party. YELL LOUD")));
        //String encrypted = c.encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",15);
        //System.out.println(c.decrypt(encrypted));


        //w.testCountWordLengths();

//        System.out.println(c.encryptTwoKeys("Just a test string with lots of eeeeeeeeeeeeeeeees", 23,2));
//        String encrypted = c.encrypt("Just a test string with lots of eeeeeeeeeeeeeeeees", 23);
//        System.out.println(c.encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 26-2, 26- 20));
//        FileResource f = new FileResource();
//        String encrypt = f.asString();
//        System.out.println(c.decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));








    }
}
