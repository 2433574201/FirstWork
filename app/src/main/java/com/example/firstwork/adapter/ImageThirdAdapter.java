package com.example.firstwork.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.firstwork.R;
import com.example.firstwork.model.ImageBitmap;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ImageThirdAdapter extends RecyclerView.Adapter<ImageThirdAdapter.ViewHolder> {

    public List<ImageBitmap> images;

    public ImageThirdAdapter(List<ImageBitmap> images) {
        this.images = images;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view_third);
            textView = itemView.findViewById(R.id.text_view_third);
        }
    }
    @NonNull
    @Override
    public ImageThirdAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_item_third,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "width："+v.getWidth()+" "+"height："+v.getHeight();
                Toast.makeText(v.getContext(),data,Toast.LENGTH_SHORT).show();
            }
        });
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = v.findViewById(R.id.text_view_third);
                String data =textView.getText().toString();
                Toast.makeText(v.getContext(),data,Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageThirdAdapter.ViewHolder holder, int position) {
             ImageBitmap image = images.get(position);
        Bitmap bitmap = getBitmapByName(holder.imageView.getContext(),image.getName());
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap placeHolder = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        Drawable drawable = new BitmapDrawable(placeHolder);
//        Glide.with(holder.imageView.getContext())
//                .load("file:///android_asset/"+image.getName())
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .preload(w,h);
        Glide.with(holder.imageView.getContext())
                .load("file:///android_asset/"+image.getName())
                .placeholder(drawable)
                .into(holder.imageView);
        holder.textView.setText(image.getName());
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public  Bitmap getBitmapByName(Context context , String name){
        InputStream is = null;
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            is = context.getAssets().open(name);
            bitmap = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(is!=null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}
