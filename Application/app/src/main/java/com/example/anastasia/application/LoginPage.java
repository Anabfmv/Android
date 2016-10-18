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
            SQLiteDatabase connection = db.getReadableDatabase();
            Cursor cursor=null;
            /*cursor=connection.query("users",new String[]{"login","password"},
            "login=? and password=?",new String[]{login,password},null,null,null);*/
            cursor=connection.rawQuery("select id from users where login=? and password=?",
                    new String[]{login,password});
            cursor.moveToFirst();
            int user_id=cursor.getInt(0);
            return 1111;
        }
        catch (Exception e)
        {
            return -1;
        }
    }

}
