package com.example.healthylifestylemobile;

import android.os.Parcel;
import android.os.Parcelable;

public class MaskGoal implements Parcelable {
    private int id;
    private String title;


    public MaskGoal(int id, String title) {
        this.id = id;
        this.title = title;
    }

    protected MaskGoal(Parcel in) {
        id = in.readInt();
        title = in.readString();
    }

    public static final Creator<MaskGoal> CREATOR = new Creator<MaskGoal>() {
        @Override
        public MaskGoal createFromParcel(Parcel in) {
            return new MaskGoal(in);
        }

        @Override
        public MaskGoal[] newArray(int size) {
            return new MaskGoal[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
    }
}
