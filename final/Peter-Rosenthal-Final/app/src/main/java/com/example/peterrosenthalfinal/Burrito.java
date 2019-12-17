package com.example.peterrosenthalfinal;

import java.util.ArrayList;

public class Burrito {
    private ArrayList<String> locations;
    private ArrayList<String> restaurants;
    private ArrayList<String> websites;

    public Burrito() {
        locations = new ArrayList<>();
        locations.add("The Hill");
        locations.add("29th street");
        locations.add("Pearl street");

        restaurants = new ArrayList<>();
        restaurants.add("Illegal Pete's");
        restaurants.add("Chipotle");
        restaurants.add("Bartaco");

        websites = new ArrayList<>();
        websites.add("http://illegalpetes.com/");
        websites.add("https://www.chipotle.com/");
        websites.add("https://bartaco.com/");
    }

    public String getRestaurantByNumber(int i) {
        String restaurant = restaurants.get(i);
        String location = locations.get(i);
        String restaurantLocation = restaurant + " on " + location;
        return restaurantLocation;
    }

    public String getURLByNumber(int i) {
        return websites.get(i);
    }
}
