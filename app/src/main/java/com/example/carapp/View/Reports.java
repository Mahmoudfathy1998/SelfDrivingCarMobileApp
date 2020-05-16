package com.example.carapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.example.carapp.Controller.CallBacks.SelectReportCallBack;
import com.example.carapp.Controller.ReportsController;
import com.example.carapp.Entites.Report;
import com.example.carapp.Entites.User;
import com.example.carapp.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Reports extends AppCompatActivity {
    private String userID;
    private ReportsController reportsController;
    RecyclerView recyclerView;
    ReportsRecyclerViewAdapter ReportsRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        reportsController = new ReportsController(this);
        userID = intent.getStringExtra("UserID" );


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        recyclerView = findViewById(R.id.recyclerView);

        showReports(userID);

    }


    public void showReports(String UserID){

        reportsController.selectReports(UserID, new SelectReportCallBack() {
            @Override
            public void onSuccess(ArrayList<Report> arrayList) {
                System.out.println(" Reports Retrieved to view.");
                ReportsRecyclerViewAdapter = new ReportsRecyclerViewAdapter(arrayList, getApplicationContext());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(ReportsRecyclerViewAdapter);

            }

            @Override
            public void onfailure(String error) {
                System.out.println("Failed To Retrieve reports...");

            }
        });

    }
}