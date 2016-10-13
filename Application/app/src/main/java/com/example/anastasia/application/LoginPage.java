package com.example.anastasia.application;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
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

                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                break;

            case R.id.registerbutton:
                Intent intent = new Intent(this, RegisterPage.class);
                startActivity(intent);
                break;
        }

    }
    private boolean auth(String login,String password){
        DBHelper db = new DBHelper(this);
       SQLiteDatabase connection = db.getReadableDatabase();
        Cursor c=null;
        c=connection.query("users",new String[]{"login","password"},
                "login=? and password=?",new String[]{"log","pass"},null,null,null);//select login,password from users where login='log' and password='pass'
    }

}
