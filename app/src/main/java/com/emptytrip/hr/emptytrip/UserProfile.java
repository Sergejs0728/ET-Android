package com.emptytrip.hr.emptytrip;

/**
 * Created by navi on 08/04/2018.
 */

public class UserProfile {

    public int id;
    public String name;
    public String email;
    public String phone;
    public String profileImage;
    public String inviteCode;
    public String authToken;
    public int status;
    public int verified;
    public String dateCreated;
    public String dateUpdated;

    public UserProfile(){

        id = -1;
        name = "";
        email = "";
        phone = "";
        profileImage = "";
        inviteCode = "";
        authToken = "";
        status = -1;
        verified = -1;
        dateCreated = "";
        dateUpdated = "";


    }


}