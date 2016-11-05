package com.example.anastasia.application;

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
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

/**
 * Created by Admin on 22.10.2016.
 */
public class FragmentAvatarStatus extends Fragment implements View.OnClickListener {
    public ImageView ivPhoto;
    private String status;
    File directory;
    public static final int REQUEST_CODE_PHOTO = 1;
    public static final int REQUEST_CODE_GALERY = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_avatar_settings_fragment, null);
        ((TextView)view.findViewById(R.id.profile_status_string)).setText(CurrentUserInfo.getInstance(getActivity()).status);
        createDirectory();
        ivPhoto = (ImageView) view.findViewById(R.id.settings_fragment_photo);
        return view;
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.Settings_fragent_make_photo:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri());
                startActivityForResult(intent, REQUEST_CODE_PHOTO);
                break;
            case R.id.Settings_fragent_load_photo:
                intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALERY);

                break;
        }
    }

    private Uri generateFileUri() {
        File file = null;

                file = new File(directory.getPath() + "/" + "photo_"
                        + System.currentTimeMillis() + ".jpg");

               return Uri.fromFile(file);
    }

    private void createDirectory() {
        directory = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyFolder");
        if (!directory.exists())
            directory.mkdirs();
    }


}
