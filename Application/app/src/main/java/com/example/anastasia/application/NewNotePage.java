package com.example.anastasia.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NewNotePage extends AppCompatActivity {

    CurrentUserInfo user;
    EditText HeaderTextbox;
    EditText TextTextbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user=CurrentUserInfo.getInstance(this);
        setContentView(R.layout.activity_new_note_page);
        HeaderTextbox = (EditText)findViewById(R.id.new_note_header_textbox);
        TextTextbox = (EditText)findViewById(R.id.new_note_text_textbox);
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
                h.AddNewNote(header, text, user.curent_id);
                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
