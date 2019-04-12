package com.example.ig.account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ig.Config;
import com.example.ig.LoginActivity;
import com.example.ig.R;

public class SettingsActivity extends FragmentActivity {
    private Button backButton;
    private TextView textView;
    private RelativeLayout rlAccount;
    private RelativeLayout rlResetPassword;
    private RelativeLayout rlCleanUp;
    private RelativeLayout rlLogout;
    private RelativeLayout rlAboutUs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);
        backButton = (Button) findViewById(R.id.backButton);
        textView = (TextView) findViewById(R.id.titleText);
        textView.setText("设置");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 账号设置
        rlAccount = findViewById(R.id.settings_account);
        rlAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsActivity.this, "该功能暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
        // 修改密码
        rlResetPassword = findViewById(R.id.settings_resetpassword);
        rlResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsActivity.this, "该功能暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
        // 清除缓存
        rlCleanUp = findViewById(R.id.settings_cleanup);
        rlCleanUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsActivity.this, "清除缓存成功", Toast.LENGTH_SHORT).show();
            }
        });
        // 关于我们
        rlAboutUs = findViewById(R.id.settings_aboutus);
        rlAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsActivity.this, "由Guis团队打造的分类精灵APP", Toast.LENGTH_SHORT).show();
            }
        });
        // 退出登录
        rlLogout = findViewById(R.id.settings_logout);
        rlLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences(Config.Xml, Config.XmlModel);
                SharedPreferences.Editor editor = pref.edit();
                editor.remove(Config.username);
                editor.remove(Config.password);
                editor.apply();
                Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
