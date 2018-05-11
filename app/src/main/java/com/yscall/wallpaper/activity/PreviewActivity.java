package com.yscall.wallpaper.activity;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * Created by 你的样子 on 2018/5/10.
 * 图片预览
 * @author gerile
 */
public class PreviewActivity extends Activity {

    public static void startPreviewActivity(Context context,int sourceId){
        Intent intent = new Intent(context,PreviewActivity.class);
        intent.putExtra("sourceId",sourceId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(imageView);

        super.onCreate(savedInstanceState);

        int sourceId = getIntent().getIntExtra("sourceId",0);
        if(sourceId == 0){
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            Drawable wallpaperDrawable = wallpaperManager.getDrawable();
            Bitmap bitmap = ((BitmapDrawable) wallpaperDrawable).getBitmap();
            if(bitmap != null){
                imageView.setImageBitmap(bitmap);
            }
        }else {
            Glide.with(this)
                    .load(sourceId)
                    .into(imageView);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(400);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setFillBefore(true);
        imageView.startAnimation(alphaAnimation);
    }

}
