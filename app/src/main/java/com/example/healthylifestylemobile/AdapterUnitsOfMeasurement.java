package com.example.healthylifestylemobile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterUnitsOfMeasurement extends BaseAdapter  {
    private Context mContext;
    List<UnitsOfMeasurementModel> maskList;

    public AdapterUnitsOfMeasurement(Context mContext, List<UnitsOfMeasurementModel> maskList) {
        this.mContext = mContext;
        this.maskList = maskList;
    }

    @Override
    public int getCount() {
        return maskList.size();
    }


    @Override
    public Object getItem(int i) {
        return maskList.get(i);
    }


    @Override
    public long getItemId(int i)
    {
        return maskList.get(i).getUnitsOfMeasurementId();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext,R.layout.items_units,null);

        TextView title = v.findViewById(R.id.Title);

        UnitsOfMeasurementModel unitsOfMeasurementModel = maskList.get(position);
        title.setText(String.valueOf(unitsOfMeasurementModel.getTitle()));

        return v;
    }
}
