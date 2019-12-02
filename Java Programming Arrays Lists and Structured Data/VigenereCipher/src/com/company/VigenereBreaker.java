package com.company;

import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {

    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        char[] chars = message.toCharArray();
        for (int i = whichSlice; i < chars.length; i += totalSlices) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    //this method gets the key chars indexes in the alphabit
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        String[] msgSlices = new String[klength];
        for (int i = 0; i < klength; i++) {
            msgSlices[i] = sliceString(encrypted, i, klength);
        }
        //System.out.println(Arrays.toString(msgSlices));
        CaesarCracker ck = new CaesarCracker(mostCommon);
        for (int i = 0; i < msgSlices.length; i++) {
            int theKey = ck.getKey(msgSlices[i]);
            key[i] = theKey;
        }
        return key;
    }

    public String breakVigenere() {
        HashMap<String,HashSet<String>> map = new HashMap<>();
        FileResource f = new FileResource();
        String enMsg = f.asString();
        DirectoryResource dic = new DirectoryResource();
        for (File file : dic.selectedFiles()) {
            String fileName = file.getName();
            System.out.println("reading " + fileName);
            FileResource fr = new FileResource(file);
            HashSet<String> dicWords = readDictionary(fr);
            map.put(fileName, dicWords);
        }
        System.out.println("decrypted vig msg : ");
       return breakForAllLangs(enMsg,map);
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> wordsList = new HashSet<>();
        for (String line : fr.lines()) {
            line = line.toLowerCase();
            if (!wordsList.contains(line)) {
                wordsList.add(line);
            }
        }
        return wordsList;
    }

    public int countWords(String msg, HashSet<String> wordsList) {
        int count = 0;
        String[] msgWords = msg.split("\\W+");
        for (String word : msgWords) {
            if (wordsList.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int counts = 0;
        String WriteMsg = "";
        int[] key = null;
        int lengthOfKey = 0;
        char mostCommon = mostCommonCharIn(dictionary);
        for (int i = 1; i <= 100; i++) {
            int[] testkey = tryKeyLength(encrypted, i,mostCommon);
          VigenereCipher vc = new VigenereCipher(testkey);
          String decrypted = vc.decrypt(encrypted);
          int wordsCount = countWords(decrypted, dictionary);
          if (wordsCount > counts) {
              counts = wordsCount;
              WriteMsg = decrypted;
              key = testkey;
              lengthOfKey = i;
          }
        }
        System.out.println("key Length = " + lengthOfKey);
        System.out.println("Key is " + Arrays.toString(key));
        System.out.println("number of valid words = " + counts +"\n" +"\n");
        return WriteMsg;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character,Integer> charCounts = new HashMap<>();
        for (String word : dictionary) {
            char[] chars = word.toCharArray();
            for (char c : chars) {
                if (!charCounts.containsKey(c)) {
                    charCounts.put(c,1);
                } else {
                    charCounts.put(c, charCounts.get(c) + 1);
                }
            }
        }
        int max = 0;
        Character maxChar = null;
        for (Character c : charCounts.keySet()) {
            int value = charCounts.get(c);
            if (value > max) {
                max = value;
                maxChar = c;
            }
        }
        return maxChar;
    }

    public String breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages) {
        HashMap<String,Integer> wordsCountOnEachLang = new HashMap<>();
        for (String lang : languages.keySet()) {
            System.out.println("lang : " + lang);
            HashSet<String> langDic = languages.get(lang);
            String decrypted =  breakForLanguage(encrypted, langDic);
            int count = 0;
            String[] msgWords = decrypted.split("\\W+");
            for (String word : msgWords) {
                if (langDic.contains(word.toLowerCase())) {
                    count++;
                }
            }
            wordsCountOnEachLang.put(lang + "\n" + decrypted , count);
        }
        int maxCount = 0;
        String maxWords = null;
        for (String text : wordsCountOnEachLang.keySet()) {
            int value = wordsCountOnEachLang.get(text);
            if (value > maxCount) {
                maxCount = value;
                maxWords = text;
            }
        }
        return maxWords;
    }











}
