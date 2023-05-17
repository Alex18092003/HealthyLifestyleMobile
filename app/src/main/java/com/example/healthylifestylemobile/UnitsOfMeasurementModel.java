package com.example.healthylifestylemobile;

public class UnitsOfMeasurementModel {

    private int UnitsOfMeasurementId;
    private String Title;

    public  UnitsOfMeasurementModel(int UnitsOfMeasurementId,
                            String Title)
    {
        this.UnitsOfMeasurementId = UnitsOfMeasurementId;
        this.Title = Title;

    }

    public void setUnitsOfMeasurementId(int UnitsOfMeasurementId)
    {
        this.UnitsOfMeasurementId = UnitsOfMeasurementId;
    }
    public void setTitle(String Title)
    {
        this.Title = Title;
    }
    public int getUnitsOfMeasurementId()
    {
        return UnitsOfMeasurementId;
    }
    public String getTitle()
    {
        return Title;
    }
}
