package com.example.carapp.Controller;

import android.content.Context;

import com.example.carapp.Controller.CallBacks.InsertUserCallBack;
import com.example.carapp.Database.VolleyCallBack;
import com.example.carapp.Model.CarModel;
import com.example.carapp.Model.UserModel;

import java.util.HashMap;

public class CarController {
    private CarModel carModel;

    public CarController(Context context) {
        this.carModel = new CarModel(context);
    }

    public void updateCar(HashMap<String, String> data, int userID, final VolleyCallBack updateCallBack){
        HashMap<String, String> conditions = new HashMap<>();
        conditions.put("userID", String.valueOf(userID));

        carModel.updateCar(conditions, data, new VolleyCallBack()   {
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

    public void insertCar(String serialNum, String modelName, int userID, final VolleyCallBack insertCallBack){

        HashMap<String, String> con = new HashMap<>();
        con.put("SerialNum", serialNum);
        con.put("ModelName", modelName);
        con.put("userID", String.valueOf(userID));

        carModel.insertCar(con, new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {
                insertCallBack.onSuccess(result);
            }

            @Override
            public void onError(String error) {
                insertCallBack.onError(error);
            }
        });





    }

}
