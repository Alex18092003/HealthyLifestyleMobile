package com.example.healthylifestylemobile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterRecepes extends BaseAdapter {

    private Context mContext;

    public AdapterRecepes(Context mContext, List<RecepesModel> maskList) {
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


    private Bitmap getUserImage(String encodedImg) // Преобразование из String в Bitmap
    {

        if(encodedImg!=null&& !encodedImg.equals("null")) {
            byte[] bytes = Base64.decode(encodedImg, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        else
            return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext,R.layout.item_recepes,null);

        TextView Title = v.findViewById(R.id.Title);


        RecepesModel mask = maskList.get(position);
        Title.setText(mask.getTitle());


        return v;
    }
}
