package com.example.ig.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ig.R;

public class IntegralRuleActivity extends FragmentActivity {
    private Button backButton;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_integral_rule);
        backButton = (Button) findViewById(R.id.backButton);
        textView = (TextView) findViewById(R.id.titleText);
        textView.setText("积分规则");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
