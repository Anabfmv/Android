package com.example.anastasia.application;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by Admin on 22.10.2016.
 */
public class FragmentAvatarStatus extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_avatar_settings_fragment, null);
    }
}
