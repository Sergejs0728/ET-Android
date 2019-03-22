package com.emptytrip.hr.emptytrip;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by navi on 07/03/2018.
 */

public class AdapterMyCaseDetails extends BaseAdapter{
    ImageButton btn_sendbutton;
    View vi2,vi;
    MyCaseDetailsFragment mycaseDetailsFragment;
    Context context;
    private static LayoutInflater inflater = null;

    public AdapterMyCaseDetails(Context context,MyCaseDetailsFragment fragment){
        this.context = context;
        mycaseDetailsFragment = fragment;

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



        Typeface Gotham_Medium = Typeface.createFromAsset(context.getAssets(), "fonts/Gotham-Medium.otf");
        Typeface roboto_Regular = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface roboto_Medium = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
        Typeface roboto_Black = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Black.ttf");
        Typeface roboto_Bold = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");






        if(i==9){
                vi2 = inflater.inflate(R.layout.item_casedetails2,null);

            btn_sendbutton = (ImageButton)vi2.findViewById(R.id.btn_sendbutton);
            btn_sendbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mycaseDetailsFragment.loadFragment(new MyCaseDoneFragment());

                }
            });

            TextView tv_note = (TextView)vi2.findViewById(R.id.tv_note);

            EditText et_details = (EditText)vi2.findViewById(R.id.et_details);

            tv_note.setTypeface(roboto_Regular);
            et_details.setTypeface(roboto_Regular);


            return vi2;
        }else{


                vi = inflater.inflate(R.layout.item_casesdetails1,null);

            ImageView iv_photo = (ImageView)vi.findViewById(R.id.iv_photo);
            TextView tv_text1 = (TextView)vi.findViewById(R.id.tv_text1);
            tv_text1.setTypeface(roboto_Regular);

            return vi;
        }

    }

}
