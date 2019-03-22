package com.emptytrip.hr.emptytrip;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class CreatePasswordActivity extends Activity {

    String fullname;
    String verifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);


        Intent intent = getIntent();
        fullname = intent.getExtras().getString("fullname");
        verifyCode = intent.getExtras().getString("verifyCode");

        // Font initialize

        Typeface Gotham_Medium = Typeface.createFromAsset(getAssets(),"fonts/Gotham-Medium.otf");
        Typeface roboto_Regular = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Regular.ttf");
        Typeface roboto_Medium = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
        Typeface roboto_Black = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Black.ttf");
        Typeface roboto_Bold = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");

        // Button initialize

        TextView tv_logo1 = (TextView)findViewById(R.id.tv_logo1);
        TextView tv_logo2 = (TextView)findViewById(R.id.tv_logo2);
        TextView tv_main = (TextView)findViewById(R.id.tv_main);

        TextView tv_password = (TextView)findViewById(R.id.tv_password);
        final EditText et_repassword = (EditText)findViewById(R.id.et_repassword);
        final EditText et_password = (EditText)findViewById(R.id.et_password);

        Button btn_Done = (Button)findViewById(R.id.btn_Done);

        Button btn_back = (Button)findViewById(R.id.btn_back);

        // Set Font

        tv_logo1.setTypeface(Gotham_Medium);
        tv_logo2.setTypeface(Gotham_Medium);
        tv_main.setTypeface(roboto_Black);

        tv_password.setTypeface(roboto_Regular);
        et_repassword.setTypeface(roboto_Regular);
        et_password.setTypeface(roboto_Regular);

        btn_Done.setTypeface(roboto_Medium);


        // Functions

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final SweetAlertDialog pDialog = new SweetAlertDialog(CreatePasswordActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.setTitleText("Logging In...");
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.show();



                UserProfile user = EmptyTripApplication.getUserProfile();
                FormBody.Builder formBuilder = new FormBody.Builder()
                        .add("name", fullname)
                        .add("userId", String.valueOf(user.id))
                        .add("authToken", user.authToken)
                        .add("password", et_password.getText().toString())
                        .add("confirmPassword", et_repassword.getText().toString())
                        .add("verificationCode",verifyCode);
                RequestBody formBody = formBuilder.build();

                APIManager.getInstance().UserSignUpComplete(formBody, new APIManager.MyCallBackInterface() {
                    @Override
                    public void onSuccess(JSONArray result) {
                    }

                    @Override
                    public void onSuccess(JSONObject result) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pDialog.hide();
                            }
                        });
                        try {
                            //pDialog.hide();

                            if(result.has("id")){

                                UserProfile user = new UserProfile();

                                user.id = result.getInt("id");
                                user.name = result.getString("name");
                                user.email = result.getString("email");
                                user.phone = result.getString("phone");
                                user.profileImage = result.getString("profileImage");
                                user.inviteCode = result.getString("inviteCode");
                                user.authToken = result.getString("authToken");
                                user.status = result.getInt("status");
                                user.verified = result.getInt("verified");
                                user.dateCreated = result.getString("dateCreated");
                                user.dateUpdated = result.getString("dateUpdated");

                                EmptyTripApplication emptyTripApplication = new EmptyTripApplication();
                                emptyTripApplication.setUserProfile(user);

                                Intent intent = new Intent(CreatePasswordActivity.this,SignDoneActivity.class);
                                startActivity(intent);

                            }else{

                                String error = result.getString("errors");

                                JSONArray errorsArray = result.getJSONArray("errors");
                                JSONObject errorIndex0 = errorsArray.getJSONObject(0);
                                final String msg = errorIndex0.getString("message");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        pDialog.hide();
                                        new SweetAlertDialog(CreatePasswordActivity.this, SweetAlertDialog.ERROR_TYPE)
                                                .setTitleText("Oops...")
                                                .setContentText(msg)
                                                .show();
                                    }
                                });
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                    @Override
                    public void onFailure(final String error, int nCode) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pDialog.hide();
                                new SweetAlertDialog(CreatePasswordActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Oops...")
                                        .setContentText("Unable to sign up, try again!")
                                        .show();
                            }
                        });

                    }
                });
            }
        });
    }
}
