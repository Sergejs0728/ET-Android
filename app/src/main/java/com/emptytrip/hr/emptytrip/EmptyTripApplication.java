package com.emptytrip.hr.emptytrip;

import android.app.Application;

import java.util.ArrayList;


/**
 * Created by navi on 08/04/2018.
 */

class EmptyTripApplication extends Application{


    static UserProfile userProfile = new UserProfile();
    static ArrayList<ProtectContactModel> allContactList = new ArrayList<ProtectContactModel>();
    static EmptyTripApplication signleton;
    EmptyTripApplication getInstance() {
        return signleton;
    }

    static String emailMode = "";

    void setUserProfile(UserProfile userProfile){
        this.userProfile = userProfile;
    }
    static UserProfile getUserProfile(){
        return userProfile;
    }
    static String protectModeStr = "";

    void setProtectContacts(ArrayList<ProtectContactModel> allcontacts){
        this.allContactList = allcontacts;
    }
    static ArrayList<ProtectContactModel> getProtectedContactList(){
        return allContactList;
    }

    void setProtectModeStr(String str){
        this.protectModeStr = str;
    }
    static String getProtectModeStr(){
        return protectModeStr;
    }

    void setEmailModeStr(String str){
        this.emailMode = str;
    }
    static String getEmailModeStr(){
        return emailMode;
    }


    @Override
    public void onCreate() {



        signleton = this;

        super.onCreate();
    }
}
