package com.example.zh.myapplication.view;

import android.graphics.Bitmap;

import com.example.zh.myapplication.interfa.ImageloaderInter;

/**
 * Created by Zys on 2016/9/12.
 */
public class DoubleCache implements ImageloaderInter{

    MemoryCache memoryCache = new MemoryCache();
    DiskCache diskCache = new DiskCache();

    public Bitmap get(String url) {
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap == null) {
            bitmap = diskCache.get(url);
        }
        return bitmap;
    }

    public void put(String url, Bitmap bitmap) {

        memoryCache.put(url, bitmap);
        diskCache.put(url, bitmap);
    }
}
