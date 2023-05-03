package com.example.healthylifestylemobile;

public class UserModel {

    private int UserId;
    private int GenderId;
    private String Login;
    private float Weight;
    private float Height;
    private int ActivityId;
    private int GoalId;
    private float Calories;
    private float Squirrels;
    private int DateOfBirth;
    private String Password;
    private float Fats;
    private float Carbohydrates;


    public UserModel(int UserId, int GenderId, String Login, float Weight,
                     float Height, int ActivityId, int GoalId, float Calories, float Squirrels,
                     int DateOfBirth, String Password, float Fats, float Carbohydrates) {
        this.UserId = UserId;
        this.GenderId = GenderId;
        this.Login = Login;
        this.Weight = Weight;
        this.Height = Height;
        this.ActivityId = ActivityId;
        this.GoalId = GoalId;
        this.Calories = Calories;
        this.Squirrels = Squirrels;
        this.DateOfBirth = DateOfBirth;
        this.Password = Password;
        this.Fats = Fats;
        this.Carbohydrates = Carbohydrates;

    }



    public void setUserId(int UserId)
    {
        this.UserId = UserId;
    }
    public void setGenderId(int GenderId)
    {
        this.GenderId = GenderId;
    }
    public void setLogin(String Login) {
        this.Login = Login;
    }
    public void setWeight(float Weight)
    {
        this.Weight = Weight;
    }
    public void setHeight(float Height)
    {
        this.Height = Height;
    }
    public void setActivityId(int ActivityId)
    {
        this.ActivityId = ActivityId;
    }
    public void setGoalId(int GoalId)
    {
        this.GoalId = GoalId;
    }
    public void setCalories(float Calories)
    {
        this.Calories = Calories;
    }
    public void setSquirrels(float Squirrels)
    {
        this.Squirrels = Squirrels;
    }
    public void setDateOfBirth(int DateOfBirth)
    {
        this.DateOfBirth = DateOfBirth;
    }
    public void setPassword(String Password) {
        this.Password = Password;
    }
    public void setFats(float Fats)
    {
        this.Fats = Fats;
    }
    public void setCarbohydrates(float Carbohydrates)
    {
        this.Carbohydrates = Carbohydrates;
    }


    public int getUserId()
    {
        return UserId;
    }
    public int getGenderId()
    {
        return GenderId;
    }
    public String getLogin() {
        return Login;
    }
    public float getWeight()
    {
        return Weight;
    }
    public float getHeight()
    {
        return Height;
    }
    public int getActivityId()
    {
        return ActivityId;
    }
    public int getGoalId()
    {
        return GoalId;
    }
    public float getCalories()
    {
        return Calories;
    }
    public float getSquirrels()
    {
        return Squirrels;
    }
    public int getDateOfBirth()
    {
        return DateOfBirth;
    }
    public String getPassword() {
        return Password;
    }
    public float getFats()
    {
        return Fats;
    }
    public float getCarbohydrates()
    {
        return Carbohydrates;
    }

}
