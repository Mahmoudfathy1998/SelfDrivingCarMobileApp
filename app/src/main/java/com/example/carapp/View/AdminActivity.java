package com.example.carapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.carapp.R;

public class AdminActivity extends AppCompatActivity {
    private Button profileBtn, driversBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        profileBtn = findViewById(R.id.adminprofileBtn);
        driversBtn = findViewById(R.id.driversBtn);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), adminProfile.class);
                intent1.putExtra("User", getIntent().getSerializableExtra("User"));
                startActivity(intent1);
            }
        });

        driversBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), DriverList.class);
                intent1.putExtra("User", getIntent().getSerializableExtra("User"));
                startActivity(intent1);
            }
        });




    }
}
