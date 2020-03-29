package com.example.carapp.Model;

import android.content.Context;

import com.example.carapp.Controller.CallBacks.SelectCarCallBack;
import com.example.carapp.Controller.CallBacks.SelectUsertypeCallBack;
import com.example.carapp.Database.RequestApi;
import com.example.carapp.Database.VolleyCallBack;
import com.example.carapp.Entites.Car;
import com.example.carapp.Entites.User;
import com.example.carapp.Entites.UserType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class CarModel {


    RequestApi requestApi;

    public CarModel(Context context) {
        this.requestApi = new RequestApi(context);
    }

    public void selectCar(Map<String, String> con, final SelectCarCallBack selectCarCallBack){
        requestApi.selectApi(new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject obj = new JSONObject(result);
                    // error, meessage , array

                    if(obj.getBoolean("error")){
                        selectCarCallBack.onFailer(obj.getString("message"));
                    }
                    else{
                        JSONArray arr = new JSONArray(obj.getJSONArray("car")); // array of json object
                        JSONObject userObject = (JSONObject) arr.get(0);

                        Car car = new Car();
                        car.setId(userObject.getInt("id"));
                        car.setSerialNumber(userObject.getString("SerialNumber"));
                       // car.setAnomaly();

                       /* Map<String, String> conUT = null;
                        conUT.put("ID", String.valueOf(userObject.getInt("UsertypeID")));
                        usertypeModel.selectUsertype(conUT, new SelectUsertypeCallBack() {
                            @Override
                            public void onSuccess(UserType userType) {
                                user.setUsertype(userType);

                                // car

                                loginCallBack.onSuccess(user);
                            }

                            @Override
                            public void onFailer(String error) {
                                loginCallBack.onFailer(error);
                            }
                        });*/



                        selectCarCallBack.onSuccess(car);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable throwable) {
                selectCarCallBack.onFailer(throwable.getMessage());
            }
        }, "car", con);
    }
}

