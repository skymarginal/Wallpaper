package com.yscall.wallpaper.activity;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;
import com.yscall.wallpaper.R;
import com.yscall.wallpaper.adapter.GalleryAdapter;
import com.yscall.wallpaper.bean.GalleryInfo;
import com.yscall.wallpaper.task.SetWallpaperTask;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements DiscreteScrollView.OnItemChangedListener,
        View.OnClickListener, SetWallpaperTask.OnTaskCallback {

    private static final int REQUEST_PERMISSIONS = 1;
    private String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private List<GalleryInfo> data;
    private GalleryInfo currentInfo;

    private TextView title;
    private DiscreteScrollView picker;
    private InfiniteScrollAdapter infiniteAdapter;

    private WallpaperManager wallpaperManager;
    private boolean isPerform = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        wallpaperManager = WallpaperManager.getInstance(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean isAllGranted = checkPermissionAllGranted(permissions);
            if (!isAllGranted) {
                ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS);
            }
        }
    }

    private void initView() {
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.fab).setOnClickListener(this);
        title = findViewById(R.id.title);
        picker = findViewById(R.id.picker);
        picker.setOrientation(DSVOrientation.HORIZONTAL);
        picker.addOnItemChangedListener(this);
        infiniteAdapter = InfiniteScrollAdapter.wrap(new GalleryAdapter(data));
        picker.setAdapter(infiniteAdapter);
        picker.setItemTransitionTimeMillis(250);
        picker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
//                Intent intent = new Intent(Intent.ACTION_SET_WALLPAPER);
//                startActivity(Intent.createChooser(intent, "选择壁纸"));
                if (!isPerform) {
                    SetWallpaperTask task = new SetWallpaperTask(wallpaperManager, this);
                    task.execute(currentInfo.getImage());
                    isPerform = true;
                } else {
                    Toast.makeText(this, "请等待", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
        int position = infiniteAdapter.getRealPosition(adapterPosition);
        currentInfo = data.get(position);
        title.setText(currentInfo.getTitle());
    }

    @Override
    public void onCallback(Boolean aBoolean) {
        isPerform = false;
        if (aBoolean) {
            Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "设置失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void initData() {
        data = Arrays.asList(
                new GalleryInfo(1, "静谧多瑙河", R.drawable.wall01),
                new GalleryInfo(2, "冰川世纪", R.drawable.wall02),
                new GalleryInfo(3, "最美公路", R.drawable.wall03),
                new GalleryInfo(4, "浪花怒放", R.drawable.wall04),
                new GalleryInfo(5, "绚丽高原", R.drawable.wall05),
                new GalleryInfo(6, "多彩伦敦", R.drawable.wall06),
                new GalleryInfo(7, "日落西滩", R.drawable.wall07),
                new GalleryInfo(8, "优雅提琴", R.drawable.wall08),
                new GalleryInfo(9, "深港夜色", R.drawable.wall09),
                new GalleryInfo(10, "孤独远方", R.drawable.wall10),
                new GalleryInfo(11, "碧海蓝天", R.drawable.wall11),
                new GalleryInfo(12, "温暖北极", R.drawable.wall12));
    }

    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

}
