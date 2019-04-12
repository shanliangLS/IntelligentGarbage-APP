package com.example.ig;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.ig.service.AutoUpdateService;
import com.example.ig.tabs.TabAccountFragment;
import com.example.ig.tabs.TabDashboardFragment;
import com.example.ig.tabs.TabHomeFragment;
import com.example.ig.tabs.TabNotificationsFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends FragmentActivity {

    private static final String ActivityName = "MainActivity:";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = null;
    // ViewPager
    private ViewPager mViewPager;
    // 适配器
    private FragmentPagerAdapter mAdapter;
    // 装载fragment
    private List<Fragment> mFragments;

    // nav
    private BottomNavigationView navigation;

    // 设置双击退出
    private static boolean mBackKeyPressed = false;

    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = getSharedPreferences(Config.Xml, Config.XmlModel);
        String username = pref.getString(Config.username, null);
        if (username == null) {
            finish();
        }
        Log.e(ActivityName, "退出登录-->退出程序");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(Color.TRANSPARENT);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor();
        }
//        状态栏字体颜色
        View decor = getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        initViews();    // 初始化控件
        initDatas();    // 初始化事件
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // 初始化用户分数
        initUserScore();
        // 启动后台服务
        Intent i = new Intent(this, AutoUpdateService.class);
        startService(i);
    }

    // 初始化用户分数
    private void initUserScore() {
        SharedPreferences pref = getSharedPreferences("user.xml", MODE_PRIVATE);
        String user_score = pref.getString("user_score", null);
        if (user_score == null) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("user_score", "0");
            editor.apply();
        }
    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        //
//        mViewPager.setOffscreenPageLimit(3); // 设置缓存数量
        //
        navigation = (BottomNavigationView) findViewById(R.id.navigation);

    }

    private void initDatas() {
        mFragments = new ArrayList<>();
        mFragments.add(new TabHomeFragment());
        mFragments.add(new TabDashboardFragment());
        mFragments.add(new TabNotificationsFragment());
        mFragments.add(new TabAccountFragment());

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position);
                navigation.setSelectedItemId(navigation.getMenu().getItem(position).getItemId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mViewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_dashboard:
                        mViewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_notifications:
                        mViewPager.setCurrentItem(2);
                        return true;
                    case R.id.navigation_account:
                        mViewPager.setCurrentItem(3);
                        return true;
                }
                return false;
            }
        };
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
