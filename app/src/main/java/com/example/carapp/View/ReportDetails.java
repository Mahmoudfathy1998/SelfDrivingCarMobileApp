package com.example.carapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.carapp.Entites.Report;
import com.example.carapp.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.Serializable;
import java.util.ArrayList;

public class ReportDetails extends AppCompatActivity  {
    protected Report report;
    PieChart pieChart;
    TextView anomalies, date, start, end,aword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_details);
        report = (Report) getIntent().getSerializableExtra("Report");
        findView();

        drawChart();




    }

    private void findView(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.fadein);
        pieChart = findViewById(R.id.chart1);
        anomalies = findViewById(R.id.anomalies);
        date = findViewById(R.id.date);
        start = findViewById(R.id.starttrip);
        end = findViewById(R.id.endtrip);
        aword = findViewById(R.id.anomalyword);

        anomalies.setText(String.valueOf(report.getNumofanomalies()));
        date.setText("Trip Date                            "+report.getDate());
        start.setText("Start Time                          "+report.getTripstart());
        end.setText("End Time                            "+report.getTripend());
        anomalies.startAnimation(animation);
        date.startAnimation(animation);
        start.startAnimation(animation);
        end.startAnimation(animation);
        aword.startAnimation(animation);

    }
    private void drawChart(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(40,""));
        entries.add(new PieEntry(40,""));
        //entries.add(new PieEntry(30,""));
        entries.add(new PieEntry(40,""));


        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.99f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.setTransparentCircleRadius(65f);
        pieChart.animateY(2000, Easing.EaseInOutCubic );
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);;
        PieDataSet dataSet = new PieDataSet(entries,"");
        //dataSet.setSliceSpace(1f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setDrawValues(false);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
    }

}
