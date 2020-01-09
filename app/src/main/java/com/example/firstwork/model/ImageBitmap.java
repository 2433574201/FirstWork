package com.example.firstwork.model;


import android.graphics.Bitmap;

public class ImageBitmap {
    private String name;
    private Bitmap bitmap;

    public ImageBitmap(String name) {
        this.name = name;
    }

    public ImageBitmap(String name, Bitmap bitmap) {
        this.name = name;
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
