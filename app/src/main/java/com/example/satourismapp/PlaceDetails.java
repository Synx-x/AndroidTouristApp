package com.example.satourismapp;

import java.util.Arrays;
import java.util.List;

public class PlaceDetails {

    String place;
    String nPlaces;
    String[] places;

    public String[] PlaceDetails(){

        database db;
        db = new database(PlaceDetails.this);

        db.open();
        db.getPlaceDetails();
        nPlaces = db.getPlaceDetails();

        List<String> convertedPlace = Arrays.asList(nPlaces.split("\\s*,\\s*"));;

        String [] array3 = new String[convertedPlace.size()];

        for(int i = 0; i < 9; i++){
            array3[i] = convertedPlace.get(i);
        }

        places = Arrays.copyOf(array3, 9, String[].class);

        place = Arrays.toString(places);

        db.close();

        return places;
    }

    public String[] getPlace() {
        return places;
    }

    public void setPlace(String[] place) {
        this.places = places;
    }
}
