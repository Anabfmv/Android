package com.example.anastasia.application;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class NewNotePage extends AppCompatActivity {

    CurrentUserInfo user;
    EditText HeaderTextbox;
    EditText TextTextbox;
    public static final String CAN_EDIT = "CAN_EDIT";
    public static final String MESSAGE_ID = "MESSAGE_ID";
    public static final String MESSAGE_TEXT = "MESSAGE_TEXT";
    public static final String MESSAGE_HEADER = "MESSAGE_HEADER";
    int message_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user=CurrentUserInfo.getInstance(this);
        setContentView(R.layout.activity_new_note_page);

        HeaderTextbox = (EditText)findViewById(R.id.new_note_header_textbox);
        TextTextbox = (EditText)findViewById(R.id.new_note_text_textbox);
        if(!getIntent().getBooleanExtra(CAN_EDIT,true)) {
            HeaderTextbox.setKeyListener(null);
            TextTextbox.setKeyListener(null);
            HeaderTextbox.setTextIsSelectable(true);
            TextTextbox.setTextIsSelectable(true);
        }
        SetTextboxConfigs(TextTextbox);
        SetTextboxConfigs(HeaderTextbox);
        LinearLayout fon = (LinearLayout)findViewById(R.id.new_note_layout);
        if(user.background_color!=null) fon.setBackgroundColor(getColorFromResurse(user.background_color));
        TextTextbox.setText(getIntent().getStringExtra(MESSAGE_TEXT));
        HeaderTextbox.setText(getIntent().getStringExtra(MESSAGE_HEADER));
        message_id = getIntent().getIntExtra(MESSAGE_ID,-1);
    }
    private void SetTextboxConfigs(EditText textbox)
    {
        int value;
        if(user.font_color!=null) {
            value=getColorFromResurse(user.font_color);
            textbox.setTextColor(value);
        }
        if(user.font_size!=null)
        {
            textbox.setTextSize(Float.parseFloat(user.font_size));
        }
        if(user.font_style!=null)
        {
            switch (user.font_style)
            {
                case "Обычный": textbox.setTypeface(null,Typeface.NORMAL);break;
                case "Полужирный": textbox.setTypeface(null,Typeface.BOLD);break;
                case "Курсив": textbox.setTypeface(null,Typeface.ITALIC);break;
                case "Полужирный+Курсив": textbox.setTypeface(null,Typeface.BOLD_ITALIC);break;
            }
        }
    }
    private int getColorFromResurse(String color)
    {
        int int_color;
        Resources res = getResources();
        switch (color)
        {
            case "Черный": int_color = res.getColor(R.color.Чёрный);break;
            case "Белый": int_color = res.getColor(R.color.Белый);break;
            case "Красный": int_color = res.getColor(R.color.Красный);break;
            case "Песочный": int_color = res.getColor(R.color.Песочный);break;
            case "Зеленый": int_color = res.getColor(R.color.Зеленый);break;
            case "Синий": int_color = res.getColor(R.color.Синий);break;
            case "Голубой": int_color = res.getColor(R.color.Голубой);break;
            default: int_color = -1;break;
        }
        return int_color;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_note_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.new_note_save:
                DBHelper h=new DBHelper(this);
                String header=HeaderTextbox.getText().toString();
                String text = TextTextbox.getText().toString();
                if(message_id!=-1)
                {
                    h.UpdateNote(header,text,String.valueOf(message_id));
                }
                else
                {
                    h.AddNewNote(header, text, user.curent_id);
                }
                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
