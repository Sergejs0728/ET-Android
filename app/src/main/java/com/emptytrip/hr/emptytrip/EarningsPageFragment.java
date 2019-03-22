package com.emptytrip.hr.emptytrip;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EarningsPageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EarningsPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EarningsPageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public ViewPagerAdapter adapter;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public ViewPager viewPager;
    private OnFragmentInteractionListener mListener;

    public EarningsPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EarningsPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EarningsPageFragment newInstance(String param1, String param2) {
        EarningsPageFragment fragment = new EarningsPageFragment();
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

//        Toast.makeText(getContext(), "asdf", Toast.LENGTH_SHORT).show();

        View view = getView();




    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        Typeface Gotham_Medium = Typeface.createFromAsset(getContext().getAssets(),"fonts/Gotham-Medium.otf");
        Typeface roboto_Regular = Typeface.createFromAsset(getContext().getAssets(),"fonts/Roboto-Regular.ttf");
        Typeface roboto_Medium = Typeface.createFromAsset(getContext().getAssets(),"fonts/Roboto-Medium.ttf");
        Typeface roboto_Black = Typeface.createFromAsset(getContext().getAssets(),"fonts/Roboto-Black.ttf");
        Typeface roboto_Bold = Typeface.createFromAsset(getContext().getAssets(),"fonts/Roboto-Bold.ttf");

        TextView tv_price = (TextView)view.findViewById(R.id.tv_price);
        TextView tv_main = (TextView)view.findViewById(R.id.tv_main);

        tv_price.setTypeface(roboto_Black);
        tv_main.setTypeface(roboto_Bold);

//Initializing viewPager
        viewPager = (ViewPager)view.findViewById(R.id.earningViewpager);
        viewPager.setOffscreenPageLimit(3);
        setupViewPager(viewPager);

//Initializing the tablayout

        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

//
//        Button btn_back = (Button)view.findViewById(R.id.btn_back);
//        btn_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loadFragment(new EarningsPageFragment());
//            }
//        });

        ((EmptyTripMainActivity)getActivity()).fl_pager = (FrameLayout)view.findViewById(R.id.fl_pager);

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//                ((EmptyTripMainActivity)getActivity()).fl_pager.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((EmptyTripMainActivity)getActivity()).fl_pager.setVisibility(View.GONE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                ((EmptyTripMainActivity)getActivity()).fl_pager.setVisibility(View.GONE);
            }
        });

    }



    private void setupViewPager(ViewPager viewPager)
    {
        adapter = new ViewPagerAdapter(getChildFragmentManager());

        Fragment totalPage=new EarningTotalPageFragment();
        Fragment allContactsFragment=new AllContactsFragment();
        Fragment affiliatePageFragment=new AffiliateContentFragment();
        adapter.addFragment(totalPage,"TOTAL");
        adapter.addFragment(allContactsFragment,"CONTACTS");
        adapter.addFragment(affiliatePageFragment,"AFFLIATE");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void loadFragment(Fragment fragment) {
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
        return inflater.inflate(R.layout.fragment_earnings_page, container, false);
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
