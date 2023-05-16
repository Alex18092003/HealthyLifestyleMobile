package com.example.healthylifestylemobile;

public class IngredientForRecipeModel {
    private int IngredientForRecipeId;
    private int IngredientId;
    private int RecipeId;
    private int UnitsOfMeasurementId;
    private double Quantity;

    public  IngredientForRecipeModel(int IngredientForRecipeId,
                            int IngredientId,int RecipeId,
                            int UnitsOfMeasurementId, double Quantity)
    {
        this.IngredientForRecipeId = IngredientForRecipeId;
        this.IngredientId = IngredientId;
        this.RecipeId = RecipeId;
        this.UnitsOfMeasurementId = UnitsOfMeasurementId;
        this.Quantity = Quantity;
    }

    public void setIngredientForRecipeId(int IngredientForRecipeId)
    {
        this.IngredientForRecipeId = IngredientForRecipeId;
    }
    public void setIngredientId(int IngredientId)
    {
        this.IngredientId = IngredientId;
    }
    public void setRecipeId(int RecipeId)
    {
        this.RecipeId = RecipeId;
    }
    public void setUnitsOfMeasurementId(int UnitsOfMeasurementId)
    {
        this.UnitsOfMeasurementId = UnitsOfMeasurementId;
    }
    public void setQuantity(double StepId)
    {
        this.Quantity = Quantity;
    }



    public int getIngredientForRecipeId()
    {
        return IngredientForRecipeId;
    }
    public int getIngredientId()
    {
        return IngredientId;
    }
    public int getRecipeId()
    {
        return RecipeId;
    }
    public int getUnitsOfMeasurementId()
    {
        return UnitsOfMeasurementId;
    }
    public double getQuantity()
    {
        return Quantity;
    }
}
