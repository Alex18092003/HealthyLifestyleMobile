package com.example.healthylifestylemobile;

public class ModelSendUser {
    private String Login;
    private String Password;

    public ModelSendUser(String Login, String Password) {
        this.Login = Login;
        this.Password = Password;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}
