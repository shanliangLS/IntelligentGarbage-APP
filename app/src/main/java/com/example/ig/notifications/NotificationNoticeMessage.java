package com.example.ig.notifications;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ig.R;

public class NotificationNoticeMessage extends FragmentActivity {
    private RelativeLayout noticeMessage;

    private Button backButton;
    private TextView textView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_notice_message);
        backButton = (Button) findViewById(R.id.backButton);
        textView = (TextView) findViewById(R.id.titleText);
        noticeMessage = findViewById(R.id.notifications_system_notice_row);

        textView.setText("通知");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 点击事件
        noticeMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NotificationNoticeMessage.this, "该功能暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
