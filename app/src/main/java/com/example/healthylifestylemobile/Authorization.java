package com.example.healthylifestylemobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telecom.Call;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;


public class Authorization extends AppCompatActivity {

    ImageView image;
    EditText textPassword, textLogin;
    TextView Hint;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorization);

        image = findViewById(R.id.ivVisiblePassword);
        textLogin = findViewById(R.id.textLogin);
        textPassword = findViewById(R.id.textPassword);
        Hint = findViewById(R.id.Hint);
        progressBar = findViewById(R.id.pbLoading);
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

        SharedPreferences prefs = this.getSharedPreferences(
                "Date", Context.MODE_PRIVATE); // Получение данных о пользователе
    }

    public void nextMain(View v)
    {
        if(textLogin.getText().toString().equals("") || textPassword.getText().toString().equals("")) // Проверка заполненности полей
        {
            Hint.setText("Поля не заполены\\nДля входа необходимо ввести логин И пароль или пройти Регистрацию");
        }
        else
        {
            callLogin();
        }
    }

    private void callLogin() // Процесс проверки пользователя
    {
        String login = String.valueOf(textLogin.getText());
        String password = String.valueOf(textPassword.getText());
        Retrofit retrofit = new Builder()
                .baseUrl("https://iis.ngknn.ru/ngknn/лебедевааф/api/Users")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        ModelSendUser modelSendUser = new ModelSendUser(email, password);
        Call<UserModel> call = retrofitAPI.createUser(modelSendUser);
        call.enqueue(new Callback<MaskUser>() {
            @Override
            public void onResponse(Call<MaskUser> call, Response<MaskUser> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Login.this, "Пользователь с такой почтой и паролем не найден", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(response.body() != null)
                {
                    if(response.body().getToken() != null)
                    {
                        SharedPreferences prefs = getSharedPreferences( // Сохранение данных
                                "Date", Context.MODE_PRIVATE);
                        prefs.edit().putString("Email", "" + email).apply();
                        prefs.edit().putString("Avatar", "" + response.body().getAvatar()).apply();
                        prefs.edit().putString("NickName", "" + response.body().getNickName()).apply();

                        Onboarding.avatar = response.body().getAvatar();
                        Onboarding.nickName = response.body().getNickName();

                        Intent intent = new Intent(Login.this, Main.class);
                        Bundle b = new Bundle();
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(Login.this, "При авторизации возникла ошибка: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
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

    public void nextRegistation(View view)
    {
        startActivity(new Intent(this, Registration.class));
    }


    private class Retrofit {
    }
}