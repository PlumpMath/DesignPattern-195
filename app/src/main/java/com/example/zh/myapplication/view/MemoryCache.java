package com.example.zh.myapplication.view;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.example.zh.myapplication.interfa.ImageloaderInter;

/**
 * Created by Shanzi on 2016/9/3.
 */
public class MemoryCache implements ImageloaderInter {

    LruCache<String, Bitmap> mMemoryCache;

    public MemoryCache() {
        final int maxMemory = (int) Runtime.getRuntime().maxMemory() / 1024;
        final int sizeMemory = maxMemory / 4;
        mMemoryCache = new LruCache<String, Bitmap>(sizeMemory){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }
    @Override

    public Bitmap get(String url) {
        return mMemoryCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {

        mMemoryCache.put(url, bitmap);
    }
}
