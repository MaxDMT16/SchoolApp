package com.dmt.max.schoolschedule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dmt.max.schoolschedule.main.MainActivity;
import com.dmt.max.schoolschedule.model.login.FailLoginResponse;
import com.dmt.max.schoolschedule.model.login.LoginRequest;
import com.dmt.max.schoolschedule.model.login.SuccessLoginResponse;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Inject
    SchoolSystemWebService schoolSystemWebService;

    TextInputEditText loginEditText;
    TextInputEditText passwordEditText;
    Button loginButton;

    String login;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((SchoolApplication) getApplication()).getSchoolComponent().inject(this);

        findViews();


        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                fetchCredentials();
                login();
            }
        });
    }

    private void findViews() {
        loginEditText = findViewById(R.id.loginInputEditText);
        passwordEditText = findViewById(R.id.passwordInputEditText);
        loginButton = findViewById(R.id.buttonLogin);
    }

    private void fetchCredentials() {
        login = loginEditText.getText().toString();
        password = passwordEditText.getText().toString();
    }

    private void login() {
        if (schoolSystemWebService == null) {
            Toast.makeText(getApplicationContext(), "Can't resolve web service", Toast.LENGTH_SHORT).show();
        } else {
            Call<SuccessLoginResponse> loginResponse = schoolSystemWebService.login(new LoginRequest(login, password));

            loginResponse.enqueue(new Callback<SuccessLoginResponse>() {
                @Override
                public void onResponse(Call<SuccessLoginResponse> call, Response<SuccessLoginResponse> response) {
                    if (response.isSuccessful()) {

                        String accessToken = response.body().getAccessToken();
                        String refreshToken = response.body().getRefreshToken();

                        if (trySaveTokens(accessToken, refreshToken)) {
                            startMainActivity();
                        }
                    } else {
                        showError(response);
                    }

                    Toast.makeText(getApplicationContext(), "Finish", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<SuccessLoginResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "onFailure method", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean trySaveTokens(String accessToken, String refreshToken) {
        String sharePreferencesKey = getResources().getString(R.string.shared_preferences_key);
        String accessTokenKey = getResources().getString(R.string.access_token_key);
        String refreshTokenKey = getResources().getString(R.string.refresh_token_key);

        SharedPreferences sharedPreferences = getSharedPreferences(sharePreferencesKey, MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();

        preferencesEditor.putString(accessTokenKey, accessToken);
        preferencesEditor.putString(refreshTokenKey, refreshToken);

        boolean areTokensCommited = preferencesEditor.commit();

        if (areTokensCommited) {
            Toast.makeText(getApplicationContext(), "Success!!!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Can't save access token", Toast.LENGTH_SHORT).show();
        }

        return areTokensCommited;
    }

    private void showError(Response<SuccessLoginResponse> response) {
        try {
            Gson gson = new Gson();
            FailLoginResponse failLoginResponse = gson.fromJson(response.errorBody().string(), FailLoginResponse.class);

            if (failLoginResponse.getCode().equalsIgnoreCase("ss-0001")) {
                Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
