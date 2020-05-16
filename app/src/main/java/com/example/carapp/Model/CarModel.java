package com.example.carapp.Model;

import android.content.Context;

import com.example.carapp.Controller.CallBacks.SelectCarCallBack;
import com.example.carapp.Database.RequestApi;
import com.example.carapp.Database.VolleyCallBack;
import com.example.carapp.Entites.Car;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class CarModel {

    RequestApi requestApi;

    public CarModel(Context context) {
        this.requestApi = new RequestApi(context);
    }

    public void selectCar(HashMap<String, String> con, final SelectCarCallBack selectCarCallBack){
        requestApi.selectApi(new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                try {
                    JSONObject obj = new JSONObject(result);
                    // error, meessage , array

                    if(obj.getBoolean("error")){
                        selectCarCallBack.onFailer(obj.getString("message"));
                    }
                    else{
                        JSONArray arr = obj.getJSONArray("car"); // array of json object
                        JSONObject userObject = (JSONObject) arr.get(0);

                        Car car = new Car();
                        car.setId(userObject.getInt("ID"));
                        car.setSerialNumber(userObject.getString("SerialNum"));
                        car.setModelName(userObject.getString("ModelName"));

                        //getAllAnomalies(car, selectCarCallBack);

                        selectCarCallBack.onSuccess(car);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                selectCarCallBack.onFailer(error);
            }
        }, "car", con);
    }

    public void updateCar(HashMap<String, String> con, HashMap<String, String> data,final VolleyCallBack updateCallBack ){
        requestApi.updateApi(new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject object = new JSONObject(result);

                    if(object.getBoolean("error")){
                        updateCallBack.onError(object.getString("message"));
                    }else{
                        updateCallBack.onSuccess(object.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String reason) {
                updateCallBack.onError(reason);
            }
        },"car",data,con);
    }

    public void insertCar( HashMap<String, String> data,final VolleyCallBack insertCallBack){

        requestApi.insertApi(new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {


                try {
                    JSONObject object = new JSONObject(result);


                    if (object.getBoolean("error")) {
                        insertCallBack.onError(object.getString("message")); //check 3al db
                    } else {
                        insertCallBack.onSuccess(result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error)  {
                insertCallBack.onError(error);

            }
        }, "car", data);


    }
}


