package com.example.healthylifestylemobile;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registration extends AppCompatActivity {
    ImageView image, image2;
    EditText textPassword,textPassword2, textLogin;
    TextView Hint;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Hint = findViewById(R.id.Hint);
        image = findViewById(R.id.ivVisiblePassword);
        image2 = findViewById(R.id.ivVisiblePassword2);
        textLogin = findViewById(R.id.textLogin);
        textPassword = findViewById(R.id.textPassword);
        textPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        textPassword2 = findViewById(R.id.textPassword2);
        textPassword2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        progressBar = findViewById(R.id.pbLoading);

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
                textPassword.setHint("Пароль");
        });
        textPassword2.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                textPassword2.setHint("");
            else
                textPassword2.setHint("Повторите пароль");
        });
    }

    public void getVisiblePassword(View v)
    {
        if(textPassword.getInputType() == 129)
        {
            image.setImageResource(R.drawable.icon_password_not_visible1);
            textPassword.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        else
        {
            image.setImageResource(R.drawable.icon_password_visible1);
            textPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    public void getVisiblePassword2(View v)
    {
        if(textPassword2.getInputType() == 129)
        {
            image2.setImageResource(R.drawable.icon_password_not_visible1);
            textPassword2.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        else
        {
            image2.setImageResource(R.drawable.icon_password_visible1);
            textPassword2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    public void nextRegistrarion(View v)
    {
        progressBar.setVisibility(View.VISIBLE);
        String login = String.valueOf(textLogin.getText());
        String password = String.valueOf(textPassword.getText());
        String confirmPassword = String.valueOf(textPassword2.getText());
        if(login.replaceAll("\\s+", " ").equals(""))
        {
            Hint.setText("Поля не заполены\nНеобходимо придумать логин И пароль");
            progressBar.setVisibility(View.GONE);
            return;
        }
        if(password.replaceAll("\\s+", " ").equals(""))
        {
            Hint.setText("Поля не заполены\nНеобходимо придумать логин И пароль");
            progressBar.setVisibility(View.GONE);
            return;
        }
        if(!password.equals(confirmPassword))
        {
            Hint.setText("Повторный пароль введён не верно");
            progressBar.setVisibility(View.GONE);
        }
        else
        {
            callRegistration();

            progressBar.setVisibility(View.GONE);

        }
    }

    private void callRegistration()
    {
        progressBar.setVisibility(View.VISIBLE);
        String Login = String.valueOf(textLogin.getText());
        String Password = String.valueOf(textPassword.getText());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://iis.ngknn.ru/ngknn/лебедевааф/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<Boolean> call = retrofitAPI.examinationRegistration(Login);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (!response.isSuccessful()) {
                    Hint.setText("При регистрации возникла ошибка");
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if(response.body().equals(false))
                {
                    nextActiv();
                }
                else
                {
                    Hint.setText( "Пользователь с таким логиным уже зарегистрирован");
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Hint.setText( "При авторизации возникла ошибка: " );
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void nextActiv()
    {
        Intent intent = new Intent(this, Activities.class);
        intent.putExtra("login", textLogin.getText().toString());
        intent.putExtra("password", textPassword.getText().toString());
        startActivity(intent);
    }

    public void nextAutorization(View view)
    {
        startActivity(new Intent(this, Authorization.class));
    }
}