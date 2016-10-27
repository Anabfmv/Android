package com.example.anastasia.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SettingsPage extends AppCompatActivity implements View.OnClickListener  {

    CurrentUserInfo user;
    EditText status_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        user=CurrentUserInfo.getInstance(this);
        status_input=(EditText)findViewById(R.id.settings_page_status_input);
        status_input.setText(user.status);
    }
    @Override
    public void onClick(View view) {
        user.setStatus(this,status_input.getText().toString());
        Intent intent;
        switch (view.getId()) {
            case R.id.redtext:
                intent = new Intent(this,TextSettings.class);
                startActivity(intent);
                break;
        }
    }


}
