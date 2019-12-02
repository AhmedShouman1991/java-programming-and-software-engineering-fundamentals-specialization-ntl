package com.company;

public class WordPlay {

    public boolean isVowel(char ch) {
        char lch = Character.toLowerCase(ch);
        String vowels = "aeiou";
        for (int i = 0; i < vowels.length(); i++) {
            if (lch == vowels.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    public String replaceVowels(String phrase, char ch) {
        StringBuilder sr = new StringBuilder(phrase);
        for (int i = 0; i < sr.length(); i++) {
            char x = Character.toLowerCase(sr.charAt(i));
            if (isVowel(x)) {
                sr.setCharAt(i,ch);
            }
        }
        return sr.toString();
    }

    public String emphasize(String phrase, char ch) {
        StringBuilder sr = new StringBuilder(phrase);
        for (int i = 0; i < sr.length(); i++) {
            if (Character.toLowerCase(sr.charAt(i)) == Character.toLowerCase(ch)) {
                if ((i + 1) % 2 == 0) {
                    sr.setCharAt(i,'+');
                }else sr.setCharAt(i,'*');
            }
        }
        return sr.toString();
    }

}
