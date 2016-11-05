package com.example.anastasia.application;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ProfilePage extends AppCompatActivity {

    FragmentAvatarStatus fragment_avater;
    Fragment fragment_name;
    FragmentTransaction fTrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        fragment_avater=new FragmentAvatarStatus();
       ImageView v = fragment_avater.ivPhoto;
        fragment_name=new FragmentNameEmail();
        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.frame1,fragment_avater);
        fTrans.add(R.id.frame2,fragment_name);
        fTrans.commit();
    }
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent)
    {
        switch (requestCode)
        {
            case FragmentAvatarStatus.REQUEST_CODE_PHOTO:
                if (resultCode == RESULT_OK)
                {
                    if (intent == null) {

                    } else {
                        Bundle bndl = intent.getExtras();
                        if (bndl != null) {
                            Object obj = intent.getExtras().get("data");
                            if (obj instanceof Bitmap) {
                                Bitmap bitmap = (Bitmap) obj;
                                fragment_avater.ivPhoto.setImageBitmap(bitmap);
                            }
                        }
                    }
                }
                break;
            case FragmentAvatarStatus.REQUEST_CODE_GALERY:
                Bitmap img = null;
                if (resultCode == RESULT_OK)
                {
                    Uri selectedImage = intent.getData();
                    try {
                        img = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    fragment_avater.ivPhoto.setImageBitmap(img);
                    break;
                }
        }
    }
}
