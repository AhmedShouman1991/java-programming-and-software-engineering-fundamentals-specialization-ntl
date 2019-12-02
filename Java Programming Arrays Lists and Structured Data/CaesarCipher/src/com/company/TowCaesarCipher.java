package com.company;

public class TowCaesarCipher {
    private String alpha;
    private String FirstShiftedAlpha;
    private String SecondShiftedAlpha;
    private int key1;
    private int key2;

    public TowCaesarCipher(int key1, int key2) {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.FirstShiftedAlpha = alpha.substring(key1) + alpha.substring(0, key1);
        this.SecondShiftedAlpha = alpha.substring(key2) + alpha.substring(0, key2);
        this.key1 = key1;
        this.key2 = key2;
    }

    public String encryptTwoKeys(String input) {
        StringBuilder sr = new StringBuilder(input);

        for (int i = 0; i < sr.length(); i++) {
            int index = alpha.indexOf(sr.charAt(i));
            if (index != -1) {
                if ((i + 1) % 2 != 0) {
                    char shiftedChar = FirstShiftedAlpha.charAt(index);
                    sr.setCharAt(i, shiftedChar);
                } else {
                    char shiftedChar = SecondShiftedAlpha.charAt(index);
                    sr.setCharAt(i, shiftedChar);
                }
            } else {
                if (Character.isLowerCase(sr.charAt(i))) {
                    int indexOfUChar = alpha.indexOf(Character.toUpperCase(sr.charAt(i)));
                    if ((i + 1) % 2 != 0) {
                        char shiftedChar = Character.toLowerCase(FirstShiftedAlpha.charAt(indexOfUChar));
                        sr.setCharAt(i, shiftedChar);
                    } else {
                        char shiftedChar = Character.toLowerCase(SecondShiftedAlpha.charAt(indexOfUChar));
                        sr.setCharAt(i, shiftedChar);
                    }
                }
            }
        }
        return sr.toString();
    }

    public String decrypt(String encryptedText) {
        TowCaesarCipher c2c = new TowCaesarCipher(26 - key1, 26 - key2);
        return c2c.encryptTwoKeys(encryptedText);
    }
}
