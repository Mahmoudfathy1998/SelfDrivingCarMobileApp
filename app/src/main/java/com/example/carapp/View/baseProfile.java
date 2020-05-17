package com.example.carapp.View;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carapp.Controller.UserController;
import com.example.carapp.Database.VolleyCallBack;
import com.example.carapp.Entites.User;
import com.example.carapp.R;


import java.util.HashMap;

public abstract class baseProfile extends AppCompatActivity implements View.OnClickListener{
    private EditText firstNameET, lastNameET, emailET, passET;
    private TextView passwordTV;
    private Button saveBTN;
    public HashMap<String, String> userHashmap = new HashMap<>();
    protected Context context;
    private UserController userController;
    protected User user;

    protected void setBasicSettings(){ //btgm3 functions ytndo m3a b3d

        userController = new UserController(context);
        initAllViews();
    }
    protected abstract void initViews();
    private void initAllViews() {
        firstNameET = findViewById(R.id.userFirstname);
        lastNameET = findViewById(R.id.userLasttname);
        emailET = findViewById(R.id.userEmail);
        passET = findViewById(R.id.userPassword);
        saveBTN = findViewById(R.id.button_save);
        saveBTN.setOnClickListener(this);


        initViews();
        displayUserProfileData();
    }


    protected abstract void validateChange();
    private void validateMainChange() {
        firstNameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                userHashmap.put("Firstname", firstNameET.getText().toString());
            }
        });

        lastNameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                userHashmap.put("Lastname", lastNameET.getText().toString());
            }
        });

        emailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                userHashmap.put("Email", emailET.getText().toString());
            }
        });

        passET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                userHashmap.put("Password", passET.getText().toString());
            }
        });

        validateChange();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_save){
            updateUserProfileData();
        }
    }


    public abstract void displayProfileData();
    protected void displayUserProfileData(){
        firstNameET.setText(user.getFirstName());
        lastNameET.setText(user.getLastName());
        emailET.setText(user.getEmail());
        passET.setText(user.getPassword());
        validateMainChange();
    }

    public abstract void updateProfileData();
    private void updateUserProfileData(){
        if(!userHashmap.isEmpty()){
           // System.out.println(userHashmap.size());
            userController.updateUser(userHashmap, user.getId(), new VolleyCallBack() {
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

        updateProfileData();


    }

}
