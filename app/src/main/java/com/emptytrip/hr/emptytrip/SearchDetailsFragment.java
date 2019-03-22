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
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SearchDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchDetailsFragment newInstance(String param1, String param2) {
        SearchDetailsFragment fragment = new SearchDetailsFragment();
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
        return inflater.inflate(R.layout.fragment_search_details, container, false);
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        // Font initialize

        Typeface Gotham_Medium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Gotham-Medium.otf");
        Typeface roboto_Regular = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface roboto_Medium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Medium.ttf");
        Typeface roboto_Black = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Black.ttf");
        Typeface roboto_Bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf");

        TextView tv_mylocation = (TextView)view.findViewById(R.id.tv_mylocation);
        TextView tv_destination = (TextView)view.findViewById(R.id.tv_destination);
        TextView text = (TextView)view.findViewById(R.id.text);
        TextView tv_estimatehours = (TextView)view.findViewById(R.id.tv_estimatehours);
        TextView tv_departuretime = (TextView)view.findViewById(R.id.tv_departuretime);
        TextView tv_arrivaltime = (TextView)view.findViewById(R.id.tv_arrivaltime);

        tv_mylocation.setTypeface(roboto_Regular);
        tv_destination.setTypeface(roboto_Regular);
        text.setTypeface(roboto_Regular);
        tv_estimatehours.setTypeface(roboto_Bold);
        tv_departuretime.setTypeface(roboto_Bold);
        tv_arrivaltime.setTypeface(roboto_Bold);

        TextView tv_title = (TextView)view.findViewById(R.id.tv_title);
        TextView tv_peopleno = (TextView)view.findViewById(R.id.tv_peopleno);
        TextView tv_petno = (TextView)view.findViewById(R.id.tv_petno);
        TextView tv_luggage = (TextView)view.findViewById(R.id.tv_luggage);
        TextView lb_small1 = (TextView)view.findViewById(R.id.lb_small1);
        TextView lb_small2 = (TextView)view.findViewById(R.id.lb_small2);
        TextView lb_small3 = (TextView)view.findViewById(R.id.lb_small3);
        TextView tv_small1 = (TextView)view.findViewById(R.id.tv_small1);
        TextView tv_small2 = (TextView)view.findViewById(R.id.tv_small2);
        TextView tv_small3 = (TextView)view.findViewById(R.id.tv_small3);

        TextView tv_total = (TextView)view.findViewById(R.id.tv_total);
        TextView tv_price = (TextView)view.findViewById(R.id.tv_price);


        tv_title.setTypeface(roboto_Medium);
        tv_peopleno.setTypeface(roboto_Medium);
        tv_petno.setTypeface(roboto_Medium);
        tv_luggage.setTypeface(roboto_Medium);
        lb_small1.setTypeface(roboto_Medium);
        lb_small2.setTypeface(roboto_Medium);
        lb_small3.setTypeface(roboto_Medium);
        tv_small1.setTypeface(roboto_Medium);
        tv_small2.setTypeface(roboto_Medium);
        tv_small3.setTypeface(roboto_Medium);
        tv_total.setTypeface(roboto_Bold);
        tv_price.setTypeface(roboto_Black);

        Button btn_confirm = (Button)view.findViewById(R.id.btn_confirm);

        btn_confirm.setTypeface(roboto_Medium);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new SearchDriverDetailsFragment());
            }
        });

        Button btn_back = (Button)view.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new SearchDriversFragment());
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
