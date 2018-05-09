package com.yscall.wallpaper.bean;

/**
 * 画廊实体类
 * @author gerile
 */
public class GalleryInfo {

    private final int id;
    private final String title;
    private final int image;

    public GalleryInfo(int id, String title, int image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }
}
