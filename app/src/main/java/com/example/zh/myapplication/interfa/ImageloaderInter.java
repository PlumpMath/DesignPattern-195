package com.example.zh.myapplication.interfa;

import android.graphics.Bitmap;

/**
 * Created by Shanzi on 2016/9/3.
 */
public interface ImageloaderInter {
    public Bitmap get(String url);

    public void put(String url, Bitmap bitmap);
}
