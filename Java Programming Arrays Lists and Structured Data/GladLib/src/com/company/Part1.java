package com.company;

import edu.duke.FileResource;

import java.util.HashMap;

public class Part1 {
    private HashMap<String,Integer> map;

    public Part1() {
        this.map = new HashMap<>();
    }

    public void buildCodonMap(int start, String dna) {
        map.clear();
        dna = dna.toUpperCase();
        String codon = "";
        for (int i = start; i < dna.length(); i+=3) {
            if ((i + 3) <= dna.length()) {
                codon = dna.substring(i, i + 3);
                if (map.containsKey(codon)) {
                    map.put(codon, map.get(codon) + 1);
                } else {
                    map.put(codon, 1);
                }
            }
        }
        System.out.println("Reading frame starting with " + start + " results in  " + map.size() + " unique codons");
    }

    public String getMostCommonCodon() {
        int maxValu = 0;
        String maxCodon = null;
        for (String key : map.keySet()) {
            if (map.get(key) > maxValu) {
                maxValu = map.get(key);
                maxCodon = key;
            }
        }
        return maxCodon;
    }

    public void printCodonCounts(int start, int end) {
        System.out.println("codons between " + start + " and " + end +  " are:");
        for (String key : map.keySet()) {
            int value = map.get(key);
            if (value >= start && value <= end) {
                System.out.println(key + "  " + value);
            }
        }
    }

    public void tester() {
        FileResource f = new FileResource();
        String dna = f.asString().toUpperCase().trim();
        for (int i =0; i<=2; i++) {
            buildCodonMap(i,dna);
            String mostCommon = getMostCommonCodon();
            System.out.println("Most common codon = " + mostCommon + "    " + map.get(mostCommon));
            printCodonCounts(0,1000);
            System.out.println("===========================================================");
        }
    }

}
