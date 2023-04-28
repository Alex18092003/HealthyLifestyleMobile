package com.example.healthylifestylemobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Registration extends AppCompatActivity {
    ImageView image;
    EditText textPassword, textLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        image = findViewById(R.id.ivVisiblePassword);
        textLogin = findViewById(R.id.textLogin);
        textPassword = findViewById(R.id.textPassword);
        textPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        textLogin.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                textLogin.setHint("");
            else
                textLogin.setHint("Логин");
        });
        textPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                textPassword.setHint("");
            else
                textPassword.setHint("Password");
        });
    }

    public void getVisiblePassword(View v)
    {
        if(textPassword.getInputType() == 129)
        {
            image.setImageResource(R.drawable.icon_password_visible1);
            textPassword.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        else
        {
            image.setImageResource(R.drawable.icon_password_not_visible1);
            textPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    public void nextAutorization(View view)
    {
        startActivity(new Intent(this, Autorization.class));
    }
}