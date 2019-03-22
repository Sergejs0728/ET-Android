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

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AddNameActivity extends Activity {

    String verifyCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_name);


        Intent intent = getIntent();
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

        TextView tv_fullname = (TextView)findViewById(R.id.tv_fullname);
        final EditText et_fullname = (EditText)findViewById(R.id.et_fullname);

        Button btn_Done = (Button)findViewById(R.id.btn_Done);

        Button btn_back = (Button)findViewById(R.id.btn_back);

        // Set Font

        tv_logo1.setTypeface(Gotham_Medium);
        tv_logo2.setTypeface(Gotham_Medium);
        tv_main.setTypeface(roboto_Black);

        tv_fullname.setTypeface(roboto_Regular);
        et_fullname.setTypeface(roboto_Regular);

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

                String fullname = et_fullname.getText().toString();
                if(fullname.equals("")){
                    new SweetAlertDialog(AddNameActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Should Input your full name!")
                            .show();
                }else{
                    Intent intent = new Intent(AddNameActivity.this,CreatePasswordActivity.class);
                    intent.putExtra("fullname",fullname);
                    intent.putExtra("verifyCode",verifyCode);
                    startActivity(intent);
                }
            }
        });
    }
}
