package com.example.healthylifestylemobile;

import org.json.JSONObject;

public class DairyModel {
    private int DailyRationId;
    private int UserId;
    private int RecepeId;
    private JSONObject Dates;
    private double Calories;
    private double Squirrels;
    private double Fats;
    private double Carbohydrates;
    private double CaloriesUsers;
    private int MealId;

    public DairyModel(int DailyRationId,
                             int UserId, int RecepeId,
                      JSONObject Dates, double Calories,
                             double Squirrels,
                             double Fats, double Carbohydrates,
                             double CaloriesUsers,
                             int MealId) {
        this.DailyRationId = DailyRationId;
        this.UserId = UserId;
        this.RecepeId = RecepeId;
        this.Dates = Dates;
        this.Calories = Calories;
        this.Squirrels = Squirrels;

        this.Fats = Fats;
        this.Carbohydrates = Carbohydrates;
        this.CaloriesUsers = CaloriesUsers;
        this.MealId = MealId;
    }

    public void setDailyRationId(int DailyRationId)
    {
        this.DailyRationId = DailyRationId;
    }
    public void setUserId(int UserId)
    {
        this.UserId = UserId;
    }
    public void setRecepeId(int RecepeId)
    {
        this.RecepeId = RecepeId;
    }
    public void setDate(JSONObject Dates)
    {
        this.Dates = Dates;
    }
    public void setCalories(double Calories)
    {
        this.Calories = Calories;
    }
    public void setSquirrels(double Squirrels)
    {
        this.Squirrels = Squirrels;
    }
    public void setFats(double Fats)
    {
        this.Fats = Fats;
    }
    public void setCarbohydrates(double Carbohydrates)
    {
        this.Carbohydrates = Carbohydrates;
    }
    public void setCaloriesUsers(double CaloriesUsers)
    {
        this.CaloriesUsers = CaloriesUsers;
    }
    public void setMealId(int MealId)
    {
        this.MealId = MealId;
    }


    public int getDailyRationId()
    {
        return DailyRationId;
    }
    public int getUserId()
    {
        return UserId;
    }
    public int getRecepeId()
    {
        return RecepeId;
    }
    public JSONObject getDate()
    {
        return Dates;
    }
    public double getCalories()
    {
        return Calories;
    }
    public double getSquirrels()
    {
        return Squirrels;
    }
    public double getFats()
    {
        return Fats;
    }
    public double getCarbohydrates()
    {
        return Carbohydrates;
    }
    public double getCaloriesUsers()
    {
        return CaloriesUsers;
    }
    public int getMealId()
    {
        return MealId;
    }
}
