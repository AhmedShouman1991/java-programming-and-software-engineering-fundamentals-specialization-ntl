package com.company;

import java.util.ArrayList;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        // default constructor
        this("data/ratings_short.csv");
    }

    public ThirdRatings(String ratingsFile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsFile);
    }

    public int getRaterSize() {
        return this.myRaters.size();
    }

    private double getAverageByID(String id, int minimalRaters) {
        int rates = 0;
        for (Rater plainRater : this.myRaters) {
            if (plainRater.hasRating(id)) {
                rates++;
            }
        }
        if (rates >= minimalRaters) {
            int ratesCounts = 0;
            double totalRates = 0;
            for (Rater plainRater : this.myRaters) {
                if (plainRater.hasRating(id)) {
                    ratesCounts++;
                    totalRates += plainRater.getRating(id);
                }
            }
            return totalRates / ratesCounts;
        } else {
            return 0.0;
        }
    }


    public ArrayList<Rating> getAverageRatings(int minimalRatings) {
        ArrayList<Rating> ratingArrayList = new ArrayList<>();
        ArrayList<String> myMoviesIDs = MovieDatabase.filterBy(new TrueFilter());
        for (String id : myMoviesIDs) {
            double rates = getAverageByID(id, minimalRatings);
            if (rates > 0) {
                Rating rating = new Rating(id, rates);
                ratingArrayList.add(rating);
            }
        }
        return ratingArrayList;
    }

    public ArrayList<Rating> getAverageRatingsWithFilter(int minimalRatings, Filter filter) {
        ArrayList<Rating> ratingArrayList = new ArrayList<>();
        ArrayList<String> myMoviesIDs = MovieDatabase.filterBy(filter);
        for (String id : myMoviesIDs) {
            double rates = getAverageByID(id, minimalRatings);
            if (rates > 0) {
                Rating rating = new Rating(id, rates);
                ratingArrayList.add(rating);
            }
        }
        return ratingArrayList;
    }
}