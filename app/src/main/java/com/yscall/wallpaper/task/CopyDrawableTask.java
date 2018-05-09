package com.yscall.wallpaper.task;

import android.os.AsyncTask;

import com.yscall.wallpaper.utils.FileUtil;

/**
 * Created by 你的样子 on 2018/2/3.
 * 下载图片异步任务
 * @author gerile
 */
public class CopyDrawableTask extends AsyncTask<Object,Long,Boolean> {

    private CopyResultCallback listener;

    public void setCopyResultCallback(CopyResultCallback listener){
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(Object[] maps) {
        int id = (int) maps[0];
        String name = (String) maps[1];
        return FileUtil.drawableSaveToDirectory(id,name);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if(listener != null){
            listener.onCallback(aBoolean);
        }
    }

    public interface CopyResultCallback{
        void onCallback(Boolean aBoolean);
    }

}
