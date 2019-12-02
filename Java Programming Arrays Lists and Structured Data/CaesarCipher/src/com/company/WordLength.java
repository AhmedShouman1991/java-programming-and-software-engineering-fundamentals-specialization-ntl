package com.company;

import edu.duke.FileResource;

import java.util.Arrays;

public class WordLength {

    private void countWordLengths(FileResource fr, int[] counts) {
        for (String word : fr.words()) {
            int length = word.length();
            if (!Character.isLetter(word.charAt(0))) {
                length--;
            }
            if (!Character.isLetter(word.charAt(word.length() - 1))) {
                length--;
            }
            if (length < 0) length = 1;
            if (length >= 90) {
                counts[counts.length - 1]++;
            }else {
                counts[length]++;
            }
        }
        System.out.println(Arrays.toString(counts));
    }

   public void testCountWordLengths() {
        int[] counts = new int[90];
        FileResource f = new FileResource();
        countWordLengths(f, counts);
        for (int i = 1; i < counts.length; i++) {
            System.out.println("Number of words with length [" + i + "]" + " = " + counts[i]);
        }
       System.out.println("Largest length count = " + indexOfMax(counts));
   }

   public int indexOfMax(int[] countsArray) {
        int maxIndex = 0;
        for (int i = 0; i < countsArray.length; i++) {
            if (countsArray[i] > countsArray[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
   }

}
