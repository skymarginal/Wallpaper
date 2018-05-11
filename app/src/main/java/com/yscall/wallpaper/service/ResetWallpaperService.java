package com.yscall.wallpaper.service;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;

import com.yscall.wallpaper.AppContext;


/**
 * Created by 你的样子 on 2018/5/10.
 * 重置壁纸服务
 *
 * @author gerile
 */
public class ResetWallpaperService extends WallpaperService {

    public Engine onCreateEngine() {
        return new VideoEngine();
    }

    class VideoEngine extends Engine {

        @Override
        public void onVisibilityChanged(boolean visible) {
        }

        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(AppContext.getAppContext());
            Drawable wallpaperDrawable = wallpaperManager.getDrawable();
            Bitmap bitmap = ((BitmapDrawable) wallpaperDrawable).getBitmap();
            if(bitmap != null && !bitmap.isRecycled()){
                Canvas canvas = holder.lockCanvas();
                if (canvas != null) {
                    Paint paint = new Paint();
                    canvas.drawBitmap(bitmap, 0, 0, paint);
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            Log.i("==TAG==","onSurfaceChanged --");
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            Log.i("==TAG==","onSurfaceDestroyed --");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("==TAG==","onDestroy --");
//        Intent resetIntent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
//        resetIntent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
//                new ComponentName(this, ResetWallpaperService.class));
//        startActivity(resetIntent);
    }
}
