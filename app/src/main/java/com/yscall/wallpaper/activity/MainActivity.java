package com.yscall.wallpaper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;
import com.yscall.wallpaper.R;
import com.yscall.wallpaper.adapter.GalleryAdapter;
import com.yscall.wallpaper.bean.GalleryInfo;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements DiscreteScrollView.OnItemChangedListener,
        View.OnClickListener {

    private List<GalleryInfo> data;

    private TextView title;
    private DiscreteScrollView picker;
    private InfiniteScrollAdapter infiniteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
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
                break;
        }
    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
        int position = infiniteAdapter.getRealPosition(adapterPosition);
        title.setText(data.get(position).getTitle());
    }

}
