package com.example.carapp.Model;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.example.carapp.Controller.CallBacks.InsertUserCallBack;
import com.example.carapp.Controller.CallBacks.LoginCallBack;
import com.example.carapp.Controller.CallBacks.SelectCarCallBack;
import com.example.carapp.Controller.CallBacks.SelectUsersListCallBack;
import com.example.carapp.Controller.CallBacks.SelectUsertypeCallBack;
import com.example.carapp.Database.RequestApi;
import com.example.carapp.Database.VolleyCallBack;
import com.example.carapp.Entites.Car;
import com.example.carapp.Entites.User;
import com.example.carapp.Entites.UserType;
import com.example.carapp.SendMail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UserModel {

    RequestApi requestApi;
    UsertypeModel usertypeModel;
    CarModel carmodel;
    private Context context;

    public UserModel(Context context) {
        this.context = context;
        this.requestApi = new RequestApi(context);
        this.usertypeModel = new UsertypeModel(context);
        this.carmodel  = new CarModel(context);
    }

    public void selectSingleUser(HashMap<String, String> con, final LoginCallBack loginCallBack){

        requestApi.selectApi(new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    // error, meessage , array

                    if(obj.getBoolean("error")){
                        loginCallBack.onFailer(obj.getString("message"));
                    }
                    else{
                        JSONArray arr = obj.getJSONArray("users"); // array of json object
                        final JSONObject userObject = (JSONObject) arr.get(0);

                        final User user = new User();
                        user.setId(userObject.getInt("ID"));
                        user.setFirstName(userObject.getString("Firstname"));
                        user.setLastName(userObject.getString("Lastname"));
                        user.setEmail(userObject.getString("Email"));
                        user.setPassword(userObject.getString("Password"));

                        // usertype object .. car
                        final HashMap<String, String> conUT = new HashMap<>();
                        conUT.put("ID", String.valueOf(userObject.getInt("UsertypeID")));
                        usertypeModel.selectUsertype(conUT, new SelectUsertypeCallBack() {
                            @Override
                            public void onSuccess(UserType userType) {
                                user.setUsertype(userType);

                                if(userType.getId() == 2){
                                    try {
                                        final HashMap<String, String> userCar = new HashMap<>();
                                        userCar.put("userID", String.valueOf(userObject.getInt("ID")));

                                        carmodel.selectCar(userCar, new SelectCarCallBack() {
                                            @Override
                                            public void onSuccess(Car car) {
                                                user.setCar(car);
                                                loginCallBack.onSuccess(user);
                                            }

                                            @Override
                                            public void onFailer(String error) {
                                                loginCallBack.onFailer(error);
                                            }
                                        });
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    loginCallBack.onSuccess(user);
                                }


                            }

                            @Override
                            public void onFailer(String error) {

                                loginCallBack.onFailer(error);
                            }
                        });

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                loginCallBack.onFailer(error);
            }
        }, "users", con);

    }

    public void selectUsersList(final HashMap<String, String> con, final SelectUsersListCallBack selectUsersListCallBack){

        requestApi.selectApi(new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                try {
                    final ArrayList<User> userArrayList = new ArrayList<>();
                    JSONObject obj = new JSONObject(result);
                    // error, meessage , array

                    if(obj.getBoolean("error")){
                        selectUsersListCallBack.onError(obj.getString("message"));
                    }
                    else{
                        JSONArray arr = obj.getJSONArray("users"); // array of json object

                        for(int i =0; i < arr.length(); i++){
                            final JSONObject userObject = (JSONObject) arr.get(i);
                            final User user = new User();
                            user.setId(userObject.getInt("ID"));
                            user.setFirstName(userObject.getString("Firstname"));
                            user.setLastName(userObject.getString("Lastname"));
                            user.setEmail(userObject.getString("Email"));
                            user.setPassword(userObject.getString("Password"));

                            // usertype object .. car
                            final HashMap<String, String> conUT = new HashMap<>();
                            conUT.put("ID", String.valueOf(userObject.getInt("UsertypeID")));
                            usertypeModel.selectUsertype(conUT, new SelectUsertypeCallBack() {
                                @Override
                                public void onSuccess(UserType userType) {
                                    user.setUsertype(userType);


                                    try {
                                        final HashMap<String, String> userCar = new HashMap<>();
                                        userCar.put("userID", String.valueOf(userObject.getInt("ID")));

                                        carmodel.selectCar(userCar, new SelectCarCallBack() {
                                            @Override
                                            public void onSuccess(Car car) {
                                                user.setCar(car);
                                                userArrayList.add(user);
                                                selectUsersListCallBack.onSuccess(userArrayList);
                                            }

                                            @Override
                                            public void onFailer(String error) {
                                                selectUsersListCallBack.onError(error);
                                            }
                                        });
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }

                                @Override
                                public void onFailer(String error) {

                                    selectUsersListCallBack.onError(error);
                                }
                            });

                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                selectUsersListCallBack.onError(error);
            }
        }, "users", con);

    }

    public void insertUser(final HashMap<String, String> con, final InsertUserCallBack insertCallBack ){

        requestApi.insertApi(new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {

                System.out.println("Heyy "+result);

                try {
                    JSONObject object = new JSONObject(result);


                    if(object.getBoolean("error")){
                        insertCallBack.onFailer(object.getString("message")); //check 3al db
                        System.out.println("IM ERROR 7ODA");

                    }else{
                        System.out.println("IM CORRECT 7ODA");
                        System.out.println(con.get("Email"));

                        // System.out.println(object.toString());

                        String mail=con.get("Email");
                        String pass = con.get("Password");
                        String name = con.get("Firstname");
                        System.out.println(object.toString());
                        //System.out.println("IM HERE 7ODA");

                        SendMail Passmail = new SendMail(context, mail, "TestMail", "Welcome "+name+ ", Your password is '" +pass+"'");
                        Passmail.execute();
                        insertCallBack.onSuccess(object.getString("message"), object.getInt("id"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {
                insertCallBack.onFailer(error);//network,API
            }
        }, "users",con);

    }

    public void updateUser(HashMap<String, String> con, HashMap<String, String> data,final VolleyCallBack updateCallBack ){
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
        },"users",data,con);

    }

}
