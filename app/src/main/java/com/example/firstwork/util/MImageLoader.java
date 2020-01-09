package com.example.firstwork.util;

import android.content.Context;
import android.content.res.Resources;

import com.example.firstwork.model.MImage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MImageLoader {
    public static List<MImage> getImagesByName(Context context,Field[] fields,String name){
        List<MImage> images = new ArrayList<>();
        Resources resources = context.getResources();
        String pkgName = context.getPackageName();
        for(int i=0;i<fields.length;i++){
            if(!fields[i].isAccessible())fields[i].setAccessible(true);
            String fname = fields[i].getName();
            if(fname.contains(name)){
                int resId = resources.getIdentifier(fname,"drawable",pkgName);
                images.add(new MImage(fname,resId));
            }
        }
        return images;
    }
}
