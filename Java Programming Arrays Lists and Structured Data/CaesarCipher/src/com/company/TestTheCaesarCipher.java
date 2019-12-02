package com.company;

import edu.duke.FileResource;

public class TestTheCaesarCipher {
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

    public void simpleTests() {
        FileResource f = new FileResource();
        String text = f.asString();
        TheCaesarCipher cc1 = new TheCaesarCipher(18);

        String encrypted = cc1.encrypt(text);
        System.out.println("encrypted text = " + encrypted);

        String decrypted = cc1.decrypt(encrypted);
        System.out.println("decrypted text = " + decrypted);

        System.out.println("decrypted by brake the cipher  " + breakCaesarCipher(encrypted));
    }

    public String breakCaesarCipher(String encryptedText) {
        int[] lettersCounter = getLettersCounter(encryptedText);
        //System.out.println(Arrays.toString(lettersCounter));
        int maxIndex = getMaxIndex(lettersCounter);
        System.out.println("MaxIndex = " + maxIndex);
        int decryptKey = 14 - maxIndex;
        if (decryptKey < 14) {
            decryptKey = 26 - 14 + maxIndex;
        }
        TheCaesarCipher ccc = new TheCaesarCipher(26 - decryptKey);
        return ccc.encrypt(encryptedText);
    }
}
