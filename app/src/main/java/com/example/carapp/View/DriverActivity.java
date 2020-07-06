package com.example.carapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.carapp.Entites.User;
import com.example.carapp.R;
import com.example.carapp.bumps;

import java.io.Serializable;

public class DriverActivity extends AppCompatActivity {
    private Button bumpsBTN, distanceBTN,profileBTN, reportsBTN;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.user = (User) getIntent().getSerializableExtra("User");
        setContentView(R.layout.activity_driver);

        reportsBTN = findViewById(R.id.reports);

        profileBTN =  findViewById(R.id.profileBTN);


        reportsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReports();
            }
        });
        profileBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });
    }
    public void openbumps(){
        Intent intent = new Intent(this, bumps.class);
        startActivity(intent);
    }
    public void opendistance(){
        Intent intent = new Intent(this, bumps.class);
        startActivity(intent);
    }
    public void openProfile(){
        Intent intent = new Intent(this, driverProfile.class);
        intent.putExtra("User", (Serializable) this.user);
        startActivity(intent);
    }
    public void openReports(){
        Integer userID = this.user.getId();
        String UserID = Integer.toString(userID);
        System.out.println("HE5O ID = "+ UserID);
        Intent intent = new Intent(this, Reports.class);
        intent.putExtra("UserID", UserID );
        startActivity(intent);
    }

    }

