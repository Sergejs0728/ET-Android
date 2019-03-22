package com.emptytrip.hr.emptytrip;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by navi on 07/03/2018.
 */

public class AdapterMyTripsDetails extends BaseAdapter{
    GoogleMap map;
    Context context;
    private static LayoutInflater inflater = null;
    private GoogleMap mMap;
    Bundle savedInstanceState;

    static final LatLng HAMBURG = new LatLng(53.558, 9.927);

    public AdapterMyTripsDetails(Context context, Bundle b){
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.savedInstanceState = b;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Typeface Gotham_Medium = Typeface.createFromAsset(context.getAssets(), "fonts/Gotham-Medium.otf");
        Typeface roboto_Regular = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface roboto_Medium = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
        Typeface roboto_Black = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Black.ttf");
        Typeface roboto_Bold = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");

        View vi;

        vi = inflater.inflate(R.layout.item_mytripdetails, null);

        TextView tv_departure = (TextView)vi.findViewById(R.id.tv_departure);
        TextView tv_arrival = (TextView)vi.findViewById(R.id.tv_arrival);
        TextView tv_name = (TextView)vi.findViewById(R.id.tv_name);
        TextView tv_vechicle = (TextView)vi.findViewById(R.id.tv_vechicle);
        TextView tv_price = (TextView)vi.findViewById(R.id.tv_price);
        TextView tv_mark = (TextView)vi.findViewById(R.id.tv_mark);

        tv_departure.setTypeface(roboto_Medium);
        tv_arrival.setTypeface(roboto_Bold);
        tv_name.setTypeface(roboto_Bold);
        tv_vechicle.setTypeface(roboto_Bold);
        tv_price.setTypeface(roboto_Regular);
        tv_mark.setTypeface(roboto_Regular );







        return vi;

    }

}
