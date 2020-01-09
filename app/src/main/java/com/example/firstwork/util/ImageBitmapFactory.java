package com.example.firstwork.util;

import android.content.Context;
import com.example.firstwork.model.ImageBitmap;
import java.util.ArrayList;
import java.util.List;

public class ImageBitmapFactory {
    //vaporwave_1.png 1-55
    //emoji_1.png 1-41
    //face_1.png 1-76
    public static String PRE_FIRST = "pic/first/vaporwave_";
    public static String PRE_SECOND = "pic/second/emoji_";
    public static String PRE_THIRD = "pic/third/face_";
    public static String POST = ".png";
    public static List<ImageBitmap> images_first = new ArrayList<>();
    public static List<ImageBitmap> images_second = new ArrayList<>();
    public static List<ImageBitmap> images_third = new ArrayList<>();
    public static void getImages(Context context,List<ImageBitmap> images,
                                 String pre, int start, int end,String post)  {

        for(int i = start;i<=end;i++){
            String filename = pre+i+post;
            ImageBitmap imageBitmap = new ImageBitmap(filename);
            images.add(imageBitmap);
        }
    }
    public static void processAll(Context context)  {
        getImages(context,images_first,PRE_FIRST,1,55,POST);
        getImages(context,images_second,PRE_SECOND,1,41,POST);
        getImages(context,images_third,PRE_THIRD,1,76,POST);
    }
    public static void processThird(Context context)  {
        getImages(context,images_third,PRE_THIRD,1,76,POST);
    }
}
