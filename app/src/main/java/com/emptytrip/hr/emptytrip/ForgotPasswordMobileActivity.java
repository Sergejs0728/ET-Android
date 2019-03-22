package com.emptytrip.hr.emptytrip;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ForgotPasswordMobileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_mobile);


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
        final TextView et_mobilenumber = (TextView)findViewById(R.id.et_mobilenumber);

        Button btn_reset = (Button)findViewById(R.id.btn_reset);
        Button btn_usingemail = (Button)findViewById(R.id.btn_usingemail);

        Button btn_back = (Button)findViewById(R.id.btn_back);


        final CountryCodePicker countryCodePicker = (CountryCodePicker)findViewById(R.id.ccp_phoneCode);
        countryCodePicker.setCountryForNameCode("GB");

        // Set Font

        tv_logo1.setTypeface(Gotham_Medium);
        tv_logo2.setTypeface(Gotham_Medium);
        tv_main.setTypeface(roboto_Regular);
        tv_mobile.setTypeface(roboto_Regular);
        et_mobilenumber.setTypeface(roboto_Regular);

        btn_reset.setTypeface(roboto_Medium);
        btn_usingemail.setTypeface(roboto_Bold);





        // Functions

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_usingemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Intent intent = new Intent(ForgotPasswordMobileActivity.this,ForgotPasswordEmailActivity.class);
                startActivity(intent);
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String full_phonenumber = countryCodePicker.getSelectedCountryCode() + et_mobilenumber.getText();
                final SweetAlertDialog pDialog = new SweetAlertDialog(ForgotPasswordMobileActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.setTitleText("Logging In...");
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.show();

                FormBody.Builder formBuilder = new FormBody.Builder()
                        .add("phone", full_phonenumber);

                RequestBody formBody = formBuilder.build();

                APIManager.getInstance().forgotPasswordRequestAPI(formBody, new APIManager.MyCallBackInterface() {
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

                                Intent intent = new Intent(ForgotPasswordMobileActivity.this,ResetCodeActivity.class);
                                intent.putExtra("id",result.getInt("id"));
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
                                        new SweetAlertDialog(ForgotPasswordMobileActivity.this, SweetAlertDialog.ERROR_TYPE)
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
                                new SweetAlertDialog(ForgotPasswordMobileActivity.this, SweetAlertDialog.ERROR_TYPE)
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
