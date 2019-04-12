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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ig.Config;
import com.example.ig.account.CallbackActivity;
import com.example.ig.account.IntegralRuleActivity;
import com.example.ig.R;
import com.example.ig.account.SettingsActivity;

public class TabAccountFragment extends Fragment {
    // 分享软件的分享文本
    private static final String shareText = "欢迎使用分类精灵APP，各大应用市场均可下载";

    private TextView userNameTextView;
    private RelativeLayout settings;        // 设置
    private RelativeLayout integralRule;    // 积分规则
    private RelativeLayout share;           // 分享
    private RelativeLayout update;          // 更新
    private RelativeLayout connect;         // 报修
    private RelativeLayout callback;        // 用户反馈
    private TextView textViewScore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_account, container, false);

        userNameTextView = view.findViewById(R.id.user_name);
        settings = view.findViewById(R.id.account_settings);
        integralRule = view.findViewById(R.id.account_integralRule);
        share = view.findViewById(R.id.account_share);
        update = view.findViewById(R.id.account_update);
        connect = view.findViewById(R.id.account_connect);
        callback = view.findViewById(R.id.account_callback);
        textViewScore = view.findViewById(R.id.user_score);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPreferences pref = getActivity().getSharedPreferences(Config.Xml, Config.XmlModel);
        String userName = pref.getString(Config.username, "");
        userNameTextView.setText(userName);
        // 设置
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });
        //积分规则
        integralRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntegralRuleActivity.class);
                startActivity(intent);
            }
        });
        // 分享软件
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                startActivity(Intent.createChooser(shareIntent, "分享"));
            }
        });
        // 版本更新
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "已是最新版本", Toast.LENGTH_SHORT).show();
            }
        });
        // 联系报修
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "该功能暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
        // 反馈留言
        callback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CallbackActivity.class);
                startActivity(intent);
            }
        });
        // 积分
        if (this.getActivity() != null) {
            SharedPreferences preferences = this.getActivity().getSharedPreferences("user.xml", Context.MODE_PRIVATE);
            String user_score = preferences.getString("user_score", null);
            if (user_score == null) {
                textViewScore.setText("0");
            } else {
                textViewScore.setText(user_score);
            }
        }

    }

}
