package com.example.zh.myapplication.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.zh.myapplication.interfa.ImageloaderInter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Shanzi on 2016/9/3.
 */
public class DiskCache implements ImageloaderInter {
    static String diskCacheDir = "sdcard/cache/";
    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(diskCacheDir + url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {

        try {
            File file = new File(diskCacheDir + url);
            FileOutputStream outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
        }
    }
}
