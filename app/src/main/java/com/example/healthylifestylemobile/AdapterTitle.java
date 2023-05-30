package com.example.healthylifestylemobile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterTitle extends BaseAdapter {
    private Context mContext;

    public AdapterTitle(Context mContext, List<RecepesModel> maskList) {
        this.mContext = mContext;
        this.maskList = maskList;
    }

    List<RecepesModel> maskList;


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
        return maskList.get(i).getRecipeId();
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext,R.layout.item_titlerecipes,null);

        TextView Title = v.findViewById(R.id.Title);


        RecepesModel mask = maskList.get(position);
        Title.setText(mask.getTitle());


        return v;
    }
}
