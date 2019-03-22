package com.emptytrip.hr.emptytrip;

import android.app.Activity;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;


public class EmptyTripMainActivity extends AppCompatActivity {

    public FrameLayout fl_pager;

    int pageNumber = 0;

    //
    EmptyTripApplication emptyTripApplication = new EmptyTripApplication();
    UserProfile user = emptyTripApplication.getUserProfile();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_trip_main);


        //Save User Info

        SharedPreferences.Editor editor = getSharedPreferences("", MODE_PRIVATE).edit();
        editor.putInt("userId", user.id);
        editor.putString("name", user.name);
        editor.putString("email", user.email);
        editor.putString("phone", user.phone);
        editor.putString("profileImage", user.profileImage);
        editor.putString("inviteCode", user.inviteCode);
        editor.putString("authToken", user.authToken);
        editor.putInt("status", user.status);
        editor.putInt("verified", user.verified);
        editor.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Font initialize

        Typeface Gotham_Medium = Typeface.createFromAsset(getAssets(),"fonts/Gotham-Medium.otf");
        Typeface roboto_Regular = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Regular.ttf");
        Typeface roboto_Medium = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
        Typeface roboto_Black = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Black.ttf");
        Typeface roboto_Bold = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");

        // Title Bar Remove
        getSupportActionBar().hide();


        final BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx)findViewById(R.id.bottom_navigation);

        // Set Search Fragment
        loadFragment(new SearchPageFragment());

        for(int i=0;i<4;i++){
            if(i==0){
                bottomNavigationViewEx.setIconTintList(i,ColorStateList.valueOf(Color.GREEN));
                bottomNavigationViewEx.setTextTintList(i,ColorStateList.valueOf(Color.GREEN));
            }else{
                bottomNavigationViewEx.setIconTintList(i,ColorStateList.valueOf(Color.WHITE));
                bottomNavigationViewEx.setTextTintList(i,ColorStateList.valueOf(Color.WHITE));
            }
        }

        // BottomNavigationViewEx

        bottomNavigationViewEx.enableAnimation(true);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);







        // Set Nav Font

        bottomNavigationViewEx.setTypeface(roboto_Medium);

        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragments[] = {new SearchPageFragment(),new MyTripsPageFragment(),new EarningsPageFragment(),new SettingsPageFragment()};

                int position = bottomNavigationViewEx.getMenuItemPosition(item);

                loadFragment(fragments[position]);

                for(int i=0;i<4;i++){
                    if(i == position){
                        bottomNavigationViewEx.setIconTintList(i,ColorStateList.valueOf(Color.GREEN));
                        bottomNavigationViewEx.setTextTintList(i,ColorStateList.valueOf(Color.GREEN));
                    }else{
                        bottomNavigationViewEx.setIconTintList(i,ColorStateList.valueOf(Color.WHITE));
                        bottomNavigationViewEx.setTextTintList(i,ColorStateList.valueOf(Color.WHITE));

                    }
                }

                return false;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {

    }
}
