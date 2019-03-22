package com.emptytrip.hr.emptytrip;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EarningTotalPageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EarningTotalPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EarningTotalPageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EarningTotalPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EarningTotalPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EarningTotalPageFragment newInstance(String param1, String param2) {
        EarningTotalPageFragment fragment = new EarningTotalPageFragment();
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
    public void onResume() {
        super.onResume();

        View view = getView();

        ImageButton btn_donate = (ImageButton)view.findViewById(R.id.btn_donate);
        ImageButton btn_withdraw = (ImageButton)view.findViewById(R.id.btn_withdraw);
        ImageButton btn_transfer = (ImageButton)view.findViewById(R.id.btn_transfer);
        ImageButton btn_contacts = (ImageButton)view.findViewById(R.id.btn_contacts);
        ImageButton btn_affiliate = (ImageButton)view.findViewById(R.id.btn_affiliate);
        ImageButton btn_protected = (ImageButton)view.findViewById(R.id.btn_protected);


        btn_donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                loadFragment(new DonateFragment());

                changeFragment(new DonateFragment());

            }
        });

        btn_withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                loadFragment(new WithdrawAmountFragment());
                changeFragment(new WithdrawAmountFragment());
            }
        });


        btn_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                loadFragment(new TransferAmountFragment());
                changeFragment(new TransferPageFragment());
            }
        });

        btn_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new ContactPageFragment());
            }
        });

        btn_affiliate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new AffiliatePageFragment());
            }
        });

        btn_protected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new ProtectMenuFragment());
            }
        });

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        ((EmptyTripMainActivity)getActivity()).fl_pager.removeAllViews();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_pager, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
        ((EmptyTripMainActivity)getActivity()).fl_pager.setVisibility(View.VISIBLE);
    }
    public void changeFragment(Fragment fragment) {
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
        return inflater.inflate(R.layout.fragment_earning_total_page, container, false);
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
