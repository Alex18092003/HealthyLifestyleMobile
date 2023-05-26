package com.example.healthylifestylemobile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterSteps extends BaseAdapter {

    private Context mContext;
    List<StepsModel> maskList;

    public AdapterSteps(Context mContext, List<StepsModel> maskList) {
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
        return maskList.get(i).getStepId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext,R.layout.items_steps,null);

        TextView NomStep = v.findViewById(R.id.NomStep);
        TextView Descr= v.findViewById(R.id.Descr);
        ImageView picture= v.findViewById(R.id.picture);


        StepsModel stepsModel = maskList.get(position);
        NomStep.setText(String.valueOf(stepsModel.getStepNomen()));
        Descr.setText(String.valueOf(stepsModel.getDescription()));
        if(stepsModel.getPhotoAnd().toString().equals("null"))
        {
            picture.setImageResource(R.drawable.wing);
        }
        else
        {
            picture.setImageBitmap(getUserImage(stepsModel.getPhotoAnd()));
        }

        return v;
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
}
