package com.yscall.wallpaper.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.yscall.wallpaper.AppContext;
import com.yscall.wallpaper.common.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 你的样子 on 2018/5/9.
 * 文件工具
 *
 * @author gerile
 */
public class FileUtil {

    public static void createDirectory() {
        File folder = new File(Constants.ROOT_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    /**
     * 存储资源为ID的图片
     */
    public static boolean drawableSaveToDirectory(int id, String name) {
        File file = new File(Constants.ROOT_FOLDER,
                name);
        if(file.exists()) return true;
        Drawable drawable = AppContext.getAppContext().getResources().getDrawable(id);
        if (drawable == null)
            return false;
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        if (bitmap == null)
            return false;
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
