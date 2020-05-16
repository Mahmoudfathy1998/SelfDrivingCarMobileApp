package com.example.carapp.Controller;

import android.content.Context;
import android.util.Log;

import com.example.carapp.Controller.CallBacks.InsertUserCallBack;
import com.example.carapp.Controller.CallBacks.LoginCallBack;
import com.example.carapp.Controller.CallBacks.SelectUsersListCallBack;
import com.example.carapp.Database.VolleyCallBack;
import com.example.carapp.Entites.User;
import com.example.carapp.Model.UserModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UserController {

    private UserModel userModel;
    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnmABCDEFGHIJKLMNOPQRSTUVWXYZ@#$%&*_-.";

    public UserController(Context context) {
        this.userModel = new UserModel(context);
    }

    public void userLogin(String email, String pass, final LoginCallBack loginCallBack){ // call interface C - V

        HashMap<String, String> con = new HashMap<>(); // 3shan a7ot el haga fe key(Name) w value(sara) -- quary automatic created
        con.put("email", email);
        con.put("password", pass);
        con.put("isdeleted", "0");

        // select * from Users where ""email"" = email

        userModel.selectSingleUser(con, new LoginCallBack() { // imp interface ben M - C
            @Override
            public void onSuccess(User user) {
                loginCallBack.onSuccess(user);
            }

            @Override
            public void onFailer(String error) {
                loginCallBack.onFailer(error);
            }
        });

    }

    public void insertUser(String firstName, String lastName, String email, final InsertUserCallBack insertCallBack){

        HashMap<String, String> con = new HashMap<>();
        con.put("Firstname", firstName);
        con.put("Lastname", lastName);
        con.put("Email", email);
        con.put("Password", generatePassword(10)); // auto generated later
        con.put("UsertypeID", "2");

        userModel.insertUser(con, new InsertUserCallBack() {
            @Override
            public void onSuccess(String result, int id) {
                insertCallBack.onSuccess(result, id);
            }

            @Override
            public void onFailer(String error) {
                insertCallBack.onFailer(error);
            }
        });

    }

    private static String generatePassword(final int passwordSize){
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(passwordSize);

        for(int i=0;i<passwordSize;++i){
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }
        return sb.toString();
    }

    public void updateUser(HashMap<String, String>data, int Userid, final VolleyCallBack updateCallBack){
        HashMap<String, String> conditions = new HashMap<>();
        conditions.put("id", String.valueOf(Userid));

        userModel.updateUser(conditions, data, new VolleyCallBack()   {
            @Override
            public void onSuccess(String success) {
                updateCallBack.onSuccess(success);
            }

            @Override
            public void onError(String reason) {
                updateCallBack.onError(reason);
            }
        });
    }

    public void selectUserList(final SelectUsersListCallBack selectUsersListCallBack){
        HashMap<String, String> conditions = new HashMap<>();
        conditions.put("Isdeleted","0");
        conditions.put("UsertypeID", "2");

        userModel.selectUsersList(conditions, new SelectUsersListCallBack()   {
            @Override
            public void onSuccess(ArrayList<User> arrayList) {
                selectUsersListCallBack.onSuccess(arrayList);
            }

            @Override
            public void onError(String reason) {
                selectUsersListCallBack.onError(reason);
            }
        });
    }
}
