package com.company;

import edu.duke.*;
import org.apache.commons.csv.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class FirstRatings {


    public ArrayList<Movie> loadMovies(String fileName) {
        ArrayList<Movie> movies = new ArrayList<>();
        FileResource f = new FileResource(fileName);
        CSVParser parser = f.getCSVParser();
        for (CSVRecord record : parser) {
            //id,title,year,country,genre,director,minutes,poster
            movies.add(new Movie(record.get("id"), record.get("title"),
                    record.get("year"), record.get("genre"), record.get("director"),
                    record.get("country"), record.get("poster"),
                    Integer.parseInt(record.get("minutes"))));
        }
        return movies;
    }
    public void testLoadMovies() {
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        System.out.println("number of movies in list = " + movies.size());
//        for (Movie movie : movies) {
//            System.out.println(movie);
//        }

        String genre = "Comedy";
        int maxMinuets = 150;
        HashMap<String, Integer> directors = new HashMap<>();
        int genreCount = 0;
        int maxMineCount = 0;
        int maxMovies = 0;
        ArrayList<String> namesWithMaxMovies = new ArrayList<>();

        for (Movie movie : movies) {
            //Stéphane Aubier, Vincent Patar, ahmed ramadan
            String director = movie.getDirector();
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
            for (String name : directorsList) {
                if (!directors.containsKey(name)) {
                    directors.put(name, 1);
                } else {
                    directors.put(name, directors.get(name) + 1);
                }
            }
            //System.out.println("size = " + directors.size());

            if (movie.getGenres().contains(genre)) {
                genreCount++;
            }
            if (movie.getMinutes() > maxMinuets) {
                maxMineCount++;
            }
        }
        for (String name : directors.keySet()) {
            if (directors.get(name) > maxMovies) {
                maxMovies = directors.get(name);
            }
        }
        for (String name : directors.keySet()) {
            //System.out.println(name);
            if (directors.get(name) == maxMovies) {
                //System.out.println(name);
                namesWithMaxMovies.add(name);
            }
        }
        System.out.println("number of movies contains " + genre + " = " + genreCount);
        System.out.println("number of movies length more then " + maxMinuets + " = " + maxMineCount);
        System.out.println("maximum number of movies by any director = " + maxMovies);
        System.out.println("names List: ");
        for (String name : namesWithMaxMovies) {
            System.out.println(name);
        }
    }

    public ArrayList<Rater> loadRaters(String fileName) {
        ArrayList<Rater> efficientRater = new ArrayList<>();
        FileResource f = new FileResource(fileName);
        CSVParser parser = f.getCSVParser();

        for (CSVRecord record : parser) {
            String id = record.get("rater_id");
            if (efficientRater.isEmpty() || efficientRater.size() < Integer.parseInt(id)) {
                Rater Rater = new EfficientRater(id);
                Rater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                efficientRater.add(Rater);
            } else {
                efficientRater.get(Integer.parseInt(id) - 1).addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
            }
        }
        return efficientRater;
    }

    public void testLoadRaters() {
        ArrayList<Rater> Raters = loadRaters("data/ratings.csv");
        for (int i = 0; i < Raters.size(); i++) {
            System.out.println("id = " + Raters.get(i).getID() + " - number of ratings = " + Raters.get(i).numRatings());
            //System.out.println(Arrays.toString(raters.get(i).getMyRatings().toArray()));
        }
        System.out.println("total number of rates records = " + Raters.size());
        String idToFind = "1";
        System.out.println("Number of ratings for id " + idToFind + " = " + Raters.get(Integer.parseInt(idToFind) - 1).numRatings() + " rates");

        int maxRaters = 0;
        for (Rater rater : Raters) {
            if (rater.numRatings() > maxRaters) {
                maxRaters = rater.numRatings();
            }
        }
        System.out.println("number of maxRaters = " + maxRaters);
        int countMaxRaters = 0;
        System.out.println("===================================");
        System.out.println("lis of max raters: ");
        for (Rater plainRater : Raters) {
            if (plainRater.numRatings() == maxRaters) {
                countMaxRaters++;
                System.out.println("id = " + plainRater.getID());
            }
        }
        System.out.println("=====================================");
        System.out.println("raters with max rates count = " + countMaxRaters);

        String movieId = "1798709";
        int movieRatingCount = 0;
        for (Rater rater : Raters) {
            if (rater.hasRating(movieId)) {
                movieRatingCount++;
            }
        }
        System.out.println("movie with id " + movieId + " has " + movieRatingCount + " rates");
        ArrayList<String> differentMovies = new ArrayList<>();
        for (Rater rater : Raters) {
            for (String movie : rater.getItemsRated()) {
                if (!differentMovies.contains(movie)) {
                    differentMovies.add(movie);
                }
            }
        }
        System.out.println(Arrays.toString(differentMovies.toArray()));
        System.out.println("number of differentMovies = " + differentMovies.size());
    }









}
