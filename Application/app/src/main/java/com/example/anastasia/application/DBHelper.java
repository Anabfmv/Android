package com.example.anastasia.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
//singleton pattern
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "myDB", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table users("
                   + "id integer primary key autoincrement,"
                   + "login text unique,"
                   + "email text,"
                   + "password text"
                   + ");");
        db.execSQL("CREATE TABLE users_notes ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "user_id INTEGER REFERENCES users (id),"+
                "date DATE,"+
                "header TEXT,"+
                "raw_text TEXT,"+
                "private_flag BOOLEAN"+
                ");");
    }
    public long AddUserToBD(String login, String password, String email)
    {
        ContentValues data = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        data.put("login", login);
        data.put("password", password);
        data.put("email", email);
        long rowID = db.insert("users", null, data);
        return rowID;
    }
    public int selectIDFromLoginPassword(String login, String password)
    {
        SQLiteDatabase connection = getReadableDatabase();
        Cursor cursor=connection.rawQuery("select id from users where login=? and password=?",
                new String[]{login,password});
            /*cursor=connection.query("users",new String[]{"login","password"},
            "login=? and password=?",new String[]{login,password},null,null,null);*/
        cursor.moveToFirst();
        int user_id=cursor.getInt(0);
        cursor.close();
        return user_id;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
