package com.example.zh.myapplication.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
import android.widget.ImageView;

import com.example.zh.myapplication.interfa.ImageloaderInter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zh on 2016/9/1.
 */
public class ImageLoader {
    ImageloaderInter mImageCache;
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    public void displayImage(final String url, final ImageView imageView) {

        if (mImageCache.get(url) != null) {
            imageView.setImageBitmap(mImageCache.get(url));
            return;
        }

        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(url, bitmap);
            }
        });

    }

    public Bitmap downloadImage(String url) {
        Bitmap bitmap = null;
        try {
            URL urlImage = new URL(url);
            final HttpURLConnection conn = (HttpURLConnection) urlImage.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public void setImageCacheType(ImageloaderInter cacheType) {
        mImageCache = cacheType;
    }

}
