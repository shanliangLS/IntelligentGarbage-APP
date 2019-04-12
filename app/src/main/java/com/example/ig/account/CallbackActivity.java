package com.example.ig.account;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ig.R;

public class CallbackActivity extends FragmentActivity {
    private Button backButton;
    private TextView textView;
    private EditText feedbackText;
    private Button feedbackSubmitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_callback);
        feedbackText = findViewById(R.id.feedback_content);
        feedbackSubmitButton = findViewById(R.id.feedback_submit);
        textView = findViewById(R.id.titleText);
        backButton = findViewById(R.id.backButton);
        textView.setText("反馈留言");
        // 返回 按钮 点击
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 提交反馈 按钮 点击
        feedbackSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = feedbackText.getText().toString();
                if(text.equals("")){
                    AlertDialog.Builder builder  = new AlertDialog.Builder(CallbackActivity.this);
                    builder.setTitle("" ) ;
                    builder.setMessage("输入内容为空" ) ;
                    builder.setPositiveButton("是" ,  null );
                    builder.show();
                }else {
                    AlertDialog.Builder builder  = new AlertDialog.Builder(CallbackActivity.this);
                    builder.setTitle("" ) ;
                    builder.setMessage("提交成功" ) ;
                    builder.setPositiveButton("是" ,  null );
                    builder.show();
                }
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
