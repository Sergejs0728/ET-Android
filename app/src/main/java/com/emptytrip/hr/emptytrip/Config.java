package com.emptytrip.hr.emptytrip;

/**
 * Created by Inder on 2018-03-20.
 */

public class Config {


    public static final double MAP_LATITUDE = 51.506746; // London
    public static final double MAP_LONGITUDE = -0.127391; // London

    //public static final double MAP_LATITUDE = 43.519135; // Canada
    //public static final double MAP_LONGITUDE = -79.844519; // Canada

    public static final String YAHOO_REDIRECT_URL = "https://emptytrip.com/oauth/yahoo_oauth_callback_user.php";
    //public static final String YAHOO_REDIRECT_URL = "https://emptytrip.com/sandbox/public/oauth/yahoo_oauth_callback_user.php";

    // CHANGES FOR LIVE ENDS
    //**********************

    // OAuth settings
    public static enum OAuthGoogle {
        CLIENT_ID,
        CLIENT_SECRET,
        AUTHORIZE_URI,
        TOKEN_URI,
        SCOPE,
        URL_SCHEME,
        REDIRECT_URIS;

        public static String get(OAuthGoogle google) {
            String val = "";

            switch (google) {
                case CLIENT_ID:
//                    val = "385590615938-8gt0tb7dlmqpjhrug49ibm4afp47sbrh.apps.googleusercontent.com";
                    val = "442130145202-stcrc9qk24re7kq7em84mnfourrpn4pb.apps.googleusercontent.com";
//                    val = "442130145202-ido4qh9sp6olvvlfpo9l5gh0lrskngle.apps.googleusercontent.com";
                    //val = "442130145202-2altohfavtn33rj4bekgmlqu089vmtpb.apps.googleusercontent.com";
                    break;
                case CLIENT_SECRET:
                    val = "Ru_acDzojPArB-yC6-XjrjiB";
//                    val = "ZSZ00kvNfp5AGDL46kbPsPTD";
                    break;
                case AUTHORIZE_URI:
                    val = "https://accounts.google.com/o/oauth2/auth";
                    break;
                case TOKEN_URI:
                    val = "https://www.googleapis.com/oauth2/v3/token";
                    break;
                case SCOPE:
                    val = "profile https://www.googleapis.com/auth/contacts.readonly";
                    break;
                case URL_SCHEME:
                    val = "com.googleusercontent.apps.442130145202-stcrc9qk24re7kq7em84mnfourrpn4pb";
//                    val = "com.googleusercontent.apps.385590615938-ndio61jfm1c6ccom2a1o23ec3g49smk4";
//                    val = "com.googleusercontent.apps.442130145202-ido4qh9sp6olvvlfpo9l5gh0lrskngle";
                    break;
                case REDIRECT_URIS:
                    val = "com.googleusercontent.apps.442130145202-stcrc9qk24re7kq7em84mnfourrpn4pb:/auth";
//                    val = "com.googleusercontent.apps.385590615938-8gt0tb7dlmqpjhrug49ibm4afp47sbrh:/auth";
                    //val = "com.googleusercontent.apps.442130145202-pef4jbiaqramhvphihrma1jdkg06r6pp:/auth";
                    //val = "https://emptytrip.com/sandbox/services/contacts_google.php";
                    break;
            }

            return val;
        }
    }

    public static enum OAuthMicrosoft {
        CLIENT_ID,
        AUTHORIZE_URI,
        TOKEN_URI,
        SCOPE,
        URL_SCHEME,
        REDIRECT_URIS;

        public static String get(OAuthMicrosoft microsoft) {
            String val = "";

            switch (microsoft) {
                case CLIENT_ID:

//                    val = "c2763a1d-455c-4b36-a3da-542a0b27c89a";
                    val = "6ca7d5ec-fa57-4abb-b0d0-68149ec8fc26";
                    break;
                case AUTHORIZE_URI:
                    val = "https://login.microsoftonline.com/common/oauth2/v2.0/authorize";
                    break;
                case TOKEN_URI:
                    val = "https://login.microsoftonline.com/common/oauth2/v2.0/token";
                    break;
                case SCOPE:
                    val = "openid profile User.Read Contacts.Read";
                    break;
                case URL_SCHEME:
                    val = "msal6ca7d5ec-fa57-4abb-b0d0-68149ec8fc26";
                    break;
                case REDIRECT_URIS:
//                    val = "msalc2763a1d-455c-4b36-a3da-542a0b27c89a://oauth/callback";
                    val = "msal6ca7d5ec-fa57-4abb-b0d0-68149ec8fc26://auth";
                    break;
            }

            return val;
        }
    }
    // Progress Data
    public static enum DataProgress {
        INTRO_SCREEN_SHOWN,
        REGISTRATION_COMPLETE,
        REGISTRATION_STEP_DONE,
        SIGNIN_COMPLETE;
    }
    public static final String DATA_FILE_NAME = BuildConfig.APPLICATION_ID + ".DataFile";
    public static final int API_REQUEST_TIMEOUT = 20000;
    public static enum OAuthYahoo {
        CLIENT_ID,
        CLIENT_SECRET,
        AUTHORIZE_URI,
        TOKEN_URI,
        SCOPE,
        URL_SCHEME,
        REDIRECT_URIS;

        public static String get(OAuthYahoo yahoo) {
            String val = "";

            switch (yahoo) {
                case CLIENT_ID:
//                    val = "dj0yJmk9a2FmbFBVTlJoVVlGJmQ9WVdrOU5UVmpNRmN5TXpZbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD0wNA--";
                    val = "dj0yJmk9VWRMVlcxZzJHdk5wJmQ9WVdrOVRHSlllWEZOTm1VbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD1iYQ--";
                    break;
                case CLIENT_SECRET:
                    val = "116bf4f50718d5209884d61e97bc40e033ad1839";
//                    val = "2253b5c031cd8bfa948f8c8269525c10d819a537";
                    break;
                case AUTHORIZE_URI:
                    val = "https://api.login.yahoo.com/oauth2/request_auth";
                    break;
                case TOKEN_URI:
                    val = "https://api.login.yahoo.com/oauth2/get_token";
                    break;
                case SCOPE:
                    val = "sdct-r";
                    break;
                case URL_SCHEME:
                    val = "oob.com.emptytrip.emptytripdrive-lbxyqm6e";
                    break;
                case REDIRECT_URIS:
                    val = YAHOO_REDIRECT_URL;
                    break;
            }

            return val;
        }
    }
}