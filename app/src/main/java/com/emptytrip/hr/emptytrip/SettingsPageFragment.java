package com.emptytrip.hr.emptytrip;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsPageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsPageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Boolean resolutionbutton_state = false;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SettingsPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsPageFragment newInstance(String param1, String param2) {
        SettingsPageFragment fragment = new SettingsPageFragment();
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Font initialize

        Typeface Gotham_Medium = Typeface.createFromAsset(getContext().getAssets(),"fonts/Gotham-Medium.otf");
        Typeface roboto_Regular = Typeface.createFromAsset(getContext().getAssets(),"fonts/Roboto-Regular.ttf");
        Typeface roboto_Medium = Typeface.createFromAsset(getContext().getAssets(),"fonts/Roboto-Medium.ttf");
        Typeface roboto_Black = Typeface.createFromAsset(getContext().getAssets(),"fonts/Roboto-Black.ttf");
        Typeface roboto_Bold = Typeface.createFromAsset(getContext().getAssets(),"fonts/Roboto-Bold.ttf");

        Button btn_profile = (Button)view.findViewById(R.id.btn_profile);
        Button btn_notification = (Button)view.findViewById(R.id.btn_notification);
        Button btn_billings = (Button)view.findViewById(R.id.btn_billings);
        Button btn_payments = (Button)view.findViewById(R.id.btn_payments);
        final Button btn_resolution = (Button)view.findViewById(R.id.btn_resolution);
        Button btn_logout = (Button)view.findViewById(R.id.btn_logout);

        final Button btn_tripsArchive = (Button)view.findViewById(R.id.btn_tripsArchive);
        final Button btn_myCases = (Button)view.findViewById(R.id.btn_myCases);

        btn_tripsArchive.setVisibility(View.GONE);
        btn_myCases.setVisibility(View.GONE);

        btn_profile.setTypeface(roboto_Bold);
        btn_notification.setTypeface(roboto_Bold);
        btn_billings.setTypeface(roboto_Bold);
        btn_payments.setTypeface(roboto_Bold);
        btn_tripsArchive.setTypeface(roboto_Bold);
        btn_myCases.setTypeface(roboto_Bold);
        btn_resolution.setTypeface(roboto_Bold);
        btn_logout.setTypeface(roboto_Bold);

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new MyProfileFragment());
            }
        });

        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new NotificationsFragment());
            }
        });

        btn_payments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new PaymentsFragment());
            }
        });

        btn_resolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resolutionbutton_state == false){
                    btn_tripsArchive.setVisibility(View.VISIBLE);
                    btn_myCases.setVisibility(View.VISIBLE);

                    btn_resolution.setTextColor(Color.GREEN);

                }else{
                    btn_tripsArchive.setVisibility(View.GONE);
                    btn_myCases.setVisibility(View.GONE);

                    btn_resolution.setTextColor(Color.BLACK);
                }
                resolutionbutton_state = !resolutionbutton_state;
            }
        });

        btn_tripsArchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new TripsArchiveFragment());
            }
        });

        btn_myCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new MyCasePageFragment());
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("", MODE_PRIVATE).edit();
                editor.putInt("userId", 0);
                editor.commit();

                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings_page, container, false);
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
