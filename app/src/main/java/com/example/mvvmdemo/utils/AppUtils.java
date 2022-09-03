package com.example.mvvmdemo.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.auth.model.CommonStatusMessageResponse;
import com.example.mvvmdemo.auth.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

public class AppUtils {
    public static boolean   isResponseKeyPresent(String keyName, String json){
        return json.contains(keyName);
    }
    public static void backToLoginScreen(Context context){
        Intent backLogin = new Intent(context, MainActivity.class);
        context.startActivity(backLogin);

    }
    public static File getFile(Context context, Uri uri) throws IOException {
        try {
            File destinationFilename = new File(context.getFilesDir().getPath() + File.separatorChar + queryName(context, uri));
            try (InputStream ins = context.getContentResolver().openInputStream(uri)) {
                createFileFromStream(ins, destinationFilename);
            } catch (Exception ex) {
                Log.e("Save File", ex.getMessage());
                ex.printStackTrace();
            }
            return destinationFilename;
        }catch (Exception e){
            e.printStackTrace();
            return null ;
        }
    }

    public static void createFileFromStream(InputStream ins, File destination) {
        try (OutputStream os = new FileOutputStream(destination)) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = ins.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
        } catch (Exception ex) {
            Log.e("Save File", ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static String queryName(Context context, Uri uri) {
        Cursor cursor = null;
        try {
            String name = null ;
            if (uri.getScheme().equals("content")) {
                cursor = context.getContentResolver().query(uri, null, null, null, null);
                assert cursor != null;
                int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                cursor.moveToFirst();
                name = cursor.getString(nameIndex);
            }
            if (name == null) {
                name = uri.getPath();
                int cut = name.lastIndexOf('/');
                if (cut != -1) {
                    name = name.substring(cut + 1);
                }
            }

            return name;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }finally {
            if(null != cursor){
                cursor.close();
            }
        }
    }

    public static String getServerError(int responseCode, ResponseBody responseBody, Context context){
        String serverHandling =  "Error "+responseCode+" "+"Please try again.";

        switch (responseCode){
            case 401:
                //LogOutTheUser
                AppUtils.backToLoginScreen(context);
                serverHandling = "Session Timed Out.";
                break;
            case 400:
                //Bad Request Display The Message

                try {
                    if(responseBody!=null){
                        Gson gson = new GsonBuilder().create();
                        CommonStatusMessageResponse commonStatusMessageResponseOne = gson.fromJson(responseBody.string(),CommonStatusMessageResponse.class);
                        Log.i("ERROR_BODY",commonStatusMessageResponseOne.getMessage());
                        serverHandling = commonStatusMessageResponseOne.getMessage();
                        return serverHandling;
                    }
                } catch (Exception e){

                    e.printStackTrace();
                }



        }
        return serverHandling;

    }

    public static void addFragmentBackStack(FragmentManager fragmentManager, Fragment fragment, String tag , boolean isAddToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
          fragmentTransaction.add(R.id.flFragment, fragment);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.commit();
    }



}
