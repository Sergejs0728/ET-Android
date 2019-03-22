package com.emptytrip.hr.emptytrip;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProtectedManuallyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProtectedManuallyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProtectedManuallyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProtectedManuallyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProtectedManuallyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProtectedManuallyFragment newInstance(String param1, String param2) {
        ProtectedManuallyFragment fragment = new ProtectedManuallyFragment();
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
        return inflater.inflate(R.layout.fragment_protected_manually, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Button btn_back = (Button)view.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ProtectMenuFragment());
            }
        });

        final EditText et_fullname = (EditText)view.findViewById(R.id.et_fullnameField);
        final EditText et_email = (EditText)view.findViewById(R.id.et_emailField);
        final EditText et_phone_number = (EditText)view.findViewById(R.id.et_phone_numberField);


        final SharedPreferences.Editor editor = getActivity().getSharedPreferences("", MODE_PRIVATE).edit();

        Button btn_protect = (Button)view.findViewById(R.id.btn_protect);
        btn_protect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
                pDialog.setTitleText("Please wait...");
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.show();


                UserProfile userProfile = EmptyTripApplication.getUserProfile();

                String contact = et_fullname.getText().toString() + "~" + et_email.getText().toString() + "~" + et_phone_number.getText().toString();


                FormBody.Builder formBuilder = new FormBody.Builder()
                        .add("userId", String.valueOf(userProfile.id))
                        .add("count", String.valueOf(1))
                        .add("mode","manual")
                        .add("authToken",userProfile.authToken)
                        .add("contact0",contact);

                RequestBody formBody = formBuilder.build();
                APIManager.getInstance().protected_contacts(formBody, new APIManager.MyCallBackInterface() {
                    @Override
                    public void onSuccess(JSONArray result) {
                    }

                    @Override
                    public void onSuccess(JSONObject result) {

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pDialog.hide();
                            }
                        });
                        try {
                            //pDialog.hide();

                            if(result.has("id")){

                                int protected_contacts = result.getInt("protected");
                                int total_contacts = result.getInt("total");
                                int unprotected_contacts = total_contacts - protected_contacts;


                                editor.putInt("protected",protected_contacts);
                                editor.putInt("unprotected",unprotected_contacts);
                                editor.commit();


                                loadFragment(new ProtectContactPaymentFragment());

                            }else{

                                String error = result.getString("errors");

                                JSONArray errorsArray = result.getJSONArray("errors");
                                JSONObject errorIndex0 = errorsArray.getJSONObject(0);
                                final String msg = errorIndex0.getString("message");
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        pDialog.hide();
                                        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
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
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pDialog.hide();
                                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
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

    public void loadFragment(Fragment fragment) {
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
}
