package com.emptytrip.hr.emptytrip;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by navi on 14/03/2018.
 */

public class MyMapFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup vg,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_map, vg, false);
    }
}