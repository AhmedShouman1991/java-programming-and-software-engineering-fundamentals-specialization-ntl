package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {

    public void printAverageRatings() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println(MovieDatabase.size());
        System.out.println(sr.getRaterSize());

        ArrayList<Rating> rates = sr.getAverageRatings(35);
        System.out.println("number of movies with ratings = " + rates.size());
        Collections.sort(rates);
        for (Rating rating : rates) {
            System.out.println(rating.getValue() + "  " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYear() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println(MovieDatabase.size());
        System.out.println(sr.getRaterSize());

        Filter f = new YearAfterFilter(2000);
        ArrayList<Rating> rates = sr.getAverageRatingsWithFilter(20, f);
        System.out.println("number of movies with ratings = " + rates.size());
        Collections.sort(rates);
        for (Rating rating : rates) {
            System.out.println(rating.getValue() + "  " + MovieDatabase.getYear(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByGenre() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println(MovieDatabase.size());
        System.out.println(sr.getRaterSize());

        Filter f = new GenreFilter("Comedy");
        ArrayList<Rating> rates = sr.getAverageRatingsWithFilter(20, f);
        System.out.println("number of movies with ratings = " + rates.size());
        Collections.sort(rates);
        for (Rating rating : rates) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    " + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printAverageRatingsByMinutes() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println(MovieDatabase.size());
        System.out.println(sr.getRaterSize());

        Filter f = new MinutesFilter(105, 135);
        ArrayList<Rating> rates = sr.getAverageRatingsWithFilter(5, f);
        System.out.println("number of movies with ratings = " + rates.size());
        Collections.sort(rates);
        for (Rating rating : rates) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " Time = " + MovieDatabase.getMinutes(rating.getItem()));
        }
    }

    public void printAverageRatingsByDirectors() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println(MovieDatabase.size());
        System.out.println(sr.getRaterSize());

        Filter f = new DirectorsFilter("Clint Eastwood, Joel Coen, Martin Scorsese, Roman Polanski, Nora Ephron, Ridley Scott, Sydney Pollack");
        ArrayList<Rating> rates = sr.getAverageRatingsWithFilter(4, f);
        System.out.println("number of movies with ratings = " + rates.size());
        Collections.sort(rates);
        for (Rating rating : rates) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    " + MovieDatabase.getDirector(rating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println(MovieDatabase.size());
        System.out.println(sr.getRaterSize());

        Filter f = new YearAfterFilter(1990);
        Filter f2 = new GenreFilter("Drama");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(f);
        allFilters.addFilter(f2);
        ArrayList<Rating> rates = sr.getAverageRatingsWithFilter(8, allFilters);
        System.out.println("number of movies with ratings = " + rates.size());
        Collections.sort(rates);
        for (Rating rating : rates) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " " + MovieDatabase.getYear(rating.getItem()));
            System.out.println("    " + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println(MovieDatabase.size());
        System.out.println(sr.getRaterSize());

        Filter f = new MinutesFilter(90,180);
        Filter f2 = new DirectorsFilter("Clint Eastwood, Joel Coen, Tim Burton, Ron Howard, Nora Ephron, Sydney Pollack");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(f);
        allFilters.addFilter(f2);
        ArrayList<Rating> rates = sr.getAverageRatingsWithFilter(3, allFilters);
        System.out.println("number of movies with ratings = " + rates.size());
        Collections.sort(rates);
        for (Rating rating : rates) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " " + MovieDatabase.getMinutes(rating.getItem()));
            System.out.println("    " + MovieDatabase.getDirector(rating.getItem()));
        }
    }
}
