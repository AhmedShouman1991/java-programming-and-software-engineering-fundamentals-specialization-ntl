package com.company;

import edu.duke.FileResource;

public class Test2CaesarCipher {

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

    public String breakCaesarCipher(String encrypt) {
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

        TowCaesarCipher c2c = new TowCaesarCipher(26 - key1, 26 - key2);
        return c2c.encryptTwoKeys(encrypt);
    }

    public void simpleTests() {
        FileResource f = new FileResource();
        String text = f.asString();
        TowCaesarCipher cc1 = new TowCaesarCipher(17,3);

        String encrypted = cc1.encryptTwoKeys(text);
        System.out.println("encrypted text = " + encrypted);

        String decrypted = cc1.decrypt(encrypted);
        System.out.println("decrypted text = " + decrypted);

        System.out.println("decrypted by brake the cipher  " + breakCaesarCipher(encrypted));
    }
}
