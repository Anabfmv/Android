package com.example.anastasia.application;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
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
    final Context activity=this;
    ArrayAdapter<Note> adapter;
    Note current_node;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = CurrentUserInfo.getInstance(this);
        user.LoadSettingsFromDb(this);
        list_view=(ListView)findViewById(R.id.main_list_view);
        final Toast toast= Toast.makeText(this,"",Toast.LENGTH_SHORT);
        //нет, не забыл, просто костыльная джава не даст создать Toast ниже, this там будет уже OnItemClickListener а не активити
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                Note note = notes.get(position);
                Intent intent=new Intent(activity,NewNotePage.class);
                intent.putExtra(NewNotePage.CAN_EDIT,false);
                intent.putExtra(NewNotePage.MESSAGE_ID, note.id);
                intent.putExtra(NewNotePage.MESSAGE_TEXT,note.text);
                intent.putExtra(NewNotePage.MESSAGE_HEADER, note.header);
                startActivity(intent);
            }
        });
        registerForContextMenu(list_view);
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
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.main_list_view) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            Note obj = (Note) lv.getItemAtPosition(acmi.position);
            getMenuInflater().inflate(R.menu.long_tap_meny,menu);
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        Note note = notes.get(info.position);
        current_node=note;
        switch (item.getItemId()) {
            case R.id.menu_edit:
                Intent intent=new Intent(activity,NewNotePage.class);
                intent.putExtra(NewNotePage.CAN_EDIT,true);
                intent.putExtra(NewNotePage.MESSAGE_ID, note.id);
                intent.putExtra(NewNotePage.MESSAGE_TEXT,note.text);
                intent.putExtra(NewNotePage.MESSAGE_HEADER, note.header);
                startActivity(intent);
                return true;
            case R.id.menu_delete:
                final Toast toast= Toast.makeText(this,note.header + " deleted",Toast.LENGTH_SHORT);
                ConfirmDelete(this,"Confirm delete operation",
                        "realy delete "+note.header+" note?",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { // OK
                                DBHelper h=new DBHelper(activity);
                                h.DropNoteFromID(String.valueOf(current_node.id));
                                toast.show();
                                onResume();
                            }
                        }, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { // Cancel

                            }
                        });
            default:
                return super.onContextItemSelected(item);
        }
    }
    public static void ConfirmDelete(final Activity activity, String title, String msg,
                           DialogInterface.OnClickListener okListener,
                           DialogInterface.OnClickListener cancelListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setPositiveButton(R.string.Delete, okListener);
        alertDialog.setNegativeButton(R.string.Cancel, cancelListener);
        alertDialog.show();
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
