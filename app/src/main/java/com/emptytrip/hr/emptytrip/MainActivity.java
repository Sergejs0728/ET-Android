package com.emptytrip.hr.emptytrip;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.relex.circleindicator.CircleIndicator;
import okhttp3.FormBody;
import okhttp3.RequestBody;


public class MainActivity extends FragmentActivity {

    EmptyTripApplication emptyTripApplication = new EmptyTripApplication();
    UserProfile user = new UserProfile();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences prefs = getSharedPreferences("", MODE_PRIVATE);
        user.id = prefs.getInt("userId", -1);
        user.name = prefs.getString("name", "");
        user.email  = prefs.getString("email", "");
        user.phone  = prefs.getString("phone", "");
        user.profileImage  = prefs.getString("profileImage", "");
        user.inviteCode  = prefs.getString("inviteCode", "");
        user.authToken  = prefs.getString("authToken", "");
        user.status  = prefs.getInt("status", -1);
        user.verified  = prefs.getInt("verified", -1);

        emptyTripApplication.setUserProfile(user);



        final SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.setTitleText("Please wait...");
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.show();



        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("userId", String.valueOf(user.id))
                .add("authToken", user.authToken);

        RequestBody formBody = formBuilder.build();
        APIManager.getInstance().AppLaunch(formBody, new APIManager.MyCallBackInterface() {
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

                if(result.has("id")){

                    Intent intent = new Intent(MainActivity.this,EmptyTripMainActivity.class);
                    startActivity(intent);

                }else{
                    pDialog.hide();
                }


            }


            @Override
            public void onFailure(final String error, int nCode) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pDialog.hide();
//                        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
//                                .setTitleText("Oops...")
//                                .setContentText("Unable to sign in, try again")
//                                .show();
                    }
                });

            }
        });



        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Add Fragments to adapter one by one
        adapter.addFragment(new Splash1Fragment(), "FRAG1");
        adapter.addFragment(new Splash2Fragment(), "FRAG2");
        adapter.addFragment(new Splash3Fragment(), "FRAG3");
        adapter.addFragment(new SplashMainFragment(), "FRAG4");
        viewPager.setAdapter(adapter);

        CircleIndicator indicator = (CircleIndicator)findViewById(R.id.indicator);

        indicator.setViewPager(viewPager);

//        viewPager.setCurrentItem(3);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {

    }
}
