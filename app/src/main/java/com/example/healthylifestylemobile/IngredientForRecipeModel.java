package com.example.healthylifestylemobile;

public class IngredientForRecipeModel {
    private int IngredientForRecipeId;
    private int IngredientId;
    private int RecipeId;
    private int UnitsOfMeasurementId;
    private int Quantity;

    public  IngredientForRecipeModel(int IngredientForRecipeId,
                            int IngredientId,int RecipeId,
                            int UnitsOfMeasurementId, int Quantity)
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
    public void setQuantity(int Quantity)
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
    public Object getQuantity()
    {
        if(Quantity == 0)
        {
            //int  Quantity2 = Integer.parseInt(String.valueOf(Quantity));
            return "";
        }
        //int  Quantity2 = Integer.parseInt(Quantity);
        return Quantity;
    }
}
