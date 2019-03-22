package com.emptytrip.hr.emptytrip;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by navi on 08/03/2018.
 */

public class AdapterTripsArchive extends BaseAdapter{
    Context context;
    private static LayoutInflater inflater = null;

    public AdapterTripsArchive(Context context){
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
            vi = inflater.inflate(R.layout.item_tripsarchive,null);

        }
        TextView tv_price = (TextView)vi.findViewById(R.id.tv_price);
        TextView tv_date = (TextView)vi.findViewById(R.id.tv_date);
        TextView tv_mylocation = (TextView)vi.findViewById(R.id.tv_mylocation);
        TextView tv_destination = (TextView)vi.findViewById(R.id.tv_destination);

        Typeface Gotham_Medium = Typeface.createFromAsset(context.getAssets(), "fonts/Gotham-Medium.otf");
        Typeface roboto_Regular = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface roboto_Medium = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
        Typeface roboto_Black = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Black.ttf");
        Typeface roboto_Bold = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");

        tv_price.setTypeface(roboto_Regular);
        tv_date.setTypeface(roboto_Regular);
        tv_mylocation.setTypeface(roboto_Regular);
        tv_destination.setTypeface(roboto_Regular);

        return vi;
    }
}
