package com.company;

/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.ArrayList;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myPlainRaters;

    public SecondRatings() {
        // default constructor
        this("data/ratedmovies_short.csv", "data/ratings_short.csv");
    }

    public SecondRatings(String movieFile, String ratingsFile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(movieFile);
        myPlainRaters = fr.loadRaters(ratingsFile);
    }

    public int getMovieSize() {
        return this.myMovies.size();
    }
    public int getRaterSize() {
        return this.myPlainRaters.size();
    }

    private double getAverageByID(String id, int minimalRaters) {
        int rates = 0;
        for (Rater plainRater : this.myPlainRaters) {
            if (plainRater.hasRating(id)) {
                rates++;
            }
        }
        if (rates >= minimalRaters) {
            int ratesCounts = 0;
            double totalRates = 0;
            for (Rater plainRater : this.myPlainRaters) {
                if (plainRater.hasRating(id)) {
                    ratesCounts++;
                    totalRates += plainRater.getRating(id);
                }
            }
            return totalRates/ratesCounts;
        } else {
            return 0.0;
        }
    }


    public ArrayList<Rating> getAverageRatings(int minimalRatings) {
        ArrayList<Rating> ratingArrayList = new ArrayList<>();
        for (Movie movie : this.myMovies) {
            String movieId = movie.getID();
            double rates = getAverageByID(movieId, minimalRatings);
            if (rates > 0) {
                Rating rating = new Rating(movieId, rates);
                ratingArrayList.add(rating);
            }
        }
        return ratingArrayList;
    }


    public String getTitle(String id) {
        for (Movie movie : this.myMovies) {
            if (movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        return "ID was not found";
    }

    public String getID(String title) {
        for (Movie movie : this.myMovies) {
            if (movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }
        return "NO SUCH TITLE";
    }
}