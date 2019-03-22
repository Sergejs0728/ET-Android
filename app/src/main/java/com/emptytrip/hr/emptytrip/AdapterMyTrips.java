package com.emptytrip.hr.emptytrip;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by navi on 07/03/2018.
 */

public class AdapterMyTrips extends BaseAdapter{

    Context context;
    private static LayoutInflater inflater = null;

    public AdapterMyTrips(Context context){
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        View vi = view;
        if(vi == null){
            vi = inflater.inflate(R.layout.item_mytrips,null);

        }

        Typeface Gotham_Medium = Typeface.createFromAsset(context.getAssets(), "fonts/Gotham-Medium.otf");
        Typeface roboto_Regular = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface roboto_Medium = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
        Typeface roboto_Black = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Black.ttf");
        Typeface roboto_Bold = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");


        TextView tv_price = (TextView)vi.findViewById(R.id.tv_price);
        TextView tv_date = (TextView)vi.findViewById(R.id.tv_date);
        TextView tv_start = (TextView)vi.findViewById(R.id.tv_start);
        TextView tv_end = (TextView)vi.findViewById(R.id.tv_end);
        TextView tv_view = (TextView)vi.findViewById(R.id.tv_view);

        tv_price.setTypeface(roboto_Black);
        tv_date.setTypeface(roboto_Medium);
        tv_start.setTypeface(roboto_Regular);
        tv_end.setTypeface(roboto_Regular);
        tv_view.setTypeface(roboto_Bold);

        return vi;
    }
}
