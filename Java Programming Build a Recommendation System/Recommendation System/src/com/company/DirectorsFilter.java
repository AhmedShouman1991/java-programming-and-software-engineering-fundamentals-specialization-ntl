package com.company;

import java.util.ArrayList;

public class DirectorsFilter implements Filter {
    private String director;

    public DirectorsFilter(String directors) {
        this.director = directors;
    }

    @Override
    public boolean satisfies(String id) {
        ArrayList<String> directorsList = new ArrayList<>();
        int pos = director.indexOf(",");
        if (pos == -1) {
            directorsList.add(director);
        }
        int startFrom = 0;
        while (pos != -1) {
            String added = director.substring(startFrom, pos);
            directorsList.add(added);
            startFrom = pos + 2;
            pos = director.indexOf(",", pos + 2);
            if (pos == -1) {
                int lastIndex = director.length();
                added = director.substring(startFrom, lastIndex);
                directorsList.add(added);
            }
        }

        for (String director : directorsList) {
            if (MovieDatabase.getDirector(id).contains(director)) {
                return true;
            }
            //System.out.println(Arrays.toString(directorsList.toArray()));
        }
        return false;
    }
}
