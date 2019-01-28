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
import android.widget.ListView;

import com.example.ig.R;
import com.example.ig.controller.NotificationMessage;
import com.example.ig.notifications.MyFriendActivity;
import com.example.ig.notifications.MySystemActivity;

import java.util.ArrayList;
import java.util.List;

public class TabNotificationsFragment extends Fragment {

    private Button btnFriend;
    private Button btnFriendLine;
    private Button btnFriendLineGray;
    private Button btnSystem;
    private Button btnSystemLine;
    private Button btnSystemLineGray;
    private ViewPager mViewPager;
    // 适配器
    private FragmentPagerAdapter mAdapter;
    // 装载fragment
    private List<Fragment> mFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_notifications, container, false);
        btnFriend = view.findViewById(R.id.notifications_friend);
        btnSystem = view.findViewById(R.id.notifications_system);
        mViewPager = view.findViewById(R.id.notifications_viewpager);
        mFragments = new ArrayList<>();
        btnFriendLine = view.findViewById(R.id.notifications_friend_line);
        btnSystemLine = view.findViewById(R.id.notifications_system_line);
        btnFriendLineGray = view.findViewById(R.id.notifications_friend_line_gray);
        btnSystemLineGray = view.findViewById(R.id.notifications_system_line_gray);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFragments.add(new MyFriendActivity());
        mFragments.add(new MySystemActivity());
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
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position);
                if (position == 0) {
                    btnFriend.setTextColor(getResources().getColor(R.color.themeColor));
                    btnFriendLine.setBackgroundColor(getResources().getColor(R.color.themeColor));
                    btnFriendLineGray.setBackgroundColor(getResources().getColor(R.color.themeColor));
                    btnSystem.setTextColor(getResources().getColor(R.color.colorNotificationsFalse));
                    btnSystemLine.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    btnSystemLineGray.setBackgroundColor(getResources().getColor(R.color.NotificationsFalseGray));
                } else if (position == 1) {
                    btnSystem.setTextColor(getResources().getColor(R.color.themeColor));
                    btnSystemLine.setBackgroundColor(getResources().getColor(R.color.themeColor));
                    btnSystemLineGray.setBackgroundColor(getResources().getColor(R.color.themeColor));
                    btnFriend.setTextColor(getResources().getColor(R.color.colorNotificationsFalse));
                    btnFriendLine.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    btnFriendLineGray.setBackgroundColor(getResources().getColor(R.color.NotificationsFalseGray));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btnSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSystem.setTextColor(getResources().getColor(R.color.themeColor));
                btnSystemLine.setBackgroundColor(getResources().getColor(R.color.themeColor));
                btnFriend.setTextColor(getResources().getColor(R.color.colorNotificationsFalse));
                btnFriendLine.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                mViewPager.setCurrentItem(1);
            }
        });
        btnFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnFriend.setTextColor(getResources().getColor(R.color.themeColor));
                btnFriendLine.setBackgroundColor(getResources().getColor(R.color.themeColor));
                btnSystem.setTextColor(getResources().getColor(R.color.colorNotificationsFalse));
                btnSystemLine.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                mViewPager.setCurrentItem(0);
            }
        });
    }
}
