package com.example.carapp.Model;

import android.content.Context;

import com.example.carapp.Controller.CallBacks.SelectReportCallBack;
import com.example.carapp.Database.RequestApi;
import com.example.carapp.Database.VolleyCallBack;
import com.example.carapp.Entites.Report;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ReportsModel {
    static RequestApi requestApi;
    CarModel carmodel;
    private Context context;

    public ReportsModel(Context context) {
        this.context = context;
        this.requestApi = new RequestApi(context);
        this.carmodel  = new CarModel(context);
    }

    public static void selectReports(HashMap<String, String> con, final SelectReportCallBack selectReportCallBack){

        requestApi.selectReportsApi(new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                try {
                    JSONObject obj = new JSONObject(result);
                    // error, meessage , array

                    if(obj.getBoolean("error")){
                        selectReportCallBack.onfailure(obj.getString("message"));
                    }
                    else{
                        JSONArray arr = obj.getJSONArray("reports"); // array of json object
                        final JSONObject reportObject = (JSONObject) arr.get(0);

                        final Report report = new Report();
                        report.setID(reportObject.getInt("ID"));
                        report.setNumofanomalies(reportObject.getInt("Numofanomalies"));
                        report.setTripstart(reportObject.getString("Tripstart"));
                        report.setTripend(reportObject.getString("Tripend"));
                        report.setDate(reportObject.getString("Date"));

                        // usertype object .. car
                        /*final HashMap<String, Integer> conUT = new HashMap<>();
                        conUT.put("ID", Integer.valueOf(reportObject.getInt("UsertypeID")));
                        usertypeModel.selectUsertype(conUT, new SelectReportCallBack() {
                            @Override
                            public void onSuccess(UserType userType) {
                                user.setUsertype(userType);
                                loginCallBack.onSuccess(user);
                            }

                            @Override
                            public void onFailure(String error) {

                                loginCallBack.onFailure(error);
                            }
                        });*/

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                selectReportCallBack.onfailure(error);
            }
        }, "reports", con);

    }
}







