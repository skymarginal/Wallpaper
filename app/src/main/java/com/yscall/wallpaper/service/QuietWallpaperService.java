package com.yscall.wallpaper.service;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

import com.yscall.wallpaper.AppContext;
import com.yscall.wallpaper.common.Constants;
import com.yscall.wallpaper.utils.FileUtil;
import java.io.IOException;

/**
 * Created by 你的样子 on 2018/5/11.
 * 安静的壁纸
 * @author gerile
 */
public class QuietWallpaperService extends WallpaperService {

    public Engine onCreateEngine() {
        return new VideoEngine();
    }

    private class VideoEngine extends Engine {

        private MediaPlayer mMediaPlayer;

        @Override
        public void onVisibilityChanged(boolean visible) {
//            if(mMediaPlayer != null){
//                if (visible) {
//                    mMediaPlayer.start();
//                } else {
//                    mMediaPlayer.pause();
//                }
//            }
        }

        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
            try {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(AppContext.getAppContext());
                Drawable wallpaperDrawable = wallpaperManager.getDrawable();
                Bitmap bitmap = ((BitmapDrawable) wallpaperDrawable).getBitmap();
                boolean result = FileUtil.bitmapToVideo(bitmap);
                if(result){
                    mMediaPlayer = new MediaPlayer();
                    mMediaPlayer.setSurface(holder.getSurface());
                    mMediaPlayer.setDataSource(Constants.QUIET_VIDEO_PATH);
                    mMediaPlayer.setLooping(true);
                    mMediaPlayer.setVolume(0, 0);
                    mMediaPlayer.prepare();
                    mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                                mMediaPlayer.start();
                                if(mMediaPlayer.isPlaying()){
                                    mMediaPlayer.pause();
                                }
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            if(mMediaPlayer != null){
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
        }
    }

}
