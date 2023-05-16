package com.example.healthylifestylemobile;

public class RecepesModel {

    private int RecipeId;
    private String Title;
    private int MinutesOfCooking;
    private String Description;
    private String Comment;
    private String Photo;
    private int MealId;
    private int RecipeType;
    private int DietId;
    private int SpecificsId;
    private int DifficultyId;
    private int CookingId;
    private int KitchenId;
    private double Calories;
    private double Squirrels;
    private double Fats;
    private double Carbohydrates;
    private String PhotoAnd;

    public RecepesModel(int RecipeId, String Title,
                        int MinutesOfCooking, String Description,
                        String Comment, String Photo,
                        int MealId, int RecipeType,
                        int DietId,
                        int SpecificsId, int DifficultyId,
                        int CookingId, int KitchenId,
                        double Calories , double Squirrels,
                        double Fats, double Carbohydrates, String PhotoAnd) {
        this.RecipeId = RecipeId;
        this.Title = Title;
        this.MinutesOfCooking = MinutesOfCooking;
        this.Description = Description;
        this.Comment = Comment;
        this.Photo = Photo;
        this.MealId = MealId;
        this.RecipeType = RecipeType;
        this.DietId = DietId;
        this.SpecificsId = SpecificsId;
        this.DifficultyId = DifficultyId;
        this.CookingId = CookingId;
        this.KitchenId = KitchenId;
        this.Calories = Calories;
        this.Squirrels = Squirrels;
        this.Fats = Fats;
        this.Carbohydrates = Carbohydrates;
        this.PhotoAnd = PhotoAnd;
    }

    public void setRecipeId(int RecipeId)
    {
        this.RecipeId = RecipeId;
    }
    public void setTitle(String Title)
    {
        this.Title = Title;
    }
    public void setMinutesOfCooking(int MinutesOfCooking)
    {
        this.MinutesOfCooking = MinutesOfCooking;
    }
    public void setDescription(String Description)
    {
        this.Description = Description;
    }
    public void setComment(String Comment)
    {
        this.Comment = Comment;
    }
    public void setPhoto(String Photo)
    {
        this.Photo = Photo;
    }
    public void setMealId(int MealId)
    {
        this.MealId = MealId;
    }
    public void setRecipeType(int RecipeType)
    {
        this.RecipeType = RecipeType;
    }
    public void setDietId(int DietId)
    {
        this.DietId = DietId;
    }
    public void setSpecificsId(int SpecificsId)
    {
        this.SpecificsId = SpecificsId;
    }
    public void setDifficultyId(int DifficultyId)
    {
        this.DifficultyId = DifficultyId;
    }
    public void setCookingId(int CookingId)
    {
        this.CookingId = CookingId;
    }
    public void setKitchenId(int KitchenId)
    {
        this.KitchenId = KitchenId;
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
    public void setPhotoAnd(String PhotoAnd)
    {
        this.PhotoAnd = PhotoAnd;
    }


    public int getRecipeId()
    {
        return RecipeId;
    }
    public String getTitle()
    {
        return Title;
    }
    public int getMinutesOfCooking()
    {
        return MinutesOfCooking;
    }
    public String getDescription()
    {
        return Description;
    }
    public String getComment()
    {
        return Comment;
    }
    public String getPhoto()
    {
        return Photo;
    }
    public int getRecipeType()
    {
        return RecipeType;
    }
    public int getMealId()
    {
        return MealId;
    }
    public int getDietId()
    {
        return DietId;
    }
    public int getSpecificsId()
    {
        return SpecificsId;
    }
    public int getDifficultyId()
    {
        return DifficultyId;
    }
    public int getCookingId()
    {
        return CookingId;
    }
    public int getKitchenId()
    {
        return KitchenId;
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
    public String getPhotoAnd()
    {
        return PhotoAnd;
    }

}
