package com.example.anastasia.application;


import android.os.Bundle;
import android.preference.PreferenceActivity;

public class TextSettings extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
