package com.example.carapp.View;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carapp.Entites.Report;
import com.example.carapp.R;

import java.io.Serializable;
import java.util.ArrayList;

public class ReportsRecyclerViewAdapter extends RecyclerView.Adapter<ReportsRecyclerViewAdapter.Holder>  {
    private ArrayList<Report> reportArrayList;
    private Context context;
    private int i = 1;

    public ReportsRecyclerViewAdapter(ArrayList<Report> reportArrayList, Context context) {
        this.reportArrayList = reportArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reports_list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {

        holder.reportNameTextView.setText("Trip "+ i);
        holder.reportDateTextview.setText(reportArrayList.get(position).getDate());

        holder.reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, ReportDetails.class);
                intent1.putExtra("Report", reportArrayList.get(position));
                context.startActivity(intent1);
            }
        });

        i++;
    }

    @Override
    public int getItemCount() {
        return reportArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        private Button reportBtn;
        private TextView reportNameTextView, reportDateTextview;

        public Holder(View itemView) {
            super(itemView);

            reportBtn = itemView.findViewById(R.id.reportBtn);
            reportNameTextView = itemView.findViewById(R.id.tripnameTextView);
            reportDateTextview = itemView.findViewById(R.id.tripDateTextview);
        }
    }
}
