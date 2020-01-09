package com.example.firstwork.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firstwork.R;
import com.example.firstwork.model.MImage;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class ImageSecondAdapter extends RecyclerView.Adapter<ImageSecondAdapter.ViewHolder> {
    private static final String TAG = "ImageSecondAdapter";
    private List<MImage> images;
    Timer timer = new Timer();

    public ImageSecondAdapter(List<MImage> images) {
        this.images = images;
    }


    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
//        final ViewHolder viewHolder = holder;
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                viewHolder.textView_ji.setText(images.get(viewHolder.getAdapterPosition()).getName()+"+"+
//                        viewHolder.getAdapterPosition());
//            }
//        }, 3000);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        Log.d("Recycle","onViewDetachedFromWindow"+holder.getAdapterPosition());
//        holder.textView_ji.setText(images.get(holder.getAdapterPosition()).getName());
        super.onViewDetachedFromWindow(holder);
    }


     class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout_ji;
        ImageView imageView_ji;
        TextView textView_ji;

        ConstraintLayout layout_ou;
        ImageView imageView_ou;
        TextView textView_ou;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_ji = itemView.findViewById(R.id.ji_second_layout);
            imageView_ji = itemView.findViewById(R.id.image_view_ji_second);
            textView_ji = itemView.findViewById(R.id.text_view_ji_second);

            layout_ou = itemView.findViewById(R.id.ou_second_layout);
            imageView_ou = itemView.findViewById(R.id.image_view_ou_second);
            textView_ou= itemView.findViewById(R.id.text_view_ou_second);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_item_second,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        //Log.d("Recycle",position+" ");
        Log.d(TAG, "onBindViewHolder: "+position);
        final MImage image = images.get(position);
        if((position%2)!=0){
            //奇数
            holder.layout_ou.setVisibility(View.GONE);
            holder.layout_ji.setVisibility(View.VISIBLE);
            //holder.imageView_ji.setImageResource(image.getImageId());
            Glide.with(holder.imageView_ji.getContext())
                    .load(image.getImageId())
                    .into(holder.imageView_ji);
            holder.textView_ji.setText(image.getName());
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(position==holder.getAdapterPosition())
                        holder.textView_ji.setText(image.getName() + "+" + position);
                }
            }, 3000);
        }else{
            holder.layout_ou.setVisibility(View.VISIBLE);
            holder.layout_ji.setVisibility(View.GONE);
            //holder.imageView_ou.setImageResource(image.getImageId());
            Glide.with(holder.imageView_ou.getContext()).load(image.getImageId()).into(holder.imageView_ou);
            if((position%4)==0) {
                holder.textView_ou.setText("Pro");
                holder.textView_ou.setTextColor(Color.RED);
                holder.textView_ou.setBackgroundColor(Color.WHITE);
            }else{
                holder.textView_ou.setText("Free");
            }
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

}
