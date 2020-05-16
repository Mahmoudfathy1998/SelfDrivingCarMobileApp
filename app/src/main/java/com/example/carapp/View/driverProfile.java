package com.example.carapp.View;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carapp.Controller.CarController;
import com.example.carapp.Controller.UserController;
import com.example.carapp.Database.VolleyCallBack;
import com.example.carapp.Entites.Car;
import com.example.carapp.Entites.User;
import com.example.carapp.R;
import com.example.carapp.View.baseProfile;

import java.util.HashMap;

public class driverProfile extends baseProfile {

    EditText serialNumET, modelLabelET;
    public HashMap<String, String> carHashmap = new HashMap<>();
    CarController carController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profile);
        this.context=getApplicationContext();
        carController = new CarController(getApplicationContext());

        this.user = (User) getIntent().getSerializableExtra("User");

        setBasicSettings();
        displayProfileData();
    }

    @Override
    protected void initViews() {
        serialNumET = findViewById(R.id.carSerialNum);
        modelLabelET = findViewById(R.id.Modelname);
    }

    @Override
    protected void validateChange() {
        serialNumET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                carHashmap.put("SerialNum", serialNumET.getText().toString());
            }
        });

        modelLabelET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                carHashmap.put("ModelName", modelLabelET.getText().toString());
            }
        });
    }

    @Override
    public void displayProfileData() {
        serialNumET.setText(user.getCar().getSerialNumber());
        modelLabelET.setText(user.getCar().getModelName());
    }

    @Override
    public void updateProfileData() {

        System.out.println(carHashmap.size());

        if(!carHashmap.isEmpty()){

            System.out.println(user.getCar().getId());

            carController.updateCar(carHashmap, user.getId(), new VolleyCallBack() {
                @Override
                public void onSuccess(String success) {
                    Toast.makeText(context, success, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(String reason) {
                    Toast.makeText(context, reason, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
