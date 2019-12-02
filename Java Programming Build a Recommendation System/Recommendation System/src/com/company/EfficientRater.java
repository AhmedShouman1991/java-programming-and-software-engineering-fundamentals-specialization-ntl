package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        return myRatings.containsKey(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
//        for(int k=0; k < myRatings.size(); k++){
//            if (myRatings.get(k).getItem().equals(item)){
//                return myRatings.get(k).getValue();
//            }
//        }
        for (String movieID : myRatings.keySet()) {
            if (myRatings.get(movieID).getItem().equals(item)) {
                return myRatings.get(movieID).getValue();
            }
        }
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String id : myRatings.keySet()){
            list.add(myRatings.get(id).getItem());
        }
        return list;
    }
}
