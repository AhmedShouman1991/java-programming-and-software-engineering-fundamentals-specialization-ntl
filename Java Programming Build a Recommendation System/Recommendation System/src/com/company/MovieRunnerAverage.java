package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {

    public void printAverageRatings() {
        SecondRatings sr = new SecondRatings();
        System.out.println(sr.getMovieSize());
        System.out.println(sr.getRaterSize());

        ArrayList<Rating> rates = sr.getAverageRatings(50);
        Collections.sort(rates);
        for (Rating rating : rates) {
            System.out.println(rating.getValue() + "  " + sr.getTitle(rating.getItem()));
        }
    }

    public void getAverageRatingOneMovie() {
        SecondRatings se = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
        String title = "Vacation";
        String id = se.getID(title);
        ArrayList<Rating> ratings = se.getAverageRatings(12);
        System.out.println("number of movies = " + ratings.size());
        Collections.sort(ratings);
        System.out.println(se.getTitle(ratings.get(0).getItem()));
        for (Rating rating : ratings) {
            if (rating.getItem().equals(id)) {
                System.out.println("rating for " + title + " = " + rating.getValue());
            }
        }
    }

}
