package com.emptytrip.hr.emptytrip;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.onegravity.contactpicker.contact.Contact;
import com.onegravity.contactpicker.core.ContactPickerActivity;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProtectMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProtectMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProtectMenuFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ArrayList<ProtectContactModel> allContacts = new ArrayList<ProtectContactModel>();

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    int REQUEST_CONTACT = 0;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProtectMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProtectMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProtectMenuFragment newInstance(String param1, String param2) {
        ProtectMenuFragment fragment = new ProtectMenuFragment();
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
        return inflater.inflate(R.layout.fragment_protect_menu, container, false);
    }

    private static final String EXTRA_DARK_THEME = "EXTRA_DARK_THEME";
    private static final String EXTRA_GROUPS = "EXTRA_GROUPS";
    private static final String EXTRA_CONTACTS = "EXTRA_CONTACTS";

    private boolean mDarkTheme;
    private List<Contact> mContacts;
    private List<Group> mGroups;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

// Font initialize

        Typeface Gotham_Medium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Gotham-Medium.otf");
        Typeface roboto_Regular = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface roboto_Medium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Medium.ttf");
        Typeface roboto_Black = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Black.ttf");
        Typeface roboto_Bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf");


        TextView tv_title = (TextView)view.findViewById(R.id.tv_title);
        TextView tv_note = (TextView)view.findViewById(R.id.tv_note);
        Button btn_phonecontact = (Button)view.findViewById(R.id.btn_phonecontact);
        Button btn_emailcontact = (Button)view.findViewById(R.id.btn_emailcontact);
        Button btn_facebook = (Button)view.findViewById(R.id.btn_facebook);
        Button btn_manually = (Button)view.findViewById(R.id.btn_manually);
        Button btn_howitworks = (Button)view.findViewById(R.id.btn_howitworks);

        tv_title.setTypeface(roboto_Black);
        tv_note.setTypeface(roboto_Regular);
        btn_phonecontact.setTypeface(roboto_Regular);
        btn_emailcontact.setTypeface(roboto_Regular);
        btn_facebook.setTypeface(roboto_Regular);
        btn_manually.setTypeface(roboto_Regular);
        btn_howitworks.setTypeface(roboto_Black);

        if (savedInstanceState != null) {
            mDarkTheme = savedInstanceState.getBoolean(EXTRA_DARK_THEME);
            mGroups = (List<Group>) savedInstanceState.getSerializable(EXTRA_GROUPS);
            mContacts = (List<Contact>) savedInstanceState.getSerializable(EXTRA_CONTACTS);
        }
        else {
            Intent intent = getActivity().getIntent();
            mDarkTheme = intent.getBooleanExtra(EXTRA_DARK_THEME, false);
            mGroups = (List<Group>) intent.getSerializableExtra(EXTRA_GROUPS);
            mContacts = (List<Contact>) intent.getSerializableExtra(EXTRA_CONTACTS);
        }
        btn_emailcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new SelectEmailPageFragment());
            }
        });

        btn_phonecontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadFragment(new ProtectedPhoneFragment());

                if (Build.VERSION.SDK_INT >= 23) {
                    String[] PERMISSIONS = {android.Manifest.permission.READ_CONTACTS};
                    if (!hasPermissions(getContext(), PERMISSIONS)) {
                        ActivityCompat.requestPermissions((Activity) getContext(), PERMISSIONS, 112 );
                    } else {
                        getContactsListView(getActivity().getContentResolver());
                    }
                } else {
                    getContactsListView(getActivity().getContentResolver());
                }


            }


        });

        btn_manually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ProtectedManuallyFragment());
            }
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CONTACT && resultCode == Activity.RESULT_OK && data != null &&
                (data.hasExtra(ContactPickerActivity.RESULT_GROUP_DATA) ||
                        data.hasExtra(ContactPickerActivity.RESULT_CONTACT_DATA))) {

            // we got a result from the contact picker --> show the picked contacts
//            mGroups = (List<Group>) data.getSerializableExtra(ContactPickerActivity.RESULT_GROUP_DATA);
//            mContacts = (List<Contact>) data.getSerializableExtra(ContactPickerActivity.RESULT_CONTACT_DATA);
//            populateContactList(mGroups, mContacts);
        }
    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    public void getContactsListView(ContentResolver cr)
    {
        try {
            Cursor contacts = cr.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null);
            this.allContacts.clear();
            if (contacts.getCount() > 0) {
                while (contacts.moveToNext()) {

                    ProtectContactModel protectContact = new ProtectContactModel();

                    int id = Integer.parseInt(contacts.getString(contacts.getColumnIndex(
                            ContactsContract.Contacts._ID)));
                    protectContact.setId(id);

                    String name = contacts.getString(contacts.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME));
                    protectContact.setName(name);

                    // Get phone
                    int hasPhones = Integer.parseInt(contacts.getString(contacts.getColumnIndex(
                            ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                    if (hasPhones > 0) {
                        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[] {String.valueOf(id)},
                                null);
                        while (phones.moveToNext()) {
                            String phone = phones.getString(phones.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER));
                            protectContact.setPhone(phone);
                            break;
                        }
                        phones.close();
                    }


                    // Get email
                    Cursor emails = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                            new String[] {String.valueOf(id)},
                            null);
                    while (emails.moveToNext()) {
                        String email = emails.getString(emails.getColumnIndex(
                                ContactsContract.CommonDataKinds.Email.DATA));
                        protectContact.setEmail(email);
                        break;
                    }
                    emails.close();

                    allContacts.add(protectContact);
                    Log.i("ProtectContacts", protectContact.toString());
                }
            }
            contacts.close();

            EmptyTripApplication emptyTripApplication = new EmptyTripApplication();
            emptyTripApplication.setProtectContacts(allContacts);
            emptyTripApplication.setProtectModeStr("phone");
            loadFragment(new ContactListFragment());

//            if (allContacts.size() > 0) {
//                adapter = new ProtectContactsListViewAdapter(this, allContacts, selectedContacts);
//                listView.setAdapter(adapter);
//
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        ProtectContactModel dataModel = allContacts.get(i);
//
//                        if (selectedContacts.contains(dataModel)) {
//
//                            selectedContacts.remove(dataModel);
//                            listView.findViewWithTag(i).setBackground(getDrawable(R.drawable.ic_checkbox));
//
//                            selectAllButton.setBackground(getDrawable(R.drawable.ic_checkbox));
//                            selectAllText.setText("Select All");
//                            selectAllSelected = false;
//
//                        } else {
//
//                            selectedContacts.add(dataModel);
//                            listView.findViewWithTag(i).setBackground(getDrawable(R.drawable.ic_checkbox_checked));
//
//                        }
//                    }
//                });
//            }

            //display contact numbers in the list
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ProtectContacts Error", e.getLocalizedMessage());

            Toast.makeText(getContext(), "Error: Unable to fetch contacts", Toast.LENGTH_SHORT).show();
        }
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
