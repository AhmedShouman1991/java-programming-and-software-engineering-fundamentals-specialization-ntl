package com.company;
import edu.duke.*;

import java.util.Arrays;

public class CaesarCipher {

    public String encrypt(String input, int key) {

        StringBuilder sr = new StringBuilder(input);

        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlpha = alpha.substring(key) + alpha.substring(0,key);

        for (int i = 0; i < sr.length(); i++) {
            int index = alpha.indexOf(sr.charAt(i));
            if (index != -1) {
                char shiftedChar = shiftedAlpha.charAt(index);
                sr.setCharAt(i,shiftedChar);
            }else {
                if (Character.isLowerCase(sr.charAt(i))) {
                    int indexOfUChar = alpha.indexOf(Character.toUpperCase(sr.charAt(i)));
                    char shiftedChar =Character.toLowerCase(shiftedAlpha.charAt(indexOfUChar));
                    sr.setCharAt(i,shiftedChar);
                }
            }
        }
        return sr.toString();
    }


    public String testEncryption(int key) {
        FileResource f = new FileResource();
        String massage = f.asString();
        return encrypt(massage,key);
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder sr = new StringBuilder(input);
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphaNUM1 = alpha.substring(key1) + alpha.substring(0, key1);
        String shiftedAlphaNUM2 = alpha.substring(key2) + alpha.substring(0, key2);

        for (int i = 0; i < sr.length(); i++) {
            int index = alpha.indexOf(sr.charAt(i));
            if (index != -1) {
                if ((i + 1) % 2 != 0) {
                    char shiftedChar = shiftedAlphaNUM1.charAt(index);
                    sr.setCharAt(i, shiftedChar);
                } else {
                    char shiftedChar = shiftedAlphaNUM2.charAt(index);
                    sr.setCharAt(i, shiftedChar);
                }
            } else {
                if (Character.isLowerCase(sr.charAt(i))) {
                    int indexOfUChar = alpha.indexOf(Character.toUpperCase(sr.charAt(i)));
                    if ((i + 1) % 2 != 0) {
                        char shiftedChar = Character.toLowerCase(shiftedAlphaNUM1.charAt(indexOfUChar));
                        sr.setCharAt(i, shiftedChar);
                    } else {
                        char shiftedChar = Character.toLowerCase(shiftedAlphaNUM2.charAt(indexOfUChar));
                        sr.setCharAt(i, shiftedChar);
                    }
                }
            }
        }
        return sr.toString();
    }

    public String decrypt(String encrypt) {
        int[] lettersCounter = getLettersCounter(encrypt);
        //System.out.println(Arrays.toString(lettersCounter));
        int maxIndex = getMaxIndex(lettersCounter);
        System.out.println("MaxIndex = " + maxIndex);
        int decryptKey = 14 - maxIndex;
        if (decryptKey < 14) {
            decryptKey = 26 - 14 + maxIndex;
        }
        return encrypt(encrypt, 26 - decryptKey);
    }

    public int getDecryptionKey(String encrypt) {
        int[] lettersCounter = getLettersCounter(encrypt);
        //System.out.println(Arrays.toString(lettersCounter));
        int maxIndex = getMaxIndex(lettersCounter);
        System.out.println("MaxIndex = " + maxIndex);
        int decryptKey = maxIndex - 4;
        if (maxIndex < 4) {
            decryptKey = 26 - 4 + maxIndex;
        }
        return decryptKey;
    }


    public int[] getLettersCounter(String encrypted) {
        int[] lettersCounts = new int[26];

        String letters = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < encrypted.length(); i++) {
            int index = letters.indexOf(Character.toLowerCase(encrypted.charAt(i)));
            if (index != -1) {
                lettersCounts[index]++;
            }
        }
        return lettersCounts;
    }
    public int getMaxIndex(int[] lettersCounts) {

        int maxIndex = 0;
        for (int i = 0; i < lettersCounts.length; i++) {
            if (lettersCounts[i] > lettersCounts[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public String decryptTwoKeys(String encrypt) {
        StringBuilder stringA = new StringBuilder();
        StringBuilder stringB = new StringBuilder() ;

        for (int i = 0; i < encrypt.length(); i++) {
            if ((i + 1) % 2 != 0) {
                stringA.append(encrypt.charAt(i));
            }else {
                stringB.append(encrypt.charAt(i));
            }
        }
        String stringAA = stringA.toString();
        String stringBB = stringB.toString();
        System.out.println("string a = " + stringAA);
        System.out.println("string b = " + stringBB);
         int key1 = getDecryptionKey(stringAA);
         int key2 = getDecryptionKey(stringBB);
        System.out.println("key 1 = " + key1 + "    key 2 = " + key2);
         return encryptTwoKeys(encrypt, 26 - key1, 26 - key2);
    }
}
