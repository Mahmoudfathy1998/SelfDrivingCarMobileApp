package com.example.carapp.Controller.CallBacks;

import com.example.carapp.Entites.Report;

import java.util.ArrayList;

public interface SelectReportCallBack {
    void onSuccess(ArrayList<Report> userArrayList);
    void onfailure(String error);
}
