package com.example.anastasia.application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private List<Note> notes= new ArrayList<Note>();
    CurrentUserInfo user;
    ListView list_view;
    ArrayAdapter<Note> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = CurrentUserInfo.getInstance(this);
        user.LoadSettingsFromDb(this);
        user.setStatus(this,"какой то статусный статус");
        list_view=(ListView)findViewById(R.id.main_list_view);
        final Toast toast= Toast.makeText(this,"",Toast.LENGTH_SHORT);
        //нет, не забыл, просто костыльная джава не даст создать Toast ниже, this там будет уже OnItemClickListener а не активити
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                Note note = notes.get(position);//по этому position можно находить нужную заметку в листе notes
                toast.setText(note.header+" "+note.date);
                toast.show();
            }
        });
        adapter = new NoteAdapter(this,notes);
        list_view.setAdapter(adapter);
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        notes.clear();
        loadData(notes);
        adapter.notifyDataSetChanged();
    }
    private void loadData(List<Note> note_list) {

        DBHelper h=new DBHelper(this);
        h.SelectNotesToList(note_list,String.valueOf(user.curent_id));
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymeny, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_settings:
                intent = new Intent(this,SettingsPage.class);
                startActivity(intent);
                break;
            case R.id.menu_exit:
                intent = new Intent(this,LoginPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.menu_profile:
                intent=new Intent(this,ProfilePage.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.add_new_post_button:
                intent=new Intent(this,NewNotePage.class);
                startActivity(intent);
                break;
        }
    }

    private class NoteAdapter extends ArrayAdapter<Note> {

        public NoteAdapter(Context context,List<Note> notes) {
            super(context, android.R.layout.simple_list_item_2, notes);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Note note = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, null);
            }
            ((TextView) convertView.findViewById(android.R.id.text1)).setText(note.header);
            ((TextView) convertView.findViewById(android.R.id.text2)).setText(note.date);
            return convertView;
        }
    }
}
