package com.example.anastasia.application;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

/**
 * Created by Admin on 22.10.2016.
 */
public class FragmentAvatarStatus extends Fragment{
    public ImageView ivPhoto;
    private String status;
    public Button make_photo_btn;
    public Button load_photo_bth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_avatar_settings_fragment, null);
        TextView status_bar = (TextView)view.findViewById(R.id.profile_status_string);
        status_bar.setText(CurrentUserInfo.getInstance(getActivity()).status);

        make_photo_btn = (Button)view.findViewById(R.id.Settings_fragent_make_photo);
        load_photo_bth = (Button)view.findViewById(R.id.Settings_fragent_load_photo);
        return view;
    }
}
