package com.example.carapp.View;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carapp.Entites.User;
import com.example.carapp.R;

import java.sql.Driver;
import java.util.ArrayList;

public class DriverListRecyclerViewAdaptor extends RecyclerView.Adapter<DriverListRecyclerViewAdaptor.Holder> {

    private ArrayList<User> driverArrayList;
    private Context ctx;
    private int Adid;

    public DriverListRecyclerViewAdaptor(ArrayList<User> driverArrayList, Context ctx , int Adid) {
        this.driverArrayList = driverArrayList;
        this.ctx = ctx;
        this.Adid = Adid;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drivers_list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        holder.driverNameTextView.setText(driverArrayList.get(position).getFirstName() + " " + driverArrayList.get(position).getLastName());
        holder.driverEmailTextView.setText(driverArrayList.get(position).getEmail());

        holder.profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ctx, driverProfile.class);
                intent1.putExtra("User", driverArrayList.get(position));
                intent1.putExtra("Usertype" , Adid);
                ctx.startActivity(intent1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return driverArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        private Button profileBtn;
        private TextView driverNameTextView, driverEmailTextView;

        public Holder(View itemView){
            super(itemView);

            profileBtn = itemView.findViewById(R.id.profileBtn);
            driverNameTextView = itemView.findViewById(R.id.driverNameTextView);
            driverEmailTextView = itemView.findViewById(R.id.driverEmailTextView);
        }
    }
}
