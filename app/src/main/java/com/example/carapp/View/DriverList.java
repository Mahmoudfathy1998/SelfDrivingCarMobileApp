package com.example.carapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.carapp.Controller.CallBacks.SelectUsersListCallBack;
import com.example.carapp.Controller.UserController;
import com.example.carapp.Entites.User;
import com.example.carapp.R;

import java.util.ArrayList;

public class DriverList extends AppCompatActivity {

    Button addDriverBtn;
    RecyclerView recyclerView;
    DriverListRecyclerViewAdaptor driverListRecyclerViewAdaptor;
    UserController userController;
    User user;
    int Adid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user = (User) getIntent().getSerializableExtra("User");
        Adid = user.getUsertype().getId(); //=1 Admin

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_list);

        userController = new UserController(this);
        addDriverBtn = findViewById(R.id.addDriverBtn);

        addDriverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), AddDriver.class);
                startActivity(intent1);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        getDriver();

    }

    private void getDriver(){
        userController.selectUserList(new SelectUsersListCallBack() {
            @Override
            public void onSuccess(ArrayList<User> userArrayList) {

                driverListRecyclerViewAdaptor = new DriverListRecyclerViewAdaptor(userArrayList, getApplicationContext() , Adid);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(driverListRecyclerViewAdaptor);
            }

            @Override
            public void onError(String reason) {
                Toast.makeText(getApplicationContext(), reason, Toast.LENGTH_LONG).show();
            }
        });
    }
}
