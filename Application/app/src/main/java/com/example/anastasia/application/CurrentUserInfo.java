package com.example.anastasia.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.media.Image;

/**
 * Created by Admin on 22.10.2016.
 */
public class CurrentUserInfo {
   private static CurrentUserInfo userInfo = null;
    public int showAvatarBlock, showEmailBlock;
    Image avatar;
    public String status, font_size, font_style, font_color, background_color, email, login;
    static int curent_id;

    public static void InitializeID(int id){curent_id=id;}

    public static CurrentUserInfo getInstance(Context context) {
        if (userInfo == null) userInfo = new CurrentUserInfo();
        return userInfo;
    }

    public void LoadSettingsFromDb(Context context) {
        DBHelper db = new DBHelper(context);
        SQLiteDatabase base=db.getReadableDatabase();
        /*Cursor cursor=base.query("settings",
                new String[]{"showAvatarBlock","showEmailBlock","status","font_size","font_color","background_color","font_style"},
                "id=?",new String[]{String.valueOf(curent_id)},null,null,null);*/
        Cursor cursor=base.rawQuery(
                "select showAvatarBlock,showEmailBlock,status,font_size,font_color,background_color,font_style,email " +
                "from settings where user_id=?",
                new String[]{String.valueOf(curent_id)});
        cursor.moveToFirst();
        showAvatarBlock=cursor.getInt(0);
        showEmailBlock=cursor.getInt(1);
        status=cursor.getString(2);
        font_size=cursor.getString(3);
        font_color=cursor.getString(4);
        background_color=cursor.getString(5);
        font_style=cursor.getString(6);
        email=cursor.getString(7);
        cursor.close();
        cursor=base.rawQuery("select login from users where id=?",
                new String[]{String.valueOf(curent_id)});
        cursor.moveToFirst();
        login=cursor.getString(0);
        cursor.close();
    }

    private CurrentUserInfo(){}

    public void setStatus( Context context, String new_status) {
        setArg(context,"status",String.valueOf(new_status));
        status=new_status;
    }
    public void setFontSize( Context context, String new_font_size) {
        setArg(context,"font_size",String.valueOf(new_font_size));
        font_size=new_font_size;
    }
    public void setFontStyle( Context context, String new_font_style) {
        setArg(context,"font_style",String.valueOf(new_font_style));
        font_style=new_font_style;
    }
    public void setFontColor( Context context, String new_font_color) {
        setArg(context,"font_color",String.valueOf(new_font_color));
        font_color=new_font_color;
    }
    public void setBackgroundColor( Context context, String new_background_color) {
        setArg(context,"background_color",String.valueOf(new_background_color));
        background_color=new_background_color;
    }
    public void setShowAvatarBlock( Context context, int new_show_avatar_block) {
        setArg(context,"ShowAvatarBlock",String.valueOf(new_show_avatar_block));
        showAvatarBlock=new_show_avatar_block;
    }
    public void setshowEmailBlock( Context context, int new_show_email_block) {
        setArg(context,"showEmailBlock",String.valueOf(new_show_email_block));
        showEmailBlock=new_show_email_block;
    }

    private void setArg(Context context, String row, String value) {
        DBHelper db = new DBHelper(context);
        SQLiteDatabase base = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(row,value);
        int rowID = base.update("settings",contentValues,"user_id=?",new String[]{String.valueOf(curent_id)});
       // String querry="update settings set " + attribute + " where user_id = " + curent_id;
        if(rowID==-1) throw new RuntimeException("databace users insert error");
        //base.execSQL(querry);
    }
}