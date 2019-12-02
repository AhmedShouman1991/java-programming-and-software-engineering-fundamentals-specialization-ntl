package com.company;

import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String q1Title = q1.getInfo();
        String q2Title = q2.getInfo();
        if (q1Title.compareTo(q2Title) == 0) {
            if (q1.getDepth() > q2.getDepth()) return 1;
            if (q1.getDepth() < q1.getDepth()) return -1;
            else return 0;
        } else {
            return q1Title.compareTo(q2Title);
        }
    }
}
