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
        ContentValues cv = new ContentValues();
        String login = LoginField.getText().toString();
        String email = EmailField.getText().toString();
        String password = PasswordField.getText().toString();
        String repeat_password = PasswordRepeatField.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();


        switch (v.getId())
        {
                case R.id.register_page_register_button:

                    String first_pass=PasswordField.getText().toString();
                    String second_pass=PasswordRepeatField.getText().toString();
                    if(!first_pass.equals(second_pass))
                    {
                        Toast.makeText(this,R.string.Password_does_not_match,Toast.LENGTH_SHORT).show();
                        return;

                    }
                    cv.put("login",login);
                    cv.put("password",password);
                    cv.put("email",email);

                    long rowID = db.insert("users",null,cv);
                    Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                    if(rowID==-1){
                        Toast.makeText(this,R.string.Register_error,Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(this,R.string.Register_success,Toast.LENGTH_SHORT).show();
                        finish();
                    }
                break;
        }


    }
}
