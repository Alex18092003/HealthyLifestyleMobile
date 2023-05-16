package com.example.healthylifestylemobile;

public class StepsModel {

    private int StepId;
    private int RecipeId;
    private int StepNomen;
    private String Description;
    private String Photo;
    private String PhotoAnd;

    public  StepsModel(int StepId,
                       int RecipeId, int StepNomen,
                       String Description, String Photo,
                       String PhotoAnd)
    {
        this.StepId = StepId;
        this.RecipeId = RecipeId;
        this.StepNomen = StepNomen;
        this.Description = Description;
        this.Photo = Photo;
        this.PhotoAnd = PhotoAnd;

    }

    public void setStepId(int StepId)
    {
        this.StepId = StepId;
    }
    public void setRecipeId(int RecipeId)
    {
        this.RecipeId = RecipeId;
    }
    public void setStepNomen(int StepNomen)
    {
        this.StepNomen = StepNomen;
    }
    public void setDescription(String Description)
    {
        this.Description = Description;
    }
    public void setPhoto(String Photo)
    {
        this.Photo = Photo;
    }
    public void setPhotoAnd(String PhotoAnd)
    {
        this.PhotoAnd = PhotoAnd;
    }


    public int getStepId()
    {
        return StepId;
    }
    public int getRecipeId()
    {
        return RecipeId;
    }
    public int getStepNomen()
    {
        return StepNomen;
    }
    public String getDescription()
    {
        return Description;
    }
    public String getPhoto()
    {
        return Photo;
    }
    public String getPhotoAnd()
    {
        return PhotoAnd;
    }

}
