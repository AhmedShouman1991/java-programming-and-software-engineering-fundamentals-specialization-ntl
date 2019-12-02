package com.company;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

    public String getLastWordInTitle(QuakeEntry q) {
        String qTitle = q.getInfo();
        int lastIndexOfSpaceInTitle = qTitle.lastIndexOf(" ");
        return qTitle.substring(lastIndexOfSpaceInTitle, qTitle.length());
    }


    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String q1LastWordInTitle = getLastWordInTitle(q1);
        String q2LastWordInTitle = getLastWordInTitle(q2);

        //System.out.println(q1LastWordInTitle + "    " + q2LastWordInTitle);

        if (q1LastWordInTitle.compareTo(q2LastWordInTitle) == 0) {
            if (q1.getMagnitude() > q2.getMagnitude()) return 1;
            else if (q1.getMagnitude() < q2.getMagnitude()) return -1;
            else return 0;
        } else return q1LastWordInTitle.compareTo(q2LastWordInTitle);
    }
}
