
package com.example.mvvmdemo.auth.view;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.auth.model.MUpload;
import com.example.mvvmdemo.auth.viewmodel.ImageUploadViewModel;
import com.example.mvvmdemo.databinding.ActivityPostBinding;
import com.example.mvvmdemo.utils.AppUtils;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

public class PostActivity extends AppCompatActivity {
    public static final int PICK_IMAGE = 1;
    private Context context;
    private File profileFile;
    ActivityPostBinding binding;
    ImageUploadViewModel imageUploadViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     binding = DataBindingUtil.setContentView(this,R.layout.activity_post);
     imageUploadViewModel = new ViewModelProvider(this).get(ImageUploadViewModel.class);
     imageUploadViewModel.init(this);
     binding.setViewmodel(imageUploadViewModel);
     initView();
     initClick();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            Uri uri = data.getData();
            try {
                profileFile = AppUtils.getFile(this, uri);
                Glide.with(getApplicationContext())
                        .load(uri)
                        .into(binding.ivSelectImage);
                Log.d(TAG, "onActivityResult: "+data.getData().toString());
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }



        }
    }

    private void initView(){
        imageUploadViewModel.observeUploadResponse().observe(this, new Observer<MUpload>() {
            @Override
            public void onChanged(MUpload mUpload) {
                //Toast.makeText(context, "ImageUpload"+mUpload.getMessage(), Toast.LENGTH_SHORT).show();
//                Glide.with(getApplicationContext())
//                        .load(mUpload.getMessage())
//                        .into(binding.ivShow);
                binding.txtData.setText(new Gson().toJson(mUpload));
            }
        });
        imageUploadViewModel.getIsFailed().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initClick() {
        binding.ivSelectImage.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        });
        binding.btnUpload.setOnClickListener(view -> {
            if (profileFile == null) {
                Toast.makeText(this,"Please select image", Toast.LENGTH_SHORT).show();
              }  else if (profileFile != null) {
                    imageUploadViewModel.callUploadImage(profileFile);
                }

        });

        binding.btnContact.setOnClickListener(view -> {
            Intent intent = new Intent(PostActivity.this,ContactActivity.class);
            startActivity(intent);
        });

        binding.btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(PostActivity.this,UserLoginActivity.class);
            startActivity(intent);
        });
    }


}
