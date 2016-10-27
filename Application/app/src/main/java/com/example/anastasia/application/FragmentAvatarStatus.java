package com.example.anastasia.application;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Admin on 22.10.2016.
 */
public class FragmentAvatarStatus extends Fragment {
    private String status;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_avatar_settings_fragment, null);
        ((TextView)view.findViewById(R.id.profile_status_string)).setText(CurrentUserInfo.getInstance(getActivity()).status);
        return view;
    }
}