package com.example.carapp.Entites;

import java.io.Serializable;

public class Report implements Serializable {
private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTripstart() {
        return Tripstart;
    }

    public void setTripstart(String tripstart) {
        Tripstart = tripstart;
    }

    public String getTripend() {
        return Tripend;
    }

    public void setTripend(String tripend) {
        Tripend = tripend;
    }

    public int getNumofanomalies() {
        return Numofanomalies;
    }

    public void setNumofanomalies(int numofanomalies) {
        Numofanomalies = numofanomalies;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    private String Tripstart;
private String Tripend;
private int Numofanomalies;
private String Date;

}
