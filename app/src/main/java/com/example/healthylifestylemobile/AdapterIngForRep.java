package com.example.healthylifestylemobile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterIngForRep extends BaseAdapter {
    private Context mContext;
    List<IngredientForRecipeModel> maskList;

    public AdapterIngForRep(Context mContext, List<IngredientForRecipeModel> maskList) {
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
        return maskList.get(i).getIngredientForRecipeId();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext,R.layout.items_ingredients,null);


        TextView Quantity= v.findViewById(R.id.Quantity);



        IngredientForRecipeModel ingredientForRecipeModel = maskList.get(position);

        Quantity.setText(String.valueOf(ingredientForRecipeModel.getQuantity()));

        return v;
    }
}
