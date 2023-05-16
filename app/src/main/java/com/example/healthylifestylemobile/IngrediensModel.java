package com.example.healthylifestylemobile;

public class IngrediensModel {

    private int IngredientId;
    private String Title;

    public  IngrediensModel(int IngredientId,
                            String Title)
    {
        this.IngredientId = IngredientId;
        this.Title = Title;

    }

    public void setIngredientId(int IngredientId)
    {
        this.IngredientId = IngredientId;
    }
    public void setTitle(String Title)
    {
        this.Title = Title;
    }
    public int getIngredientId()
    {
        return IngredientId;
    }
    public String getTitle()
    {
        return Title;
    }

}
