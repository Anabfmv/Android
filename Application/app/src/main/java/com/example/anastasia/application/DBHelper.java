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
    private final String create_users_table=
            "create table users(id integer primary key autoincrement, login text unique, password text);";
    private final String create_notes_table=
            "CREATE TABLE users_notes (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER REFERENCES users (id),"+
            "date DATE, header TEXT, raw_text TEXT, private_flag BOOLEAN);";
    private final String create_settings_table=
            "CREATE TABLE settings (user_id INTEGER REFERENCES users (id) PRIMARY KEY, status TEXT,font_size TEXT,"+
            "font_color TEXT, background_color TEXT, email TEXT, showAvatarBlock BOOLEAN, showEmailBlock BOOLEAN, avatar BLOB);";
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //таблица пользователей
        db.execSQL(create_users_table);
        //таблица записей в дневник
        db.execSQL(create_notes_table);
        //таблица настроек
        db.execSQL(create_settings_table);
    }
    public void AddUserToBD(String login, String password, String email)
    {
        ContentValues data = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        data.put("login", login);
        data.put("password", password);
        long rowID = db.insert("users", null, data);
        if(rowID==-1) throw new RuntimeException("databace users insert error");

        int user_id=selectIDFromLoginPassword(login,password);
        data=new ContentValues();
        data.put("email",email);
        data.put("user_id",user_id);
        rowID=db.insert("settings",null,data);
        if(rowID==-1) throw new RuntimeException("databace settings insert error");
    }
    public int selectIDFromLoginPassword(String login, String password)
    {
        SQLiteDatabase connection = getReadableDatabase();
        Cursor cursor=connection.rawQuery("select id from users where login=? and password=?",
                new String[]{login,password});
        cursor.moveToFirst();
        int user_id=cursor.getInt(0);
        cursor.close();
        return user_id;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
