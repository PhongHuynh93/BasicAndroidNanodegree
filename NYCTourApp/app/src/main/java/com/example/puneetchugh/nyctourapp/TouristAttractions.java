package com.example.puneetchugh.nyctourapp;

import android.media.Image;

/**
 * Created by puneetchugh on 6/28/16.
 */
public class TouristAttractions {
    private String name;
    private String location;
    private String timings;
    private String description;
    private int image;

    public TouristAttractions(String name, String location, String timings, String description, int image){
        this.name = name;
        this.location = location;
        this.timings = timings;
        this.description = description;
        this.image =image;
    }

    public String getName(){
        return name;
    }

    public String getLocation(){
        return location;
    }

    public String getTimings(){
        return timings;
    }

    public String getDescription(){
        return description;
    }

    public int getImage(){
        return image;
    }
}
