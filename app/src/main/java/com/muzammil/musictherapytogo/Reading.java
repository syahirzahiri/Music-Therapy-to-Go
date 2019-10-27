package com.muzammil.musictherapytogo;

public class Reading {

    private String initial_reading;
    private String final_reading;

    public Reading() {
    }

    public Reading(String initial_reading, String final_reading) {
        this.initial_reading = initial_reading;
        this.final_reading = final_reading;
    }

    public String getInitial_reading() {
        return initial_reading;
    }

    public void setInitial_reading(String initial_reading) {
        this.initial_reading = initial_reading;
    }

    public String getFinal_reading() {
        return final_reading;
    }

    public void setFinal_reading(String final_reading) {
        this.final_reading = final_reading;
    }
}
