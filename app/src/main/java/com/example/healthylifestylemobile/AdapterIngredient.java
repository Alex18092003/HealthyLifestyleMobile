package com.example.healthylifestylemobile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterIngredient extends BaseAdapter {

    private Context mContext;
    List<IngrediensModel> maskList;

    public AdapterIngredient(Context mContext, List<IngrediensModel> maskList) {
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
        return maskList.get(i).getIngredientId();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext,R.layout.item_ing,null);

        TextView title = v.findViewById(R.id.Title);

        IngrediensModel ingrediensModel = maskList.get(position);
        title.setText(ingrediensModel.getTitle());

        return v;
    }
}
