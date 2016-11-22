package com.example.anastasia.application;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.*;


public class ProfilePage extends AppCompatActivity{

    public static final int REQUEST_CODE_PHOTO = 1;
    public static final int REQUEST_CODE_GALERY = 2;
    File directory;
    FragmentAvatarStatus fragment_avater;
    Fragment fragment_name;
    FragmentTransaction fTrans;
    CurrentUserInfo user;
    private void setButtonsHandlers()
    {
        fragment_avater.load_photo_bth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALERY);
            }
        });
        fragment_avater.make_photo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //intent.putExtra(MediaStore.EXTRA_OUTPUT, getCorrentUserAvatarUri());
                startActivityForResult(intent, REQUEST_CODE_PHOTO);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDirectory();
        //createDirectory();
        setContentView(R.layout.activity_profile_page);
        fragment_avater=new FragmentAvatarStatus();
        user=CurrentUserInfo.getInstance(this);

        fragment_name=new FragmentNameEmail();
        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.frame1,fragment_name);
        fTrans.add(R.id.frame2,fragment_avater);
        fTrans.commit();
    }
    @Override
    protected void onStart() {
        super.onStart();
        fragment_avater.ivPhoto = (ImageView) findViewById(R.id.settings_fragment_photo);
        setPhotoIfExist();
        setButtonsHandlers();
    }

    private void createDirectory() {
        directory = new File(getApplicationInfo().dataDir+"/Avatars");
        if (!directory.exists())
            directory.mkdirs();
    }
    private void setPhotoIfExist()
    {
        File file = new File(getApplicationInfo().dataDir+"/Avatars" + "/" + "avatar_" + user.curent_id + ".jpg");
        try {
            Bitmap img = BitmapFactory.decodeFile(file.getAbsolutePath());
            fragment_avater.ivPhoto.setImageBitmap(img);
        }
        catch (Exception e){
            Toast.makeText(this,"Avatar not found",Toast.LENGTH_SHORT).show();
        }
    }
    private void savePhotoAsAvatar(Bitmap img)
    {
        File file = new File(getApplicationInfo().dataDir+"/Avatars" + "/" + "avatar_" + user.curent_id + ".jpg");
        try {
            FileOutputStream fstream = new FileOutputStream(file);
            img.compress(Bitmap.CompressFormat.JPEG, 85, fstream);
        }
        catch (Exception e){e.printStackTrace();}
    }
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent)
    {
        switch (requestCode)
        {
            case REQUEST_CODE_PHOTO:
                if (resultCode == RESULT_OK)
                {
                   // String path = getCorrentUserAvatarUri().toString();
                    //File file = new File(path);
                    Bundle bndl = intent.getExtras();
                    if (bndl != null) {
                        Object obj = intent.getExtras().get("data");
                        if (obj instanceof Bitmap) {
                            Bitmap bitmap = (Bitmap) obj;
                            savePhotoAsAvatar(bitmap);
                            fragment_avater.ivPhoto.setImageBitmap(bitmap);
                        }
                    }
                }
                break;
            case REQUEST_CODE_GALERY:
                Bitmap img = null;
                if (resultCode == RESULT_OK)
                {
                    Uri selectedImage = intent.getData();
                    try {
                        img = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    }catch (Exception e) {e.printStackTrace();}
                    savePhotoAsAvatar(img);
                    fragment_avater.ivPhoto.setImageBitmap(img);
                    break;
                }
        }
    }
}
