package com.emptytrip.hr.emptytrip;

import android.content.Context;
import android.graphics.Typeface;
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
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectEmailPageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectEmailPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectEmailPageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SelectEmailPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectEmailPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectEmailPageFragment newInstance(String param1, String param2) {
        SelectEmailPageFragment fragment = new SelectEmailPageFragment();
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
        return inflater.inflate(R.layout.fragment_select_email_page, container, false);
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
        Button btn_gmail = (Button)view.findViewById(R.id.btn_gmail);
        final Button btn_hotmail = (Button)view.findViewById(R.id.btn_hotmail);
        Button btn_yahoo = (Button)view.findViewById(R.id.btn_yahoo);
        Button btn_outlook = (Button)view.findViewById(R.id.btn_outlook);
        Button btn_mailchimp = (Button)view.findViewById(R.id.btn_mailchimp);
        TextView tv_note = (TextView)view.findViewById(R.id.tv_note);
        EditText et_mail = (EditText)view.findViewById(R.id.et_mail);
        EditText et_password = (EditText)view.findViewById(R.id.et_password);
        Button btn_submit = (Button)view.findViewById(R.id.btn_submit);



        tv_title.setTypeface(roboto_Regular);
        btn_gmail.setTypeface(roboto_Regular);
        btn_hotmail.setTypeface(roboto_Regular);
        btn_yahoo.setTypeface(roboto_Regular);
        btn_outlook.setTypeface(roboto_Regular);
        btn_mailchimp.setTypeface(roboto_Regular);
        btn_mailchimp.setTypeface(roboto_Regular);

        tv_note.setTypeface(roboto_Regular);
        et_mail.setTypeface(roboto_Regular);
        et_password.setTypeface(roboto_Regular);

        btn_submit.setTypeface(roboto_Bold);

        btn_hotmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EmptyTripApplication emptyTripApplication = new EmptyTripApplication();
                emptyTripApplication.setEmailModeStr("MICROSOFT");
                loadFragment(new GrantPageFragment());
            }
        });

        btn_gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EmptyTripApplication emptyTripApplication = new EmptyTripApplication();
                emptyTripApplication.setEmailModeStr("GOOGLE");
                loadFragment(new GrantPageFragment());
//                loadFragment(new ContactListFragment());
            }
        });

        btn_outlook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_hotmail.performClick();
            }
        });
        btn_yahoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmptyTripApplication emptyTripApplication = new EmptyTripApplication();
                emptyTripApplication.setEmailModeStr("YAHOO");
                loadFragment(new GrantPageFragment());
            }
        });


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
}
