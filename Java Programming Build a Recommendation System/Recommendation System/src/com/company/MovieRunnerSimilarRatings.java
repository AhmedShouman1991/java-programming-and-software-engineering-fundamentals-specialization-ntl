package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class MovieRunnerSimilarRatings {

    public void test() {
        FourthRatings sr = new FourthRatings();
        RaterDatabase.initialize("rates.csv");
        Rater me = RaterDatabase.getRater("15");
        Rater r = RaterDatabase.getRater("20");
        System.out.println(sr.dotProduct(me,r));

    }

    public void printAverageRatings() {
        FourthRatings sr = new FourthRatings();
        RaterDatabase.initialize("ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println(MovieDatabase.size());
        System.out.println(RaterDatabase.size());

        ArrayList<Rating> rates = sr.getAverageRatings(1);
        System.out.println("number of movies with ratings = " + rates.size());
        Collections.sort(rates);
        for (Rating rating : rates) {
            System.out.println(rating.getValue() + "  " + MovieDatabase.getTitle(rating.getItem()));
        }
    }


    public void printAverageRatingsByYearAfterAndGenre() {
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings_short.csv");
        System.out.println(MovieDatabase.size());
        System.out.println(RaterDatabase.size());

        Filter f = new YearAfterFilter(1990);
        Filter f2 = new GenreFilter("Drama");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(f);
        allFilters.addFilter(f2);
        ArrayList<Rating> rates = sr.getAverageRatingsWithFilter(1, allFilters);
        System.out.println("number of movies with ratings = " + rates.size());
        Collections.sort(rates);
        for (Rating rating : rates) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + " " + MovieDatabase.getYear(rating.getItem()));
            System.out.println("    " + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printSimilarRatings() {
        FourthRatings f = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        ArrayList<Rating> rates = f.getSimilarRatingsByFilter("71",20,5, new TrueFilter());

        for (Rating rating : rates) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + " " + rating.getValue());
        }
    }


    public void printSimilarRatingsByGenre() {
        FourthRatings f = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        ArrayList<Rating> rates = f.getSimilarRatingsByFilter("964",20,5, new GenreFilter("Mystery"));

        for (Rating rating : rates) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + " " + rating.getValue());
            System.out.println(MovieDatabase.getGenres(rating.getItem()));
        }
    }


    public void printSimilarRatingsByDirector() {
        FourthRatings f = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        AllFilters filters = new AllFilters();

        ArrayList<Rating> rates = f.getSimilarRatingsByFilter("120",10,2, new DirectorsFilter("Clint Eastwood, J.J. Abrams, Alfred Hitchcock, Sydney Pollack, David Cronenberg, Oliver Stone, Mike Leigh"));

        for (Rating rating : rates) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + " " + rating.getValue());
            System.out.println(MovieDatabase.getDirector(rating.getItem()));
        }
    }

    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings f = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        AllFilters filters = new AllFilters();
        filters.addFilter(new GenreFilter("Drama"));
        filters.addFilter(new MinutesFilter(80,160));
        ArrayList<Rating> rates = f.getSimilarRatingsByFilter("168",10,3,filters);

        for (Rating rating : rates) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + " " + rating.getValue());
            System.out.println(MovieDatabase.getDirector(rating.getItem()));
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings f = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        AllFilters filters = new AllFilters();
        filters.addFilter(new YearAfterFilter(1975));
        filters.addFilter(new MinutesFilter(70, 200));
        ArrayList<Rating> rates = f.getSimilarRatingsByFilter("314",10,5,filters);

        for (Rating rating : rates) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + " " + rating.getValue());
            System.out.println(MovieDatabase.getDirector(rating.getItem()));
        }
    }
}
