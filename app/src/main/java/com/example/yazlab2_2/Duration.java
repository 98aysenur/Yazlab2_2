package com.example.yazlab2_2;

import java.util.ArrayList;

public class Duration {
    private String longitude;
    private String latitude;
    static ArrayList <String> durations;

    public Duration() {
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public ArrayList<String> getDurations() {

        return durations;
    }

    public void setDurations(ArrayList<String> durations) {
        this.durations = durations;
    }
}
