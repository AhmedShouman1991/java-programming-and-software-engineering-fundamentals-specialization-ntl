package com.company;

public class Main {

    public static void main(String[] args) {
        DifferentSorters df = new DifferentSorters();
        //df.sortWithCompareTo();
        //df.sortByTitleAndDepth();
        df.sortByLastWordInTitleThenByMagnitude();
    }
}
