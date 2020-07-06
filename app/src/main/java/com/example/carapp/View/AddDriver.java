package com.example.carapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carapp.Controller.CallBacks.InsertUserCallBack;
import com.example.carapp.Controller.CarController;
import com.example.carapp.Controller.UserController;
import com.example.carapp.Database.VolleyCallBack;
import com.example.carapp.R;

public class AddDriver extends AppCompatActivity {
    private EditText driverFN, driverLN, driverEmail,carserialnumET, carmodelET;
    private Button addBTN;

    private UserController userController;
    private CarController carController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);

        userController = new UserController(this);
        carController = new CarController(this);

        driverFN = (EditText) findViewById(R.id.driverFirstname);
        driverLN = (EditText) findViewById(R.id.driverLasttname);
        driverEmail = (EditText) findViewById(R.id.driverEmail);
        carserialnumET = (EditText) findViewById(R.id.carSerialNum);
        carmodelET = (EditText) findViewById(R.id.carModel);


        addBTN = findViewById(R.id.addBTN);

        //validation = true
        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDriver(driverFN.getText().toString(), driverLN.getText().toString(), driverEmail.getText().toString(), carserialnumET.getText().toString(), carmodelET.getText().toString());
            }
        });

    }

    private void addDriver(String firstName, String lastName, String email, final String serialNum, final String modelName){

        userController.insertUser(firstName, lastName, email, new InsertUserCallBack()
        {
            @Override
            public void onSuccess(String result, int id) {


                carController.insertCar(serialNum, modelName, id, new VolleyCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getApplicationContext(), "Driver Added Successfully!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                    }
                });

            }

            @Override
            public void onFailer(String error) {
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
