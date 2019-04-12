package com.example.ig.tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ig.R;
import com.example.ig.dashboard.DashboardGoodsDailyUse;
import com.example.ig.dashboard.DashboardGoodsHat;
import com.example.ig.dashboard.DashboardGoodsClothing;
import com.example.ig.dashboard.DashboardGoodsStationery;

import java.util.ArrayList;
import java.util.List;

public class TabDashboardFragment extends Fragment {
    private static ArrayList<Button> buttonArrayList = new ArrayList<>();
    private static ArrayList<Button> buttonLineArrayList = new ArrayList<>();
    private static ArrayList<Button> buttonLineGrayArrayList = new ArrayList<>();

    static {
        buttonArrayList.add(null);
        buttonArrayList.add(null);
        buttonArrayList.add(null);
        buttonArrayList.add(null);
        buttonArrayList.add(null);

        buttonLineArrayList.add(null);
        buttonLineArrayList.add(null);
        buttonLineArrayList.add(null);
        buttonLineArrayList.add(null);
        buttonLineArrayList.add(null);

        buttonLineGrayArrayList.add(null);
        buttonLineGrayArrayList.add(null);
        buttonLineGrayArrayList.add(null);
        buttonLineGrayArrayList.add(null);
        buttonLineGrayArrayList.add(null);
    }

    private ViewPager mViewPager;
    // 适配器
    private FragmentPagerAdapter mAdapter;
    // 装载fragment
    private List<Fragment> mFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_dashboard, container, false);


        buttonArrayList.set(1, (Button) view.findViewById(R.id.dashboard_goods_type_man));
        buttonArrayList.set(2, (Button) view.findViewById(R.id.dashboard_goods_type_woman));
        buttonArrayList.set(0, (Button) view.findViewById(R.id.dashboard_goods_type_eat));
        buttonArrayList.set(3, (Button) view.findViewById(R.id.dashboard_goods_type_hat));

        buttonLineArrayList.set(1, (Button) view.findViewById(R.id.dashboard_goods_type_line2));
        buttonLineArrayList.set(2, (Button) view.findViewById(R.id.dashboard_goods_type_line3));
        buttonLineArrayList.set(0, (Button) view.findViewById(R.id.dashboard_goods_type_line4));
        buttonLineArrayList.set(3, (Button) view.findViewById(R.id.dashboard_goods_type_line5));

        buttonLineGrayArrayList.set(1, (Button) view.findViewById(R.id.dashboard_goods_type_line_gray2));
        buttonLineGrayArrayList.set(2, (Button) view.findViewById(R.id.dashboard_goods_type_line_gray3));
        buttonLineGrayArrayList.set(0, (Button) view.findViewById(R.id.dashboard_goods_type_line_gray4));
        buttonLineGrayArrayList.set(3, (Button) view.findViewById(R.id.dashboard_goods_type_line_gray5));

        mViewPager = view.findViewById(R.id.dashboard_viewpager);
        mFragments = new ArrayList<>();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mFragments.add(new DashboardGoodsDailyUse());
        mFragments.add(new DashboardGoodsClothing());
        mFragments.add(new DashboardGoodsStationery());
        mFragments.add(new DashboardGoodsHat());

        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
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
//        mViewPager.setOffscreenPageLimit(2);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position);
                for (int i = 0; i < mFragments.size(); i++) {
                    if (i == position) {
                        buttonArrayList.get(i).setTextColor(getResources().getColor(R.color.themeColor));
                        buttonLineArrayList.get(i).setBackgroundColor(getResources().getColor(R.color.themeColor));
                        buttonLineGrayArrayList.get(i).setBackgroundColor(getResources().getColor(R.color.themeColor));
                    } else {
                        buttonArrayList.get(i).setTextColor(getResources().getColor(R.color.colorNotificationsFalse));
                        buttonLineArrayList.get(i).setBackgroundColor(getResources().getColor(R.color.colorWhite));
                        buttonLineGrayArrayList.get(i).setBackgroundColor(getResources().getColor(R.color.NotificationsFalseGray));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        for (int i = 0; i < mFragments.size(); i++) {
            buttonArrayList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < mFragments.size(); j++) {
                        if (buttonArrayList.get(j).getId() == v.getId()) {
                            buttonArrayList.get(j).setTextColor(getResources().getColor(R.color.themeColor));
                            buttonLineArrayList.get(j).setBackgroundColor(getResources().getColor(R.color.themeColor));
                            buttonLineGrayArrayList.get(j).setBackgroundColor(getResources().getColor(R.color.themeColor));
                            mViewPager.setCurrentItem(j);
                        } else {
                            buttonArrayList.get(j).setTextColor(getResources().getColor(R.color.colorNotificationsFalse));
                            buttonLineArrayList.get(j).setBackgroundColor(getResources().getColor(R.color.colorWhite));
                            buttonLineGrayArrayList.get(j).setBackgroundColor(getResources().getColor(R.color.NotificationsFalseGray));
                        }
                    }
                }
            });
        }
    }
}
