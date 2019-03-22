package com.emptytrip.hr.emptytrip;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.ClientAuthentication;
import net.openid.appauth.ClientSecretPost;
import net.openid.appauth.ResponseTypeValues;
import net.openid.appauth.TokenResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GrantPageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GrantPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GrantPageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    OAuthModel oauthData;
    AuthorizationRequest authRequest;
    AuthorizationService authService;
    AuthorizationServiceConfiguration serviceConfig;
    AuthorizationRequest.Builder authRequestBuilder;
    AuthorizationResponse resp;
    AuthorizationException ex;


    ArrayList<ProtectContactModel> allContacts = new ArrayList<ProtectContactModel>();


    private static final int GOOGLE_AUTH_REQUEST = 176;
    private static final int MICROSOFT_AUTH_REQUEST = 7;
    private static final int YAHOO_AUTH_REQUEST = 715;
    String service = "YAHOO";
    public GrantPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GrantPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GrantPageFragment newInstance(String param1, String param2) {
        GrantPageFragment fragment = new GrantPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grant_page, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

// Font initialize

        Typeface Gotham_Medium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Gotham-Medium.otf");
        Typeface roboto_Regular = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface roboto_Medium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Medium.ttf");
        Typeface roboto_Black = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Black.ttf");
        Typeface roboto_Bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf");


        TextView tv_title = (TextView)view.findViewById(R.id.tv_title);
        Button btn_grant = (Button)view.findViewById(R.id.btn_grant);
        TextView tv_note = (TextView)view.findViewById(R.id.tv_note);

        tv_title.setTypeface(roboto_Black);
        btn_grant.setTypeface(roboto_Medium);
        tv_note.setTypeface(roboto_Regular);

        btn_grant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callGrant();
            }
        });

        EmptyTripApplication emptyTripApplication = new EmptyTripApplication();
        this.service = emptyTripApplication.getEmailModeStr();

        switch (this.service) {
            case "GOOGLE":
                setupGoogle();
                break;
            case "MICROSOFT":
                setupMicrosoft();
                break;
            case "YAHOO":
                setupYahoo();
                break;
            default:
//                finish();
        }

    }
    private void callGrant() {
        authService = new AuthorizationService(getContext());
        Intent authIntent = authService.getAuthorizationRequestIntent(authRequest);

        switch (service) {
            case "GOOGLE":
                startActivityForResult(authIntent, GOOGLE_AUTH_REQUEST);
                break;
            case "MICROSOFT":
                startActivityForResult(authIntent, MICROSOFT_AUTH_REQUEST);
                break;
            case "YAHOO":
                startActivityForResult(authIntent, YAHOO_AUTH_REQUEST);
                break;
            default:
                break;
        }
    }

    private void setupGoogle() {

        serviceConfig =
                new AuthorizationServiceConfiguration(
                        Uri.parse(Config.OAuthGoogle.get(Config.OAuthGoogle.AUTHORIZE_URI)), // authorization endpoint
                        Uri.parse(Config.OAuthGoogle.get(Config.OAuthGoogle.TOKEN_URI))); // token endpoint

        authRequestBuilder =
                new AuthorizationRequest.Builder(
                        serviceConfig, // the authorization service configuration
                        Config.OAuthGoogle.get(Config.OAuthGoogle.CLIENT_ID), // the client ID, typically pre-registered and static
                        ResponseTypeValues.CODE, // the response_type value: we want a code
                        Uri.parse(Config.OAuthGoogle.get(Config.OAuthGoogle.REDIRECT_URIS))); // the redirect URI to which the auth response is sent

        authRequest = authRequestBuilder
                .setScope(Config.OAuthGoogle.get(Config.OAuthGoogle.SCOPE))
                .build();

    }
    private void setupMicrosoft() {

        serviceConfig =
                new AuthorizationServiceConfiguration(
                        Uri.parse(Config.OAuthMicrosoft.get(Config.OAuthMicrosoft.AUTHORIZE_URI)), // authorization endpoint
                        Uri.parse(Config.OAuthMicrosoft.get(Config.OAuthMicrosoft.TOKEN_URI))); // token endpoint

        authRequestBuilder =
                new AuthorizationRequest.Builder(
                        serviceConfig, // the authorization service configuration
                        Config.OAuthMicrosoft.get(Config.OAuthMicrosoft.CLIENT_ID), // the client ID, typically pre-registered and static
                        ResponseTypeValues.CODE, // the response_type value: we want a code
                        Uri.parse(Config.OAuthMicrosoft.get(Config.OAuthMicrosoft.REDIRECT_URIS))); // the redirect URI to which the auth response is sent

        authRequest = authRequestBuilder
                .setScope(Config.OAuthMicrosoft.get(Config.OAuthMicrosoft.SCOPE))
                .build();

    }
    private void setupYahoo() {

        serviceConfig =
                new AuthorizationServiceConfiguration(
                        Uri.parse(Config.OAuthYahoo.get(Config.OAuthYahoo.AUTHORIZE_URI)), // authorization endpoint
                        Uri.parse(Config.OAuthYahoo.get(Config.OAuthYahoo.TOKEN_URI))); // token endpoint

        authRequestBuilder =
                new AuthorizationRequest.Builder(
                        serviceConfig, // the authorization service configuration
                        Config.OAuthYahoo.get(Config.OAuthYahoo.CLIENT_ID), // the client ID, typically pre-registered and static
                        ResponseTypeValues.CODE, // the response_type value: we want a code
                        Uri.parse(Config.OAuthYahoo.get(Config.OAuthYahoo.REDIRECT_URIS))); // the redirect URI to which the auth response is sent

        authRequest = authRequestBuilder
                .setScope(Config.OAuthYahoo.get(Config.OAuthYahoo.SCOPE))
                .build();

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GOOGLE_AUTH_REQUEST) {

            resp = AuthorizationResponse.fromIntent(data);
            ex = AuthorizationException.fromIntent(data);

            if (resp != null) {
                // authorization completed
                callForToken(resp);
            } else {
                // authorization failed, check ex for more details
                String err = ex.getMessage();
                Log.e("Auth Error" + service, err);
            }

        } else if (requestCode == MICROSOFT_AUTH_REQUEST) {

            resp = AuthorizationResponse.fromIntent(data);
            ex = AuthorizationException.fromIntent(data);

            if (resp != null) {
                // authorization completed
                callForToken(resp);
            } else {
                // authorization failed, check ex for more details
                String err = ex.getMessage();
                Log.e("Auth Error" + service, err);
            }

        } else if (requestCode == YAHOO_AUTH_REQUEST) {

            resp = AuthorizationResponse.fromIntent(data);
            ex = AuthorizationException.fromIntent(data);

            if (resp != null) {
                // authorization completed
                callForYahooToken(resp);
            } else {
                // authorization failed, check ex for more details
                String err = ex.getMessage();
                Log.e("Auth Error" + service, err);
            }
        }
    }

    private void callForToken(AuthorizationResponse resp) {
        authService.performTokenRequest(
                resp.createTokenExchangeRequest(),
                new AuthorizationService.TokenResponseCallback() {
                    @Override public void onTokenRequestCompleted(
                            TokenResponse resp, AuthorizationException ex) {
                        if (resp != null) {
                            // exchange succeeded
                            String token = resp.accessToken;
                            Log.i("Token " + service, token);

                            storeTokenData(token);
                        } else {
                            // authorization failed, check ex for more details
                            String err = ex.getMessage();
                            Log.e("Token Error" + service, err);
                        }
                    }
                });
    }

    private void callForYahooToken(AuthorizationResponse resp) {
        ClientAuthentication clientAuth = new ClientSecretPost(Config.OAuthYahoo.get(Config.OAuthYahoo.CLIENT_SECRET));
        authService.performTokenRequest(
                resp.createTokenExchangeRequest(),
                clientAuth,
                new AuthorizationService.TokenResponseCallback() {
                    @Override public void onTokenRequestCompleted(
                            TokenResponse resp, AuthorizationException ex) {
                        if (resp != null) {
                            // exchange succeeded
                            String token = resp.accessToken;
                            Log.i("Token " + service, token);

                            String xoauth = resp.additionalParameters.get("xoauth_yahoo_guid");
                            Log.i("XOAUTH ", xoauth);

                            storeYahooTokenData(token, xoauth);
                        } else {
                            // authorization failed, check ex for more details
                            String err = ex.getMessage();
                            Log.e("Token Error" + service, err);
                        }
                    }
                });
    }
    private void storeYahooTokenData(String token, String xoauth) {

        DataManager dm = new DataManager(getContext());
        dm.setEmailOAuthData(service, token, xoauth);

        oauthData = dm.emailOAuthData();

        getYahooContacts(getContext().getContentResolver());

    }
    private void storeTokenData(String token) {

        DataManager dm = new DataManager(getContext());
        dm.setEmailOAuthData(service, token, "");

        oauthData = dm.emailOAuthData();

        switch (this.service) {
            case "GOOGLE":
                getGoogleContacts(getContext().getContentResolver());
                break;
            case "MICROSOFT":
                getMicrosoftContacts(getContext().getContentResolver());
                break;
            case "YAHOO":
                setupYahoo();
                break;
            default:
//                finish();
        }

    }
    protected void getGoogleContacts(ContentResolver cr) {

        /* Make sure we have a token to send to graph */
        if (oauthData.getToken() == null) {return;}

        String url = "https://people.googleapis.com/v1/people/me/connections";
        String properties = "names,emailAddresses,phoneNumbers";
        String orderby = "FIRST_NAME_ASCENDING";
        String top = "2000";

        String uri = String.format(url+ "?personFields=%1$s&sortOrder=%2$s&pageSize=%3$s",
                properties,
                orderby,
                top);

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, uri, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("Response", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.has("connections")) {

                        JSONArray fetchedContacts = jsonObject.getJSONArray("connections");
                        if (fetchedContacts != null) {

                            for (int i = 0; i < fetchedContacts.length(); i++) {

                                JSONObject contact = fetchedContacts.getJSONObject(i);
//                                Log.i(TAG, contact.toString());

                                ProtectContactModel protectContact = new ProtectContactModel();
                                protectContact.setId(i+9999);

                                String name="", email="", phone = "";

                                if (contact.has("names")) {
                                    JSONObject names = contact.getJSONArray("names").getJSONObject(0);
                                    name = names.getString("displayName");
                                    Log.i("name", name);
                                }
                                protectContact.setName(name);

                                if (contact.has("emailAddresses")) {
                                    JSONObject emailAddresses = contact.getJSONArray("emailAddresses").getJSONObject(0);
                                    email = emailAddresses.getString("value");
                                    Log.i("email", email);
                                }
                                protectContact.setEmail(email);

                                if (contact.has("phoneNumbers")) {
                                    JSONObject phoneNumbers = contact.getJSONArray("phoneNumbers").getJSONObject(0);
                                    phone = phoneNumbers.getString("value");
                                    Log.i("phone", phone);
                                }
                                protectContact.setPhone(phone);

                                allContacts.add(protectContact);
                                //Log.i("ProtectContacts", protectContact.toString());
                                protectContact = null;

                            }
                            EmptyTripApplication emptyTripApplication = new EmptyTripApplication();
                            emptyTripApplication.setProtectContacts(allContacts);

                            emptyTripApplication.setProtectModeStr("email");
                            loadFragment(new ContactListFragment());
                        }
                    }

//                    getContactsListView(cr);

                } catch (JSONException e) {

                    e.printStackTrace();


                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());

                Toast.makeText(getContext(), "Error: Unable to process request. Please try again.",
                        Toast.LENGTH_LONG).show();
            }

        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + oauthData.getToken());
                return headers;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(Config.API_REQUEST_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Access the RequestQueue through your singleton class.
        RequestHelper.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    private void getMicrosoftContacts(ContentResolver cr) {

        /* Make sure we have a token to send to graph */
        if (oauthData.getToken() == null) {return;}

        String url = "https://graph.microsoft.com/v1.0/me/contacts";
        String properties = "givenName,surname,emailAddresses,mobilePhone";
        String orderby = "givenName";
        String top = "499";

        String uri = String.format(url+ "?$select=%1$s&$orderby=%2$s&$top=%3$s",
                properties,
                orderby,
                top);

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, uri, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("Response", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.has("value")) {

                        JSONArray fetchedContacts = jsonObject.getJSONArray("value");
                        if (fetchedContacts != null) {

                            for (int i = 0; i < fetchedContacts.length(); i++) {

                                JSONObject contact = fetchedContacts.getJSONObject(i);
                                Log.i("Error", contact.toString());

                                ProtectContactModel protectContact = new ProtectContactModel();
                                protectContact.setId(i+9999);

                                String fname="", lname="", email="", phone = "";

                                if (contact.has("givenName")) {
                                    fname = contact.getString("givenName");
                                    Log.i("name", fname);
                                }

                                if (contact.has("surname")) {
                                    lname = contact.getString("surname");
                                    Log.i("name", lname);
                                }
                                protectContact.setName(fname + ' ' + lname);

                                if (contact.has("emailAddresses")) {
                                    JSONObject emailAddresses = contact.getJSONArray("emailAddresses").getJSONObject(0);
                                    email = emailAddresses.getString("address");
                                    Log.i("email", email);
                                }
                                protectContact.setEmail(email);

                                if (contact.has("mobilePhone")) {
                                    phone = contact.getString("mobilePhone");
                                    Log.i("phone", phone);
                                }
                                protectContact.setPhone(phone);

                                allContacts.add(protectContact);


                                //Log.i("ProtectContacts", protectContact.toString());
                                protectContact = null;

                            }
                            EmptyTripApplication emptyTripApplication = new EmptyTripApplication();
                            emptyTripApplication.setProtectContacts(allContacts);

                            emptyTripApplication.setProtectModeStr("email");
                            loadFragment(new ContactListFragment());
                        }
                    }

//                    getContactsListView(cr);

                } catch (JSONException e) {

                    e.printStackTrace();


                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());

                Toast.makeText(getContext(), "Error: Unable to process request. Please try again.",
                        Toast.LENGTH_LONG).show();
            }

        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + oauthData.getToken());
                return headers;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(Config.API_REQUEST_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Access the RequestQueue through your singleton class.
        RequestHelper.getInstance(getContext()).addToRequestQueue(stringRequest);

    }
    private void getYahooContacts(ContentResolver cr) {

        /* Make sure we have a token to send to graph */
        if (oauthData.getToken() == null) {return;}

        String url = "https://social.yahooapis.com/v1/user/" + oauthData.getXoauth() + "/contacts;out=displayname,phone,email;sort-fields=displayname;sort=asc;count=max?format=json";

        String uri = url;

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("Response", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.has("contacts")) {

                        JSONArray fetchedContacts = jsonObject.getJSONObject("contacts").getJSONArray("contact");
                        if (fetchedContacts != null) {

                            for (int i = 0; i < fetchedContacts.length(); i++) {

                                JSONArray contact = fetchedContacts.getJSONObject(i).getJSONArray("fields");
                                Log.i("RESPONSE:", contact.toString());

                                ProtectContactModel protectContact = new ProtectContactModel();
                                protectContact.setId(i+9999);

                                String name="", email="", phone = "";

                                for (int j = 0; j < contact.length(); j++) {

                                    String field = contact.getJSONObject(j).getString("type");

                                    if (field.equals("displayname")) {
                                        name = contact.getJSONObject(j).getString("value");
                                        Log.i("name", name);

                                        protectContact.setName(name);
                                    }

                                    if (field.equals("email")) {
                                        email = contact.getJSONObject(j).getString("value");
                                        Log.i("email", email);

                                        protectContact.setEmail(email);
                                    }

                                    if (field.equals("phone")) {
                                        phone = contact.getJSONObject(j).getString("value");
                                        Log.i("phone", phone);

                                        protectContact.setPhone(phone);
                                    }
                                }

                                allContacts.add(protectContact);
                                //Log.i("ProtectContacts", protectContact.toString());
                                protectContact = null;


                            }

                            EmptyTripApplication emptyTripApplication = new EmptyTripApplication();
                            emptyTripApplication.setProtectContacts(allContacts);

                            emptyTripApplication.setProtectModeStr("email");
                            loadFragment(new ContactListFragment());

                        }
                    }



                } catch (JSONException e) {

                    e.printStackTrace();


                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());

                Toast.makeText(getContext(), "Error: Unable to process request. Please try again.",
                        Toast.LENGTH_LONG).show();
            }

        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + oauthData.getToken());
                return headers;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(Config.API_REQUEST_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Access the RequestQueue through your singleton class.
        RequestHelper.getInstance(getContext()).addToRequestQueue(stringRequest);

    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        authService.dispose();

    }
}
