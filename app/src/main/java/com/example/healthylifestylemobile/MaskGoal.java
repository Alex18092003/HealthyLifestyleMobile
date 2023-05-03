package com.example.healthylifestylemobile;

import android.os.Parcel;
import android.os.Parcelable;

public class MaskGoal implements Parcelable {
    private int GoalId;
    private String Title;


    public MaskGoal(int GoalId, String Title) {
        this.GoalId = GoalId;
        this.Title = Title;
    }

    protected MaskGoal(Parcel in) {
        GoalId = in.readInt();
        Title = in.readString();
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

    public int getGoalId() {
        return GoalId;
    }

    public String getTitle() {
        return Title;
    }



    public void setGoalId(int GoalId) {
        this.GoalId = GoalId;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(GoalId);
        dest.writeString(Title);
    }
}
