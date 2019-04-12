package com.example.ig.tabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ig.R;
import com.example.ig.controller.PercentCircle;
import com.example.ig.home.HomeRankListActivity;
import com.example.ig.home.Recommend0Activity;
import com.example.ig.home.Recommend1Activity;
import com.example.ig.home.Recommend2Activity;
import com.example.ig.weather.MainActivity;
import com.example.ig.weather.WeatherActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
//import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
//import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;


public class TabHomeFragment extends Fragment implements OnBannerListener {

    private RefreshLayout mRefreshLayout;


//    private ScrollView home_scrollView;
    // 小贴士
    private TextView homeTips;
    // 三块
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
    private TextView textView3;

    // 得到随机的小贴士
    private static String getRandomTip() {
        String[] tips = {"垃圾分类，指按一定规定或标准将垃圾分类储存、分类投放和分类搬运，从而转变成公共资源的一系列活动的总称。",
                "垃圾分类是为了将废弃物分流处理，利用现有生产制造能力，回收利用回收品，包括物质利用和能量利用，填埋处置暂时无法利用的无用垃圾。",
                "环境保护，是指人类为解决现实的或潜在的环境问题，协调人类与环境的关系，保障经济、社会的持续发展而采取的各种行动的总称。",
                "1962年美国海洋生物学家蕾切尔·卡逊在《寂静的春天》一书中明确描述了DDT对环境的污染和破坏，该书被认为是20世纪环境生态学的标志性起点。"};
        int index = (int) (Math.random() * tips.length);
        return tips[index];
    }


    // 显示信息
    private void toastShow(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
    }



    // 设置容量
    private void setPC(int pc1, int pc2, int pc3, int pc4) {
        if (percentCircle1 != null) {
            percentCircle1.setTargetPercent(pc1);
        } else {
            Toast.makeText(getActivity(), "PercentCircle1初始化失败", Toast.LENGTH_SHORT).show();
        }
        if (percentCircle2 != null) {
            percentCircle2.setTargetPercent(pc2);
        } else {
            Toast.makeText(getActivity(), "PercentCircle2初始化失败", Toast.LENGTH_SHORT).show();
        }
        if (percentCircle3 != null) {
            percentCircle3.setTargetPercent(pc3);
        } else {
            Toast.makeText(getActivity(), "PercentCircle3初始化失败", Toast.LENGTH_SHORT).show();
        }
        if (percentCircle4 != null) {
            percentCircle4.setTargetPercent(pc4);
        } else {
            Toast.makeText(getActivity(), "PercentCircle4初始化失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void addUserScore(int score) {
        SharedPreferences pref = getActivity().getSharedPreferences("user_xml", MODE_PRIVATE);
        String user_score = pref.getString("user_score", "");
        SharedPreferences.Editor editor = pref.edit();
        user_score = Integer.parseInt(user_score) + score + "";
        editor.apply();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_home, container, false);

        mRefreshLayout = view.findViewById(R.id.refreshLayout);



        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });



//        home_scrollView = (ScrollView) view.findViewById(R.id.home_scrollView);

        // 小贴士
        homeTips = view.findViewById(R.id.home_tips);
        homeTips.setText(getRandomTip());
        // 三块
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
//        textView2.setText("晴  -20℃~-10℃\n注意保暖哦");
        textView2.setText("关心天气\n更关心你");
        textView3 = view.findViewById(R.id.home_signin_text3);
        SharedPreferences prefs = getActivity().getSharedPreferences("user.xml", MODE_PRIVATE);
        int isSignIn = prefs.getInt(calendar.get(Calendar.MONTH + 1) + "" + calendar.get(Calendar.DAY_OF_MONTH), -1);
        if (isSignIn == -1) {
            textView3.setText("今日未签到\n" + (calendar.get(Calendar.MONTH) + 1) + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日");
        } else if (isSignIn == 0) {
            textView3.setText("今日已签到\n" + (calendar.get(Calendar.MONTH) + 1) + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日");
        }
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 打开蓝牙

        int pc1 = 0;
        int pc2 = 0;
        int pc3 = 0;
        int pc4 = 10;
        setPC(pc1, pc2, pc3, pc4);

        initBannerView();

        // 积分排行
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "该功能暂未开放", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), HomeRankListActivity.class);
                startActivity(intent);
            }
        });
        // 天气
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.example.ig.weather.MainActivity.class);
                startActivity(intent);
            }
        });
        // 签到
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                SharedPreferences prefs = getActivity().getSharedPreferences("user.xml", MODE_PRIVATE);
                String user_score = prefs.getString("user_score", null);
                int isSignIn = prefs.getInt(calendar.get(Calendar.MONTH + 1) + "" + calendar.get(Calendar.DAY_OF_MONTH), -1);
                if (isSignIn == -1) {
                    SharedPreferences.Editor editor = prefs.edit();
                    user_score = Integer.parseInt(user_score) + 2 + "";
                    editor.putString("user_score", user_score);
                    editor.putInt(calendar.get(Calendar.MONTH + 1) + "" + calendar.get(Calendar.DAY_OF_MONTH), 0);
                    editor.apply();
                    textView3.setText("今日已签到\n" + (calendar.get(Calendar.MONTH) + 1) + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日");
                    Toast.makeText(getActivity(), "今日签到, 积分+2", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "今日已签到", Toast.LENGTH_SHORT).show();
                }
            }

        });

//        home_scrollView.smoothScrollTo(0, 20);
//        home_scrollView.setFocusable(true);
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
                            if (position == 0) {
                                Intent intent = new Intent(getActivity(), Recommend0Activity.class);
                                startActivity(intent);
                            } else if (position == 1) {
                                Intent intent = new Intent(getActivity(), Recommend1Activity.class);
                                startActivity(intent);
                            } else if (position == 2) {
                                Intent intent = new Intent(getActivity(), Recommend2Activity.class);
                                startActivity(intent);
                            }
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
     *
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
