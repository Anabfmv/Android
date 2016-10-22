package com.example.anastasia.application;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    private EditText login_text_field;
    private EditText password_text_field;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        login_text_field=(EditText)findViewById(R.id.login_page_login);
        password_text_field=(EditText)findViewById(R.id.login_page_password);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.login_page_loginbutton:
                String login=login_text_field.getText().toString();
                String password=password_text_field.getText().toString();
                int user_id=auth(login,password);
                if(user_id!=-1) {
                    intent = new Intent(this, MainActivity.class);
                    intent.putExtra("user_id",user_id);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else Toast.makeText(this,"Invalid username/password",Toast.LENGTH_SHORT).show();
                break;

            case R.id.login_page_registerbutton:
                intent = new Intent(this, RegisterPage.class);
                startActivity(intent);
                break;
        }

    }
    private int auth(String login,String password){

        try {
            DBHelper db = new DBHelper(this);
            return db.selectIDFromLoginPassword(login,password);
        }
        catch (Exception e)
        {
            return -1;
        }
    }

}
