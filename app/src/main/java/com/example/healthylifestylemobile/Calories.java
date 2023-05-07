package com.example.healthylifestylemobile;



public class Calories {

    float RSK;
    public float CaloriesUser(int IdGender, float Height, float Weight,
                         int Age, int IdActivities, int IdGoal) {

        if (IdGender == 2) {

            float c = Float.parseFloat(String.valueOf(10.0)) * Height;
            float cc = Float.parseFloat(String.valueOf(6.25))  * Weight;
            float ccc = Float.parseFloat(String.valueOf(5.0)) * Age;
            if (IdActivities == 1)
            {
                RSK = RSK * Float.parseFloat(String.valueOf(1.2));
            }
            else if (IdActivities == 2)
            {
                RSK = RSK * Float.parseFloat(String.valueOf(1.375));
            }
            else if (IdActivities == 3)
            {
                RSK = RSK * Float.parseFloat(String.valueOf(1.55));
            }
            else if (IdActivities == 4)
            {
                RSK = RSK * Float.parseFloat(String.valueOf(1.725));
            }
            else
            {
                RSK = RSK * Float.parseFloat(String.valueOf(1.9));
            }

            if (IdGoal == 1)
            {
                float percent = RSK / 100;
                RSK = RSK - (percent * 20);
                return RSK;
            }
            else if (IdGoal == 2)
            {
                float percent = RSK / 100;
                RSK = RSK + (percent * 20);
                return RSK;
            }
            else
            {
                return RSK;
            }

        }
        else {
            float c = Float.parseFloat(String.valueOf(10.0)) * Height;
            float cc = Float.parseFloat(String.valueOf(6.25))  * Weight;
            float ccc = Float.parseFloat(String.valueOf(5.0)) * Age;
            RSK = c + cc - ccc + 5;
            if (IdActivities == 1)
            {
                RSK = RSK * Float.parseFloat(String.valueOf(1.2));
            }
            else if (IdActivities == 2)
            {
                RSK = Float.parseFloat(String.valueOf(RSK * 1.375));
            }
            else if (IdActivities == 3)
            {
                RSK = RSK * Float.parseFloat(String.valueOf(1.55));
            }
            else if (IdActivities == 4)
            {
                RSK = RSK * Float.parseFloat(String.valueOf(1.725));
            }
            else
            {
                RSK = RSK * Float.parseFloat(String.valueOf(1.9));
            }

            if (IdGoal == 1)
            {
                float percent = RSK / 100;
                RSK = RSK - (percent * 20);
                return RSK;
            }
            else if (IdGoal == 2)
            {
                float percent = RSK / 100;
                RSK = RSK + (percent * 20);
                return RSK;
            }
            else
            {
                return RSK;
            }
        }
    }
}