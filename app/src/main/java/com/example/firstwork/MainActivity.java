package com.example.firstwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.firstwork.adapter.AdapterViewPager;
import com.example.firstwork.adapter.ImageAdapter;
import com.example.firstwork.adapter.ImageSecondAdapter;
import com.example.firstwork.adapter.ImageThirdAdapter;
import com.example.firstwork.model.ImageBitmap;
import com.example.firstwork.model.MImage;
import com.example.firstwork.util.ImageBitmapFactory;
import com.example.firstwork.util.MImageLoader;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Field[] fields;
    ImageAdapter imageAdapter;
    ImageSecondAdapter imageAdapter_second;
    RecyclerView.Adapter imageAdapter_third;

    private List<View> viewList;
    private AdapterViewPager adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        viewList = new ArrayList<>();
        fields = R.drawable.class.getFields();
        ImageBitmapFactory.processThird(this);

        List<MImage> images_first = MImageLoader.getImagesByName(this,fields,"vaporwave");
        List<MImage> images_second = MImageLoader.getImagesByName(this,fields,"emoji");
        List<ImageBitmap> images_third = ImageBitmapFactory.images_third;

        imageAdapter = new ImageAdapter(images_first);
        imageAdapter_second = new ImageSecondAdapter(images_second);
        imageAdapter_third = new ImageThirdAdapter(images_third);

        initAdapter();

        ViewPager viewPager = findViewById(R.id.view_pager);
        adapterViewPager = new AdapterViewPager(viewList);
        viewPager.setAdapter(adapterViewPager);
        
    }

    public void initAdapter(){
        View view = View.inflate(this,R.layout.recycleview_first,null);
        processView(view,2,R.id.recycle_view_first,imageAdapter);
        viewList.add(view);
        View view_second = View.inflate(this,R.layout.recycleview_second,null);
        processView(view_second,3,R.id.recycle_view_second,imageAdapter_second);
        viewList.add(view_second);
        View view_third = View.inflate(this,R.layout.recycleview_third,null);
        processView(view_third,4,R.id.recycle_view_third,imageAdapter_third);
        viewList.add(view_third);
    }
    public void processView(View view, int spanCount, int resId, final RecyclerView.Adapter imageAdapter){
        RecyclerView recyclerView = view.findViewById(resId);
        StaggeredGridLayoutManager staggeredGridLayoutManager=
                new StaggeredGridLayoutManager(spanCount,StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        staggeredGridLayoutManager.setItemPrefetchEnabled(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setDrawingCacheEnabled(true);
        //recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(imageAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
    }
}
