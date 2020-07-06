package com.example.carapp.Database;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.HashMap;
import java.util.Map;

public class RequestApi {

    private Context context;

    public RequestApi(Context context) {
        this.context = context;
    }

    public void selectApi(final VolleyCallBack callback, final String tableName, HashMap<String, String> conditions) {

        Gson gson = new Gson();
        final String con = gson.toJson(conditions);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://selfdrivingcarserver.000webhostapp.com/select.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("GAMED YASTA!");

                callback.onSuccess(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        callback.onError(error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("table", tableName);
                param.put("conditions", con);

                return param;
            }
        };

        VolleySingleton.getnInstance(context).addRequestQue(stringRequest);
    }

    public void insertApi(final VolleyCallBack callback, final String tableName, HashMap<String, String> data) {

        Gson gson = new Gson();
        final String insert_data = gson.toJson(data);

        System.out.println(insert_data);


        StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://selfdrivingcarserver.000webhostapp.com/insert.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        callback.onError(error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("table", tableName);
                param.put("data", insert_data);

                System.out.println(param);

                return param;
            }
        };

        VolleySingleton.getnInstance(context).addRequestQue(stringRequest);
    }

    public void updateApi(final VolleyCallBack callback, final String tableName, Map<String, String> data, HashMap<String, String> conditions) {
        Gson gson = new Gson();
        final String update_data = gson.toJson(data);
        final String update_con = gson.toJson(conditions);


        StringRequest request = new StringRequest(Request.Method.POST, "https://selfdrivingcarserver.000webhostapp.com/update.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println(response);

                callback.onSuccess(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        callback.onError(error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("table", tableName);
                param.put("data", update_data);
                param.put("conditions", update_con);

                return param;
            }
        };

        VolleySingleton.getnInstance(context).addRequestQue(request);

    }

    public void selectReportsApi(final VolleyCallBack callback, final String tableName, HashMap<String, String> conditions) {

        Gson gson = new Gson();
        final String con = gson.toJson(conditions);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://selfdrivingcarserver.000webhostapp.com/selectreport.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
                //System.out.println("HH Positive Response");
                //System.out.println(response);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //System.out.println("HH Negative Response");

                        error.printStackTrace();
                        callback.onError(error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("table", tableName);
                param.put("conditions", con);

                return param;
            }
        };

        VolleySingleton.getnInstance(context).addRequestQue(stringRequest);
    }


}


