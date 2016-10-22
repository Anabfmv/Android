package com.example.anastasia.application;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProfilePage extends AppCompatActivity {

    Fragment fragment_avater;
    Fragment fragment_name;
    FragmentTransaction fTrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        fragment_avater=new FragmentAvatarStatus();
        fragment_name=new FragmentNameEmail();
        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.frame1,fragment_avater);
        fTrans.add(R.id.frame2,fragment_name);
        fTrans.commit();
    }
}
