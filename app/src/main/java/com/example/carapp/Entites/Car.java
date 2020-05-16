package com.example.carapp.Entites;

import java.io.Serializable;
import java.util.ArrayList;

public class Car   implements Serializable {

    private int id;
    private String serialNumber;
    private String modelName;
    private ArrayList<Anomaly> anomalies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public ArrayList<Anomaly> getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(ArrayList<Anomaly> anomalies) {
        this.anomalies = anomalies;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
