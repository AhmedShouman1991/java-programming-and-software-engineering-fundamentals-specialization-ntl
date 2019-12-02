package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class FourthRatings {

    private double getAverageByID(String id, int minimalRaters) {
        int rates = 0;
        for (Rater rater : RaterDatabase.getRaters()) {
            if (rater.hasRating(id)) {
                rates++;
            }
        }
        if (rates >= minimalRaters) {
            int ratesCounts = 0;
            double totalRates = 0;
            for (Rater rater : RaterDatabase.getRaters()) {
                if (rater.hasRating(id)) {
                    ratesCounts++;
                    totalRates += rater.getRating(id);
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


    public double dotProduct(Rater me, Rater r) {
        HashMap<String, Rating> meAfter = new HashMap<>();
        HashMap<String, Rating> rAfter = new HashMap<>();
        for (String movieID : me.getItemsRated()) {
            double newRate = me.getRating(movieID) - 5;
            meAfter.put(movieID, new Rating(movieID, newRate));
        }

        for (String movieID : r.getItemsRated()) {
            double newRate = r.getRating(movieID) - 5;
            rAfter.put(movieID, new Rating(movieID, newRate));
        }
        double dotProduct = 0;
        for (String movieId : meAfter.keySet()) {
            if (rAfter.containsKey(movieId)) {
                dotProduct += (meAfter.get(movieId).getValue() * rAfter.get(movieId).getValue());
            }
        }

        return dotProduct;
    }




    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> similaritiesRatings = new ArrayList<>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater rater : RaterDatabase.getRaters()) {
            if (!rater.getID().equals(id)) {
                double product = dotProduct(me, rater);
                if (product > 0) {
                    similaritiesRatings.add(new Rating(rater.getID(), product));
                }
            }
        }
        Collections.sort(similaritiesRatings, Collections.reverseOrder());
        //System.out.println(Arrays.toString(similaritiesRatings.toArray()));
        return similaritiesRatings;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> movieRated = new ArrayList<>();
        ArrayList<Rating> topSimilar = new ArrayList<>();
        for (int i = 0; i < numSimilarRaters; i++) {
            topSimilar.add(getSimilarities(id).get(i));
        }
        System.out.println(Arrays.toString(topSimilar.toArray()));
        for (String movieID : MovieDatabase.filterBy(new TrueFilter())) {
            int rates = 0;
            double sum = 0;
            double weightedAverage = 0;
            for (int i = 0; i < topSimilar.size(); i++) {
                Rater rater = RaterDatabase.getRater(topSimilar.get(i).getItem());
                double weight = topSimilar.get(i).getValue();
                if (rater.hasRating(movieID)) {
                    rates++;
                    sum += weight * rater.getRating(movieID);
                }
            }
            if (rates >= minimalRaters) {
                weightedAverage = sum/rates;
                movieRated.add(new Rating(movieID, weightedAverage));
            }
        }
        Collections.sort(movieRated, Collections.reverseOrder());
        return movieRated;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filter) {
        ArrayList<Rating> movieRated = new ArrayList<>();
        ArrayList<Rating> topSimilar = new ArrayList<>();
        for (int i = 0; i < numSimilarRaters; i++) {
            topSimilar.add(getSimilarities(id).get(i));
        }
        System.out.println(Arrays.toString(topSimilar.toArray()));
        for (String movieID : MovieDatabase.filterBy(filter)) {
            int rates = 0;
            double sum = 0;
            double weightedAverage = 0;
            for (int i = 0; i < topSimilar.size(); i++) {
                Rater rater = RaterDatabase.getRater(topSimilar.get(i).getItem());
                double weight = topSimilar.get(i).getValue();
                if (rater.hasRating(movieID)) {
                    rates++;
                    sum += weight * rater.getRating(movieID);
                }
            }
            if (rates >= minimalRaters) {
                weightedAverage = sum/rates;
                movieRated.add(new Rating(movieID, weightedAverage));
            }
        }
        Collections.sort(movieRated, Collections.reverseOrder());
        return movieRated;
    }

//    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filter) {
//        ArrayList<Rating> movieRated = new ArrayList<>();
//        ArrayList<Rating> topSimilar = new ArrayList<>();
//        for (int i = 0; i < numSimilarRaters; i++) {
//            topSimilar.add(getSimilarities(id).get(i));
//        }
//        for (String movieID : MovieDatabase.filterBy(filter)) {
//            int rates = 0;
//            for (int i = 0; i < topSimilar.size(); i++) {
//                Rater rater = RaterDatabase.getRater(topSimilar.get(i).getItem());
//                if (rater.hasRating(movieID)) {
//                    rates++;
//                }
//            }
//            if (rates > minimalRaters) {
//                double movieWeighted = 0;
//                for (int k = 0; k < topSimilar.size(); k++) {
//                    Rater rater = RaterDatabase.getRater(topSimilar.get(k).getItem());
//                    double movieRate = rater.getRating(movieID);
//                    movieWeighted += movieRate * topSimilar.get(k).getValue();
//                    }
//                movieRated.add(new Rating(movieID, movieWeighted/rates));
//            }
//        }
//        Collections.sort(movieRated);
//        return movieRated;
//    }
}