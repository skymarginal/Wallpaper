package com.yscall.wallpaper.task;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.yscall.wallpaper.AppContext;

import java.io.IOException;

/**
 * Created by 你的样子 on 2018/5/9.
 * 设置壁纸异步任务
 * @author gerile
 */
public class SetWallpaperTask extends AsyncTask<Integer, Long, Boolean> {

    private WallpaperManager wallpaperManager;
    private OnTaskCallback callback;

    public SetWallpaperTask(WallpaperManager wallpaperManager,OnTaskCallback callback){
        this.wallpaperManager = wallpaperManager;
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(Integer... objects) {
        int id = objects[0];
        if(wallpaperManager != null){
            try {
                Bitmap bitmap = BitmapFactory.decodeResource(AppContext.getAppContext().getResources(),id);
                if(bitmap == null){
                    return false;
                }
                wallpaperManager.setBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if(callback != null){
            callback.onCallback(aBoolean);
        }
    }

    public interface OnTaskCallback{
        void onCallback(Boolean aBoolean);
    }
}
