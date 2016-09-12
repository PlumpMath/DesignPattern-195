package com.example.zh.myapplication.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.zh.myapplication.interfa.ImageloaderInter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Shanzi on 2016/9/3.
 */
public class DiskCache implements ImageloaderInter {
    static String diskCacheDir = "sdcard/design_pattern_sdcard_cache/";
    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(diskCacheDir + url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(diskCacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
