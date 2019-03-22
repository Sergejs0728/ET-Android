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
 * {@link MakeCaseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MakeCaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MakeCaseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MakeCaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MakeCaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MakeCaseFragment newInstance(String param1, String param2) {
        MakeCaseFragment fragment = new MakeCaseFragment();
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

        //Font initialize

        Typeface Gotham_Medium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Gotham-Medium.otf");
        Typeface roboto_Regular = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface roboto_Medium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Medium.ttf");
        Typeface roboto_Black = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Black.ttf");
        Typeface roboto_Bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf");

        Button btn_makecase = (Button)view.findViewById(R.id.btn_makecase);
        btn_makecase.setTypeface(roboto_Medium);

        TextView tv_tripsArchive = (TextView)view.findViewById(R.id.tv_tripsArchive);
        tv_tripsArchive.setTypeface(roboto_Black);

        TextView tv_distance = (TextView)view.findViewById(R.id.tv_distance);
        tv_distance.setTypeface(roboto_Regular);

        TextView tv_mylocation = (TextView)view.findViewById(R.id.tv_mylocation);
        tv_mylocation.setTypeface(roboto_Regular);

        TextView tv_destination = (TextView)view.findViewById(R.id.tv_destination);
        tv_destination.setTypeface(roboto_Regular);


        TextView tv_price = (TextView)view.findViewById(R.id.tv_price);
        tv_price.setTypeface(roboto_Black);

        TextView tv_estimatehours = (TextView)view.findViewById(R.id.tv_estimatehours);
        tv_estimatehours.setTypeface(roboto_Bold);

        TextView tv_departuretime = (TextView)view.findViewById(R.id.tv_departuretime);
        tv_departuretime.setTypeface(roboto_Bold);

        TextView tv_arrivaltime = (TextView)view.findViewById(R.id.tv_arrivaltime);
        tv_arrivaltime.setTypeface(roboto_Bold);

        TextView tv_name = (TextView)view.findViewById(R.id.tv_name);
        tv_name.setTypeface(roboto_Bold);

        TextView tv_seates = (TextView)view.findViewById(R.id.tv_seates);
        tv_seates.setTypeface(roboto_Regular);

        TextView tv_note = (TextView)view.findViewById(R.id.tv_note);
        tv_note.setTypeface(roboto_Regular);

        btn_makecase.setTypeface(roboto_Medium);

        btn_makecase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new TripArchiveDetailsFragment());
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
        return inflater.inflate(R.layout.fragment_make_case, container, false);
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
