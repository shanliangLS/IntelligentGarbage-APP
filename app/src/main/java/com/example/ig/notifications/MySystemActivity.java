package com.example.ig.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ig.R;
import com.example.ig.account.CallbackActivity;
import com.example.ig.account.IntegralRuleActivity;
import com.example.ig.account.SettingsActivity;

public class MySystemActivity extends Fragment {
    private RelativeLayout notice;          // 通知
    private RelativeLayout fabulous;        // 赞
    private RelativeLayout follow;          // 关注

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notifications_system, container, false);

        notice = view.findViewById(R.id.notifications_system_notice);
        fabulous = view.findViewById(R.id.notifications_system_fabulous);
        follow = view.findViewById(R.id.notifications_system_follow);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 通知
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NotificationNoticeMessage.class);
                startActivity(intent);
            }
        });
        // 赞
        fabulous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GoodActivity.class);
                startActivity(intent);
            }
        });
        // 关注
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImportantActivity.class);
                startActivity(intent);
            }
        });
    }
}
