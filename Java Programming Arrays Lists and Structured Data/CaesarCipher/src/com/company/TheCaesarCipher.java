package com.company;

public class TheCaesarCipher {
   private String alpha;
   private String shiftedAlpha;
   private int mainKey;

    public TheCaesarCipher(int key) {
        this.alpha = "abcdefghijklmnopqrstuvwxyz";
        this.shiftedAlpha = alpha.substring(key) + alpha.substring(0,key);
        this.mainKey = key;
    }
    public String encrypt(String input) {
        StringBuilder sr = new StringBuilder(input);
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

    public String decrypt(String encryptedInput) {
        TheCaesarCipher cc = new TheCaesarCipher(26 - mainKey);
        return cc.encrypt(encryptedInput);
    }

}
