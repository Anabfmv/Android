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
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener {

    EditText LoginField, EmailField,PasswordField,PasswordRepeatField;
    DBHelper dbHelper;

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        LoginField = (EditText) findViewById(R.id.register_page_login);
        PasswordField = (EditText) findViewById(R.id.register_page_password);
        PasswordRepeatField = (EditText) findViewById(R.id.register_page_repeat_password);
        EmailField = (EditText) findViewById(R.id.register_page_email);
        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
                case R.id.register_page_register_button:
                    String login = LoginField.getText().toString();
                    String email = EmailField.getText().toString();
                    String password = PasswordField.getText().toString();
                    String repeat_password = PasswordRepeatField.getText().toString();
                    if(!password.equals(repeat_password)){
                        Toast.makeText(this,R.string.Password_does_not_match,Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                        try {
                            dbHelper.AddUserToBD(login, password, email);
                            Toast.makeText(this, R.string.Register_success, Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(this, R.string.Register_error + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        Log.d(LOG_TAG, "user registred sucess");
                    }
                break;
        }


    }
}
