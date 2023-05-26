package com.example.healthylifestylemobile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterDairy extends BaseAdapter {
    private Context mContext;
    List<DailyRationsModel> maskList;

    public AdapterDairy(Context mContext, List<DailyRationsModel> maskList) {
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
        return maskList.get(i).getDailyRationId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext,R.layout.item_breakfast,null);


        TextView Calories= v.findViewById(R.id.Calories);
        TextView Squirrels= v.findViewById(R.id.Squirrels);
        TextView Fats= v.findViewById(R.id.Fats);
        TextView Carbohydrates= v.findViewById(R.id.Carbohydrates);



        DailyRationsModel dailyRationsModel = maskList.get(position);

        Squirrels.setText(String.valueOf(dailyRationsModel.getSquirrels()));
        Fats.setText(String.valueOf(dailyRationsModel.getFats()));
        Carbohydrates.setText(String.valueOf(dailyRationsModel.getCarbohydrates()));
        Calories.setText(String.valueOf(dailyRationsModel.getCalories()));

        return v;
    }

}
