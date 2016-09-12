package com.example.zh.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.zh.myapplication.interfa.ImageloaderInter;
import com.example.zh.myapplication.view.DiskCache;
import com.example.zh.myapplication.view.DoubleCache;
import com.example.zh.myapplication.view.ImageLoader;
import com.example.zh.myapplication.view.MemoryCache;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    private ImageView my_show;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_show = (ImageView) findViewById(R.id.iv_my_show);

        btn = (Button) findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageLoader imageLoader = new ImageLoader();
                //内存缓存
                imageLoader.setImageCacheType(new MemoryCache());
                //本地缓存
                imageLoader.setImageCacheType(new DiskCache());
                //双缓存
                imageLoader.setImageCacheType(new DoubleCache());
                //自定义缓存
                imageLoader.setImageCacheType(new ImageloaderInter() {
                    @Override
                    public Bitmap get(String url) {
                        return null;
                    }

                    @Override
                    public void put(String url, Bitmap bitmap) {

                    }
                });
                String url = "http://imgsrc.baidu.com/forum/pic/item/d7265e380cd791230540dad9ab345982b3b78025.jpg";
                imageLoader.displayImage(url, my_show);
            }
        });

    }

}
