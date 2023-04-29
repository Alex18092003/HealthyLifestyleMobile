package com.example.healthylifestylemobile;

import android.os.Parcel;
import android.os.Parcelable;

public class MaskActivities implements Parcelable {

    private int id;
    private String title;

    private float coefficient;

    public MaskActivities(int id, String title, float coefficient) {
        this.id = id;
        this.title = title;
        this.coefficient = coefficient;
    }

    protected MaskActivities(Parcel in) {
        id = in.readInt();
        title = in.readString();
        coefficient = in.readFloat();
    }

    public static final Creator<MaskActivities> CREATOR = new Creator<MaskActivities>() {
        @Override
        public MaskActivities createFromParcel(Parcel in) {
            return new MaskActivities(in);
        }

        @Override
        public MaskActivities[] newArray(int size) {
            return new MaskActivities[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getCoefficient() {
        return coefficient;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeFloat(coefficient);
    }

}
