package com.example.carapp.Controller.CallBacks;

public interface InsertUserCallBack {

    void onSuccess(String success, int userID);
    void onFailer(String error);

}
