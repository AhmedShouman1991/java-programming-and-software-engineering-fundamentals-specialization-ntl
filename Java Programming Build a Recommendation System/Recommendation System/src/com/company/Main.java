package com.company;

public class Main {

    public static void main(String[] args) {
	    MovieRunnerSimilarRatings m = new MovieRunnerSimilarRatings();
//	    m.printAverageRatings();
//	    m.printAverageRatingsByYearAfterAndGenre();
        //m.printSimilarRatings();
        //m.printSimilarRatingsByGenre();
        //m.printSimilarRatingsByDirector();
        //m.printSimilarRatingsByGenreAndMinutes();
        //m.printSimilarRatingsByYearAfterAndMinutes();
        //m.test();
        RecommendationRunner r = new RecommendationRunner();
                r.printRecommendationsFor("10");
    }
}
