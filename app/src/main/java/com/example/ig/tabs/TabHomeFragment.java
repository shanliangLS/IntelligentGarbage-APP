package com.example.ig.tabs;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ig.MainActivity;
import com.example.ig.R;
import com.example.ig.controller.PercentCircle;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Calendar;


public class TabHomeFragment extends Fragment implements OnBannerListener{
    private ScrollView home_scrollView;

    private LinearLayout rank;        // 积分排行
    private LinearLayout weather;    // 今日天气
    private LinearLayout signin;           // 每日签到

    // PercentCircle
    private PercentCircle percentCircle1;
    private PercentCircle percentCircle2;
    private PercentCircle percentCircle3;
    private PercentCircle percentCircle4;
    //
    // 轮播图
    private Banner mBanner;
    private ArrayList<Integer> imagePath;
    private ArrayList<String> imageTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_home, container, false);
        home_scrollView = (ScrollView) view.findViewById(R.id.home_scrollView);

        rank = view.findViewById(R.id.home_rank);
        weather = view.findViewById(R.id.home_weather);
        signin = view.findViewById(R.id.home_signin);

        // 4个垃圾桶
        percentCircle1 = (PercentCircle) view.findViewById(R.id.percentcircle1);
        percentCircle2 = (PercentCircle) view.findViewById(R.id.percentcircle2);
        percentCircle3 = (PercentCircle) view.findViewById(R.id.percentcircle3);
        percentCircle4 = (PercentCircle) view.findViewById(R.id.percentcircle4);
        // 轮播图
        mBanner = (Banner) view.findViewById(R.id.banner);
        imagePath = new ArrayList<>();
        imageTitle = new ArrayList<>();
        imagePath.add(R.drawable.carousel1);
        imagePath.add(R.drawable.carousel2);
        imagePath.add(R.drawable.carousel3);
        imageTitle.add("绿色环保");
        imageTitle.add("垃圾分类");
        imageTitle.add("河北工业大学");
        // 积分排行今日天气每日签到
        Calendar calendar = Calendar.getInstance();
        TextView textView1 = view.findViewById(R.id.home_rank_text1);
        textView1.setText("快来看看\n排名吧");
        TextView textView2 = view.findViewById(R.id.home_weather_text2);
        textView2.setText("晴  -20℃~-10℃\n注意保暖哦");
        TextView textView3 = view.findViewById(R.id.home_signin_text3);
        textView3.setText("今日已签到\n" + (calendar.get(Calendar.MONTH)+1) + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (percentCircle1 != null) {
            percentCircle1.setTargetPercent(25);
        } else {
            Toast.makeText(getActivity(), "PercentCircle1初始化失败", Toast.LENGTH_SHORT).show();
        }
        if (percentCircle2 != null) {
            percentCircle2.setTargetPercent(45);
        } else {
            Toast.makeText(getActivity(), "PercentCircle2初始化失败", Toast.LENGTH_SHORT).show();
        }
        if (percentCircle3 != null) {
            percentCircle3.setTargetPercent(65);
        } else {
            Toast.makeText(getActivity(), "PercentCircle3初始化失败", Toast.LENGTH_SHORT).show();
        }
        if (percentCircle4 != null) {
            percentCircle4.setTargetPercent(85);
        } else {
            Toast.makeText(getActivity(), "PercentCircle4初始化失败", Toast.LENGTH_SHORT).show();
        }
        initBannerView();


        // 版本更新
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "该功能暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
        // 联系报修
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "该功能暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
        // 版本更新
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "今日已签到", Toast.LENGTH_SHORT).show();
            }
        });

        home_scrollView.smoothScrollTo(0,20);
        home_scrollView.setFocusable(true);
    }

    // 初始化轮播图
    private void initBannerView() {
        if (mBanner != null) {
            //设置样式，里面有很多种样式可以自己都看看效果
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            //设置图片加载器
            mBanner.setImageLoader(new MyImageLoader());
            //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
            mBanner.setBannerAnimation(Transformer.ZoomOutSlide);
            //轮播图片的文字
            mBanner.setBannerTitles(imageTitle);
            //设置轮播间隔时间
            mBanner.setDelayTime(3000);
            //设置是否为自动轮播，默认是true
            mBanner.isAutoPlay(true);
            //设置指示器的位置，小点点，居中显示
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            //设置图片加载地址
            mBanner.setImages(imagePath)
                    //轮播图的监听
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {

                        }
                    })
                    //开始调用的方法，启动轮播图。
                    .start();
        } else {
            Toast.makeText(getActivity(), "Banner初始化失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 轮播图的监听
     * @param position
     */
    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getActivity(), "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
    }

    /**
     * 图片加载类
     */
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }

}
