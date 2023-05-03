package com.example.healthylifestylemobile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterMaskGoal extends BaseAdapter {

    private Context mContext;
    private List<MaskGoal> maskList;

    public AdapterMaskGoal(Context mContext, List<MaskGoal> listProduct) {
        this.mContext = mContext;
        this.maskList = listProduct;
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
        return maskList.get(i).getGoalId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext,R.layout.item_goal,null);
        TextView Title = v.findViewById(R.id.Title);
        MaskGoal maskGoal = maskList.get(position);
        Title.setText(maskGoal.getTitle());

        return v;
    }


}
