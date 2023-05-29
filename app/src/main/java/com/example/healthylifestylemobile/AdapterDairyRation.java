package com.example.healthylifestylemobile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterDairyRation extends BaseAdapter {
    private Context mContext;
    List<DairyModel> maskList;
    public AdapterDairyRation(Context mContext, List<DairyModel> maskList) {
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



        DairyModel dailyRationsModel = maskList.get(position);

        float ss = Float.parseFloat(String.valueOf(dailyRationsModel.getSquirrels()));
        Squirrels.setText(String.valueOf(ss));
        float sss = Float.parseFloat(String.valueOf(dailyRationsModel.getFats()));
        Fats.setText(String.valueOf(sss));
        float s = Float.parseFloat(String.valueOf(dailyRationsModel.getCarbohydrates()));
        Carbohydrates.setText(String.valueOf(s));
        float sw = Float.parseFloat(String.valueOf(dailyRationsModel.getCalories()));
        Calories.setText(String.valueOf(sw));

        return v;
    }
}
