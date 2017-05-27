package com.a11group.app_micro_finance_v1.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.a11group.app_micro_finance_v1.Activity.Login;
import com.a11group.app_micro_finance_v1.R;

public class Splash_Screen extends AppCompatActivity {

    private static int TEMPO_SPLASH = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login = new Intent(getApplicationContext(), Login.class);
                startActivity(login);
                finish();
            }
        }, TEMPO_SPLASH);
    }
}
