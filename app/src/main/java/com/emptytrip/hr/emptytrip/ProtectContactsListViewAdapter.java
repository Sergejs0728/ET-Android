package com.emptytrip.hr.emptytrip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by navi on 09/07/2018.
 */

public class ProtectContactsListViewAdapter extends ArrayAdapter<ProtectContactModel> implements View.OnClickListener{
    private ArrayList<ProtectContactModel> dataSet;
    private ArrayList<ProtectContactModel> selectedDataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView nameText;
        Button selectButton;
    }

    public ProtectContactsListViewAdapter(Context context, ArrayList<ProtectContactModel> data,
                                          ArrayList<ProtectContactModel> selectedData) {
        super(context, R.layout.protect_contacts_list_item, data);
        this.dataSet = data;
        this.selectedDataSet = selectedData;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position = (Integer) v.getTag();
        Object object = getItem(position);
        ProtectContactModel dataModel = (ProtectContactModel) object;

//        switch (v.getId()) {
//            case R.id.selectImageView:
//                //Utility.showToast(mContext, "Name: " + dataModel.name, Toast.LENGTH_SHORT);
//                if (this.selectedDataSet.contains(dataModel)) {
//
//                    this.selectedDataSet.remove(dataModel);
////                    v.setBackground(mContext.getDrawable(R.drawable.ic_checkbox));
//                    v.setBackgroundResource(R.drawable.ic_checkbox);
//                } else {
//
//                    this.selectedDataSet.add(dataModel);
////                    v.setBackground(mContext.getDrawable(R.drawable.ic_checkbox_checked));
//                    v.setBackgroundResource(R.drawable.ic_checkbox_checked);
//
//                }
//                break;
//        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.protect_contacts_list_item, parent,false);
        }

        // Get the data item for this position
        ProtectContactModel currentContact = getItem(position);

        TextView contactName = listItem.findViewById(R.id.nameText);
        contactName.setText(currentContact.getName());

        TextView selectButton = listItem.findViewById(R.id.selectImageView);
        if (selectedDataSet.contains(currentContact)) {
//            selectButton.setBackground(mContext.getDrawable(R.drawable.ic_checkbox_checked));
            selectButton.setBackgroundResource(R.drawable.ic_checkbox_checked);
        } else {
//            selectButton.setBackground(mContext.getDrawable(R.drawable.ic_checkbox));
            selectButton.setBackgroundResource(R.drawable.ic_checkbox);
        }

        selectButton.setOnClickListener(this);
        selectButton.setTag(position);

        listItem.setTag(currentContact.getId());

        return listItem;
    }
}
