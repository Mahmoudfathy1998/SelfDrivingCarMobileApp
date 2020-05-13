package com.example.carapp.View;

import androidx.appcompat.app.AppCompatActivity;

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
    HorizontalBarChart horizontalChart;
    private String userID;
    private ReportsController reportsController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        reportsController = new ReportsController(this);
        userID = intent.getStringExtra("UserID" );
        showReports(userID);
        //System.out.println("He5HE5O ID = "+ userID);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        horizontalChart = findViewById(R.id.chart1);
        BarDataSet barDataSet = new BarDataSet(getData(), "");
        barDataSet.setBarBorderWidth(0.7f);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.3f);
        XAxis xAxis = horizontalChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final String[] anomalies = new String[]{"AnomaliesNumber"};
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(anomalies);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
        horizontalChart.setData(barData);
        horizontalChart.setFitBars(true);
        horizontalChart.animateXY(3000, 3000);
        horizontalChart.invalidate();
    }

    private ArrayList getData(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 30f));
        return entries;
    }
    public void showReports(String UserID){

        reportsController.selectReports(UserID, new SelectReportCallBack() {
            @Override
            public void onSuccess(Report report) {
                System.out.println("Reports Retrieved.");
            }

            @Override
            public void onfailure(String error) {
                System.out.println("Failed To Retrieve reports...");

            }
        });

    }
}