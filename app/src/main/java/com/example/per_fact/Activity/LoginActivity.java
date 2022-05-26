package com.example.per_fact.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.per_fact.Api.LoginService;
import com.example.per_fact.Data.LoginData;
import com.example.per_fact.R;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText email, passwd;
    HashMap<String, String> input = new HashMap<>();
    String stringEmail, stringPasswd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //retrofit 객체 생성
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://34.64.220.224:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginService loginService = retrofit.create(LoginService.class);

        login = findViewById(R.id.btnLogin);
        email = findViewById(R.id.et_email);
        passwd = findViewById(R.id.et_passwd);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringEmail = email.getText().toString();
                stringPasswd = passwd.getText().toString();

                LoginData login = new LoginData(stringEmail, stringPasswd);

                loginService.postData(login).enqueue(new Callback<LoginData>() {
                    @Override
                    public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                        if(response.isSuccessful()) {
                            if(response.body() != null) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }else {
                            Log.i("TEST", "error");
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginData> call, Throwable t) {
                        Log.i("TEST", "실패");
                    }
                });
            }
        });
    }
}
