package com.emptytrip.hr.emptytrip;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class SignInActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

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
        TextView tv_mobile = (TextView)findViewById(R.id.tv_mobile);
        final CountryCodePicker ccp_phoneCode = (CountryCodePicker)findViewById(R.id.ccp_phoneCode);

        TextView tv_password = (TextView)findViewById(R.id.tv_password);
        final EditText et_password = (EditText)findViewById(R.id.et_password);
        Button btn_submit = (Button)findViewById(R.id.btn_submit);
        Button btn_forgotpassword = (Button)findViewById(R.id.btn_forgotpassword);
        TextView tv_textView = (TextView)findViewById(R.id.tv_textView);
        Button btn_signUp = (Button)findViewById(R.id.btn_signUp);

        Button btn_back = (Button)findViewById(R.id.btn_back);


        //

        final EditText et_mobilenumber = (EditText)findViewById(R.id.et_mobilenumber);


        // Set Font

        tv_logo1.setTypeface(Gotham_Medium);
        tv_logo2.setTypeface(Gotham_Medium);

        tv_main.setTypeface(roboto_Black);
        tv_mobile.setTypeface(roboto_Regular);
        tv_password.setTypeface(roboto_Regular);
        et_password.setTypeface(roboto_Regular);
        tv_textView.setTypeface(roboto_Bold);

        ccp_phoneCode.setCountryForNameCode("RU");
        ccp_phoneCode.setTypeFace(roboto_Regular);

        btn_submit.setTypeface(roboto_Medium);
        btn_forgotpassword.setTypeface(roboto_Medium);
        btn_signUp.setTypeface(roboto_Bold);


        // Functions

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        btn_forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Intent intent = new Intent(SignInActivity.this,ForgotPasswordEmailActivity.class);
                startActivity(intent);
            }
        });




        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String full_phonenumber = ccp_phoneCode.getSelectedCountryCode() + et_mobilenumber.getText();
                final SweetAlertDialog pDialog = new SweetAlertDialog(SignInActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.setTitleText("Logging In...");
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.show();

                FormBody.Builder formBuilder = new FormBody.Builder()
                        .add("phone", full_phonenumber)
                        .add("password", et_password.getText().toString())
                        .add("deviceToken", "");

                RequestBody formBody = formBuilder.build();

                APIManager.getInstance().UserSignIn(formBody, new APIManager.MyCallBackInterface() {
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


                                Intent intent = new Intent(SignInActivity.this,EmptyTripMainActivity.class);
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
                                        new SweetAlertDialog(SignInActivity.this, SweetAlertDialog.ERROR_TYPE)
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
                                new SweetAlertDialog(SignInActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Oops...")
                                        .setContentText("Unable to sign in, try again")
                                        .show();
                            }
                        });

                    }
                });


            }

        });

    }
}
