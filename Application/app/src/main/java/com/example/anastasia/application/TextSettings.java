package com.example.anastasia.application;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class TextSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_settings);
    }
    private AlertDialog setTextColor(int selected_item)
    {

        final String[] colors = getResources().getStringArray(R.array.text_color);
        Builder builder = new Builder(this);
        builder.setTitle("Размер шрифта").setCancelable(false);
        builder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
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
                                Toast.makeText(getApplicationContext(),"Выбранный цвет: "+ colors[item],Toast.LENGTH_SHORT).show();
                            }
                        });
        return builder.create();
    }
}
