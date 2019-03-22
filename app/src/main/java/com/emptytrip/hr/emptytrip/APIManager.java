package com.emptytrip.hr.emptytrip;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Ruby on 6/17/2017.
 */

public class APIManager {

    interface MyCallBackInterface {
        void onSuccess(JSONArray result);
        void onSuccess(JSONObject result);
        void onFailure(String error, int nCode);
    }

//    static final String BASEURL = "https://emptytrip.com/sandbox/api/user/v1/";
    static final String BASEURL = "https://emptytrip.com/api/user/v1/";


    private static APIManager instance;
    OkHttpClient client;
    private APIManager() { client = new OkHttpClient();}

    public static APIManager getInstance() {
        if (instance == null) {
            instance = new APIManager();
        }
        return instance;
    }

    public void UserSignIn(RequestBody parameters, final MyCallBackInterface callback){
        String strURL = BASEURL + "signin/";

        Request request = new Request.Builder()
                .url(strURL)
                .post(parameters)

                .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        callback.onFailure("Failed", 500);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                int code = response.code();
                try {

                    JSONObject object = new JSONObject(jsonData);
//                    JSONArray array = new JSONArray();
//                    array.put(object);
                    if(code == 200){
                        callback.onSuccess(object);
                    }
                    else {
                        callback.onFailure(object.getString("errors"), code);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onFailure("Network Error", code);
                }
            }
        });

    }

    public void UserSignUp(RequestBody parameters, final MyCallBackInterface callback) {
        String strURL = BASEURL + "signup/";

        Request request = new Request.Builder()
                .url(strURL)
                .post(parameters)

                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("Failed", 500);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                int code = response.code();
                try {

                    JSONObject object = new JSONObject(jsonData);
//                    JSONArray array = new JSONArray();
//                    array.put(object);
                    if (code == 200) {
                        callback.onSuccess(object);
                    } else {
                        callback.onFailure(object.getString("errors"), code);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onFailure("Network Error", code);
                }
            }
        });
    }

    public void UserSignUpComplete(RequestBody parameters, final MyCallBackInterface callback) {
        String strURL = BASEURL + "signup-complete/";

        Request request = new Request.Builder()
                .url(strURL)
                .post(parameters)

                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("Failed", 500);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                int code = response.code();
                try {

                    JSONObject object = new JSONObject(jsonData);
//                    JSONArray array = new JSONArray();
//                    array.put(object);
                    if (code == 200) {
                        callback.onSuccess(object);
                    } else {
                        callback.onFailure(object.getString("errors"), code);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onFailure("Network Error", code);
                }
            }
        });
    }
    public void UserVerify(RequestBody parameters, final MyCallBackInterface callback){
        String strURL = BASEURL + "verify-code/";

        Request request = new Request.Builder()
                .url(strURL)
                .post(parameters)

                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("Failed", 500);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                int code = response.code();
                try {

                    JSONObject object = new JSONObject(jsonData);
//                    JSONArray array = new JSONArray();
//                    array.put(object);
                    if(code == 200){
                        callback.onSuccess(object);
                    }
                    else {
                        callback.onFailure(object.getString("errors"), code);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onFailure("Network Error", code);
                }
            }
        });

    }

    public void AppLaunch(RequestBody parameters, final MyCallBackInterface callback){
        String strURL = BASEURL + "launch/";

        Request request = new Request.Builder()
                .url(strURL)
                .post(parameters)

                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("Failed", 500);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                int code = response.code();
                try {

                    JSONObject object = new JSONObject(jsonData);
//                    JSONArray array = new JSONArray();
//                    array.put(object);
                    if(code == 200){
                        callback.onSuccess(object);
                    }
                    else {
                        callback.onFailure(object.getString("errors"), code);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onFailure("Network Error", code);
                }
            }
        });

    }
    public void protected_contacts(RequestBody parameters, final MyCallBackInterface callback){
        String strURL = BASEURL + "protect-contacts/";

        Request request = new Request.Builder()
                .url(strURL)
                .post(parameters)

                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("Failed", 500);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                int code = response.code();
                try {

                    JSONObject object = new JSONObject(jsonData);
//                    JSONArray array = new JSONArray();
//                    array.put(object);
                    if(code == 200){
                        callback.onSuccess(object);
                    }
                    else {
                        callback.onFailure(object.getString("errors"), code);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onFailure("Network Error", code);
                }
            }
        });

    }

    public void forgotPasswordRequestAPI(RequestBody parameters, final MyCallBackInterface callback){
        String strURL = BASEURL + "forgot-password/";

        Request request = new Request.Builder()
                .url(strURL)
                .post(parameters)

                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("Failed", 500);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                int code = response.code();
                try {

                    JSONObject object = new JSONObject(jsonData);
//                    JSONArray array = new JSONArray();
//                    array.put(object);
                    if(code == 200){
                        callback.onSuccess(object);
                    }
                    else {
                        callback.onFailure(object.getString("errors"), code);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onFailure("Network Error", code);
                }
            }
        });

    }

    public void forgotPasswordCodeCompareAPI(RequestBody parameters, final MyCallBackInterface callback){
        String strURL = BASEURL + "password-code/";

        Request request = new Request.Builder()
                .url(strURL)
                .post(parameters)

                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("Failed", 500);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                int code = response.code();
                try {

                    JSONObject object = new JSONObject(jsonData);
//                    JSONArray array = new JSONArray();
//                    array.put(object);
                    if(code == 200){
                        callback.onSuccess(object);
                    }
                    else {
                        callback.onFailure(object.getString("errors"), code);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onFailure("Network Error", code);
                }
            }
        });

    }

    public void forgotPasswordsetPasswordAPI(RequestBody parameters, final MyCallBackInterface callback){
        String strURL = BASEURL + "password-reset/";

        Request request = new Request.Builder()
                .url(strURL)
                .post(parameters)

                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("Failed", 500);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                int code = response.code();
                try {

                    JSONObject object = new JSONObject(jsonData);
//                    JSONArray array = new JSONArray();
//                    array.put(object);
                    if(code == 200){
                        callback.onSuccess(object);
                    }
                    else {
                        callback.onFailure(object.getString("errors"), code);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onFailure("Network Error", code);
                }
            }
        });

    }









    public void device_token(RequestBody parameters, final MyCallBackInterface callback){
        String strURL = BASEURL + "device-token/";

        Request request = new Request.Builder()
                .url(strURL)
                .post(parameters)

                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("Failed", 500);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                int code = response.code();
                try {

                    JSONObject object = new JSONObject(jsonData);
//                    JSONArray array = new JSONArray();
//                    array.put(object);
                    if(code == 200){
                        callback.onSuccess(object);
                    }
                    else {
                        callback.onFailure(object.getString("errors"), code);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onFailure("Network Error", code);
                }
            }
        });

    }

    public void ContentView(final String contactURL,final MyCallBackInterface callback){

        String strURL = String.format("%s",contactURL);
        Request request = new Request.Builder()
                .url(strURL)
                .get()

                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure("Failed", 500);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                int code = response.code();
                try {

                    JSONObject object = new JSONObject(jsonData);
                    JSONArray array = object.getJSONArray("results");
                    if(code == 200){
                        callback.onSuccess(array);
                    }
                    else {
                        callback.onFailure(object.getString("errors"), code);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onFailure("Network Error", code);
                }
            }
        });

    }
}
