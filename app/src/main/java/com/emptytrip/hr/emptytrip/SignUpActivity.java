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

public class SignUpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

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
        TextView tv_affiliateCode = (TextView)findViewById(R.id.tv_affiliateCode);
        final EditText et_affiliateCode = (EditText)findViewById(R.id.et_affiliateCode);
        final CountryCodePicker ccp_phoneCode = (CountryCodePicker)findViewById(R.id.ccp_phoneCode);

        TextView tv_email = (TextView)findViewById(R.id.tv_email);
        final EditText et_email = (EditText)findViewById(R.id.et_email);
        Button btn_submit = (Button)findViewById(R.id.btn_submit);
        Button btn_signIn = (Button)findViewById(R.id.btn_signIn);


        //

        final EditText et_mobilenumber = (EditText)findViewById(R.id.et_mobilenumber);


        Button btn_back = (Button)findViewById(R.id.btn_back);

        // Set Font

        tv_logo1.setTypeface(Gotham_Medium);
        tv_logo2.setTypeface(Gotham_Medium);

        tv_main.setTypeface(roboto_Black);
        tv_mobile.setTypeface(roboto_Regular);

        ccp_phoneCode.setCountryForNameCode("RU");
        ccp_phoneCode.setTypeFace(roboto_Regular);

        btn_submit.setTypeface(roboto_Medium);
        btn_signIn.setTypeface(roboto_Bold);

        tv_affiliateCode.setTypeface(roboto_Regular);
        et_affiliateCode.setTypeface(roboto_Regular);

        tv_email.setTypeface(roboto_Regular);
        et_email.setTypeface(roboto_Regular);

        // Functions

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final SweetAlertDialog pDialog = new SweetAlertDialog(SignUpActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.setTitleText("Logging In...");
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.show();


                FormBody.Builder formBuilder = new FormBody.Builder()
                        .add("email", et_email.getText().toString())
                        .add("phone", ccp_phoneCode.getSelectedCountryCode() + et_mobilenumber.getText().toString())
                        .add("affiliateCode", et_affiliateCode.getText().toString())
                        .add("deviceToken", "");
                RequestBody formBody = formBuilder.build();

                APIManager.getInstance().UserSignUp(formBody, new APIManager.MyCallBackInterface() {
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

                                String id = result.getString("id");
//                                String verificationCode = result.getString("verificationCode");

                                Intent intent = new Intent(SignUpActivity.this,ConfirmCodeActivity.class);
                                intent.putExtra("id",id);
//                                intent.putExtra("verificationCode",verificationCode);
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
                                        new SweetAlertDialog(SignUpActivity.this, SweetAlertDialog.ERROR_TYPE)
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
                                new SweetAlertDialog(SignUpActivity.this, SweetAlertDialog.ERROR_TYPE)
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
