package com.example.anastasia.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    ListView list_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_view=(ListView)findViewById(R.id.main_list_view);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.list_item,getModel());
        list_view.setAdapter(adapter);
    }
    private List<String> getModel() {
        List<String> list = new ArrayList<String>();
        list.add("Linux");
        list.add("Windows7");
        list.add("Suse");
        list.add("Eclipse");
        list.add("Ubuntu");
        list.add("Solaris");
        list.add("Android");
        list.add("iPhone");
        // Первоначальный выбор одного из элементов
        return list;
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.menu_settings:
                Log.d("12345","click");
                break;
        }
    }
}