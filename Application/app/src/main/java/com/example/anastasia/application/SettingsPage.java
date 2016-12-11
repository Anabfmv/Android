package com.example.anastasia.application;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SettingsPage extends AppCompatActivity implements View.OnClickListener  {

    CurrentUserInfo user;
    EditText status_input;
    private String[] colors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        user=CurrentUserInfo.getInstance(this);
        status_input=(EditText)findViewById(R.id.settings_page_status_input);
        status_input.setText(user.status);
        colors = getResources().getStringArray(R.array.colors);
    }
    @Override
    public void onClick(View view) {
        Intent intent;
        int id;
        AlertDialog dialog;
        switch (view.getId()) {
            case R.id.settings_page_status_button:
                String text=status_input.getText().toString();
                user.setStatus(this,text);
                Toast.makeText(this,"new status: "+text,Toast.LENGTH_SHORT).show();
            break;
            case R.id.settings_font_color_button :
                id=findElementInStringArray(colors,user.font_color);
                dialog = setTextColor(id);
                dialog.show();
                break;
            case R.id.settings_fon_color_button :
                id=findElementInStringArray(colors,user.background_color);
                dialog = setFonColor(id);
                dialog.show();
                break;
        }
    }
    private int findElementInStringArray(String[] array,String element)
    {
        for(int i =0;i<array.length;i++)
        {
            if(array[i].equalsIgnoreCase(element)) return i;
        }
        return -1;
    }
    private AlertDialog setTextColor(int selected_item)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Размер шрифта").setCancelable(false);
        builder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        Toast.makeText(getApplicationContext(), "Вы сделали правильный выбор",Toast.LENGTH_LONG).show();
                    }
                });
        // добавляем переключатели
        builder.setSingleChoiceItems(colors, selected_item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                Toast.makeText(getApplicationContext(),"Выбранный цвет текста: "+ colors[item],Toast.LENGTH_SHORT).show();
                user.setFontColor(getApplicationContext(),colors[item]);
            }
        });
        return builder.create();
    }
    private AlertDialog setFonColor(int selected_item)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Размер шрифта").setCancelable(false);
        builder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        Toast.makeText(getApplicationContext(), "Вы сделали правильный выбор",Toast.LENGTH_LONG).show();
                    }
                });
        // добавляем переключатели
        builder.setSingleChoiceItems(colors, selected_item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                Toast.makeText(getApplicationContext(),"Выбранный цвет фона: "+ colors[item],Toast.LENGTH_SHORT).show();
                user.setBackgroundColor(getApplicationContext(),colors[item]);
            }
        });
        return builder.create();
    }
}
