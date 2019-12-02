package com.company;

import java.util.ArrayList;
import java.util.Random;

public class RecommendationRunner implements Recommender {
    @Override
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<String> outPut = new ArrayList<>();
        for (String movieId : movies) {
            if (MovieDatabase.getGenres(movieId).contains("Action") && MovieDatabase.getYear(movieId) >= 2012) {
                outPut.add(movieId);
                if (outPut.size() >= 12) break;
            }
        }
        return outPut;
    }

    @Override
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> webRatings = fr.getSimilarRatings(webRaterID, 1, 3);
        ArrayList<Rating> recommendations = new ArrayList<>();

        int howMany = 6;
        if (webRatings.size() < 6 && webRatings.size() > 0) {
            howMany = webRatings.size();
        }

        if (webRatings.size() == 0) {
            ArrayList<String> randomMovie = MovieDatabase.filterBy(new TrueFilter());
            Random r = new Random();
            for (int i = 0; i < 6; i++) {
                int index = r.nextInt(randomMovie.size());
                String movieID = randomMovie.get(index);
                double movieRate = 5;
                recommendations.add(new Rating(movieID, movieRate));
            }
        }

       for (int i = 0; i < howMany; i++) {
           recommendations.add(webRatings.get(i));
       }

        System.out.println("<html>");
        System.out.println("<head>");
        System.out.println("<style>");
        System.out.println("body{background-color :SpringGreen;}");
        System.out.println(" title{\n" +
                "  font-family:\"Courier New\", Courier, monospace;\n" +
                "  font-size:30px;\n" +
                "}");
        System.out.println("h2{\n" +
                "  font-family:\"Courier New\", Courier, monospace;\n" +
                "  color:white;\n" +
                "  margin:0pt;\n" +
                "  text-align: center;\n" +
                "  padding-top:5px;\n" +
                "  padding-bottom:10px;\n" +
                "  border:solid;\n" +
                "  color:black;\n"+
                "}");
        System.out.println("p{\n" +
                "  font-family:\"Courier New\", Courier, monospace;\n" +
                "  text-align: center;\n" +
                "  background-color:white;\n" +
                "}");
        System.out.println("</style>");
        System.out.println("<title> <b> Welcome to MyRecommendation website</b></title>");
        System.out.println("</head>");
        System.out.println("<body>");
        System.out.println("<h2> your top recommendation are : </h2>");
        System.out.println("<p>");
        System.out.println("<table>");
        System.out.println("<tr>");
        System.out.println("<th> Movie ID </th>");
        System.out.println("<th> Movie title </th>");
        System.out.println("<th> Movie year </th>");
        System.out.println("<th> Movie Genre </th>");
        System.out.println("<th> Duration </th>");
        System.out.println("</tr>");


        for (Rating rating : recommendations) {
            System.out.println("<tr>");
            System.out.println("<td> "+ rating.getItem() + " </td>");
            System.out.println("<td> " + MovieDatabase.getTitle(rating.getItem()) + " </td>");
            System.out.println("<td> " + MovieDatabase.getYear(rating.getItem()) + " </td>");
            System.out.println("<td> " + MovieDatabase.getGenres(rating.getItem()) + " </td>");
            System.out.println("<td> " + MovieDatabase.getMinutes(rating.getItem()) + " </td>");
            System.out.println("</tr>");
        }

        System.out.println("</table>");
        System.out.println("</p>");
        System.out.println("</body>");
        System.out.println("</html>");



















    }
}
