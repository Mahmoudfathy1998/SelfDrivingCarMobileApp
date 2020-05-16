package com.example.carapp.Controller;

import android.content.Context;

import com.example.carapp.Controller.CallBacks.SelectReportCallBack;
import com.example.carapp.Entites.Report;
import com.example.carapp.Model.ReportsModel;

import java.util.ArrayList;
import java.util.HashMap;

public class ReportsController {
    private ReportsModel reportsModel;


    public ReportsController(Context context) {
        this.reportsModel = new ReportsModel(context);
    }

    public void selectReports(String userID,final SelectReportCallBack selectReport) {

        HashMap<String, String> conditions = new HashMap<>();
        conditions.put("UserID", userID);

        ReportsModel.selectReports(conditions, new SelectReportCallBack() {
            @Override
            public void onSuccess(ArrayList<Report> arrayList) {
                selectReport.onSuccess(arrayList);
                System.out.println("Controller Done");
            }

            @Override
            public void onfailure(String reason) {
                selectReport.onfailure(reason);
                System.out.println("Controller Error");

            }
        });
    }

}
