package com.example.healthylifestylemobile;

import android.os.Parcel;
import android.os.Parcelable;

public class MaskActivities implements Parcelable {

    private int ActivityId;
    private String Title;

    private double Coefficient;

    public MaskActivities(int ActivityId, String Title, double Coefficient) {
        this.ActivityId = ActivityId;
        this.Title = Title;
        this.Coefficient = Coefficient;
    }

    protected MaskActivities(Parcel in) {
        ActivityId = in.readInt();
        Title = in.readString();
        Coefficient = in.readDouble();
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

    public int getActivityId() {
        return ActivityId;
    }

    public String getTitle() {
        return Title;
    }

    public double getCoefficient() {
        return Coefficient;
    }



    public void setActivityId(int ActivityId) {
        this.ActivityId = ActivityId;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setCoefficient(double Coefficient) {
        this.Coefficient = Coefficient;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ActivityId);
        dest.writeString(Title);
        dest.writeDouble(Coefficient);
    }

}
