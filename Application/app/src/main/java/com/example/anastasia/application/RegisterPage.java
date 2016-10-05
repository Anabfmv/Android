package com.example.anastasia.application;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.widget.EditText;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener {

    EditText etLogin, etEmail,etPassword,etPassword1;
    DBHelper dbHelper;

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        etLogin = (EditText) findViewById(R.id.login1);
        etPassword = (EditText) findViewById(R.id.password1);
        etPassword1 = (EditText) findViewById(R.id.password2);
        etEmail = (EditText) findViewById(R.id.email);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v)
    {
        ContentValues cv = new ContentValues();
        String login = etLogin.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String password1 = etPassword1.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (v.getId())
        {
                case R.id.registerbutton1:
                    cv.put("login",login);
                    cv.put("password1",password);
                    cv.put("password2",password1);
                    cv.put("email",email);

                    long rowID = db.insert("users",null,cv);
                    Log.d(LOG_TAG, "row inserted, ID = " + rowID);


                break;
        }
    }
}
