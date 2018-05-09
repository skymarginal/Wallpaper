package com.yscall.wallpaper;

import android.app.Application;
import android.content.Context;

import com.yscall.wallpaper.utils.FileUtil;

/**
 * Created by 你的样子 on 2018/5/9.
 * Application
 *
 * @author geriel
 */
public class AppContext extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        FileUtil.createDirectory();
    }

    public static Context getAppContext() {
        return appContext;
    }

}
