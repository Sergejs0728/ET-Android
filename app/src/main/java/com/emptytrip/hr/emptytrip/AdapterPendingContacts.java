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

public class AdapterPendingContacts extends BaseAdapter{

    Context context;
    private static LayoutInflater inflater = null;

    public AdapterPendingContacts(Context context){
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
            vi = inflater.inflate(R.layout.item_pending,null);

        }
        TextView tv_caseName = (TextView)vi.findViewById(R.id.tv_name);
        TextView tv_pending = (TextView)vi.findViewById(R.id.tv_pending);
        TextView tv_address = (TextView)vi.findViewById(R.id.tv_address);
        TextView btn_remind = (TextView)vi.findViewById(R.id.btn_remind);

        Typeface Gotham_Medium = Typeface.createFromAsset(context.getAssets(), "fonts/Gotham-Medium.otf");
        Typeface roboto_Regular = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface roboto_Medium = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
        Typeface roboto_Black = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Black.ttf");
        Typeface roboto_Bold = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");

        tv_caseName.setTypeface(roboto_Regular);
        tv_pending.setTypeface(roboto_Bold);
        tv_address.setTypeface(roboto_Regular);
        btn_remind.setTypeface(roboto_Medium);

        return vi;
    }
}
