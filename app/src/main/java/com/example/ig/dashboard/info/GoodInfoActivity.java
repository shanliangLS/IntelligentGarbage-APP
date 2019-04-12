package com.example.ig.dashboard.info;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ig.Config;
import com.example.ig.R;

public class GoodInfoActivity extends FragmentActivity {

    private ImageView imageView;
    private TextView titleText;
    private Button backButton;
    private Button buyButton;
    private TextView priceText;
    private TextView infoText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_info);
        Intent i = getIntent();
        int image = i.getIntExtra(Config.goods_image, R.drawable.goods3_eat);
        String price = i.getStringExtra(Config.goods_price);
        String info = i.getStringExtra(Config.goods_info);
        // 标题栏
        titleText = findViewById(R.id.titleText);
        titleText.setText("积分兑换");
        // 返回按钮
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 兑换按钮
        buyButton = findViewById(R.id.now_buy);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "兑换功能暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
        // 图片
        imageView = findViewById(R.id.shop_icons_xiangqing);
        imageView.setImageResource(image);
        // 积分
        priceText = findViewById(R.id.price_xiangqing);
        priceText.setText(price + "积分");
        // 详情
        infoText = findViewById(R.id.cuxiao_icon);
        infoText.setText(info);
    }
}
