package com.example.anastasia.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.loginbutton:
                break;
            case R.id.registerbutton:
                Intent intent = new Intent(this, RegisterPage.class);
                startActivity(intent);
                break;
        }

    }
}
