package com.example.anastasia.application;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentNameEmail  extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_name_email_settings_fragment, null);
        ((TextView)view.findViewById(R.id.profile_email_field)).setText(CurrentUserInfo.getInstance(getActivity()).email);
        ((TextView)view.findViewById(R.id.profile_name_field)).setText(CurrentUserInfo.getInstance(getActivity()).login);
        return  view;
    }
}