package com.example.ig.dashboard;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ig.Config;
import com.example.ig.R;
import com.example.ig.controller.dashboardGoods;
import com.example.ig.dashboard.info.GoodInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class DashboardGoodsDailyUse extends Fragment {
    private static List<dashboardGoods> goodsEatList;
    private ListView goodsListView;

    private CardView cardViewLeft;
    private CardView cardViewRight;

    static {
        goodsEatList = new ArrayList<>();
        goodsEatList.add(new dashboardGoods(R.drawable.goods1_eat,
                "创意家居生活用品用具居家宿舍卧室收纳神器",
                "178", "0", R.drawable.goods2_eat,
                "家庭实用日用品宿舍神器手动开关小夜灯",
                "290", "0"));
        goodsEatList.add(new dashboardGoods(R.drawable.goods3_eat,
                "家用厨房卫生间用具可旋转地面刮水器",
                "168", "0", R.drawable.goods4_eat,
                "创意家居生活用品实用柠檬加湿器",
                "420", "0"));
        goodsEatList.add(new dashboardGoods(R.drawable.goods5_eat,
                "家居洗漱日用品清新简约加厚圆形漱口杯",
                "90", "0", R.drawable.goods6_eat,
                "创意实用家居用品收纳神器宿舍寝室挂钩",
                "188", "0"));
        goodsEatList.add(new dashboardGoods(R.drawable.goods7_eat,
                "创意日用品浴室家居吸盘肥皂盒",
                "131", "0", R.drawable.goods8_eat,
                "创意家居新家必备实乐趣味小i台灯",
                "560", "0"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dashboard_goods_eat, container, false);

        goodsListView = view.findViewById(R.id.dashboard_goods_eat_listview);
        goodsListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return goodsEatList.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View itemview = null;

                // 如果缓存为空，生成新的布局作为1个item
                if (convertView == null) {
                    Log.i("goodsListInfo:", "没有缓存，重新生成" + position);
                    LayoutInflater goodsInflater = DashboardGoodsDailyUse.this.getLayoutInflater();
                    // 因为getView()返回的对象，adapter会自动赋给ListView
                    itemview = inflater.inflate(R.layout.dashboard_goods_list_row, null);
                } else {
                    Log.i("goodsListInfo:", "有缓存，不需要重新生成" + position);
                    itemview = convertView;
                }

                final dashboardGoods goods = goodsEatList.get(position);
                // 图片
                ImageView imageViewLeft = (ImageView) itemview.findViewById(R.id.dashboard_goods_img_left);
                imageViewLeft.setImageResource(goods.getImagePathLeft());
                ImageView imageViewRight = (ImageView) itemview.findViewById(R.id.dashboard_goods_img_right);
                imageViewRight.setImageResource(goods.getImagePathRight());
                // 简介
                TextView introductionTextViewdLeft = (TextView) itemview.findViewById(R.id.dashboard_goods_introduction_left);
                introductionTextViewdLeft.setText(goods.getGoodsIntroductionLeft());
                TextView introductionTextViewdRight = (TextView) itemview.findViewById(R.id.dashboard_goods_introduction_right);
                introductionTextViewdRight.setText(goods.getGoodsIntroductionRight());
                // 价格
                TextView priceTextViewdLeft = (TextView) itemview.findViewById(R.id.dashboard_goods_price_left);
                priceTextViewdLeft.setText(goods.getPriceLeft());
                TextView priceTextViewdRight = (TextView) itemview.findViewById(R.id.dashboard_goods_price_right);
                priceTextViewdRight.setText(goods.getPriceRight());
                // 记录
                TextView recordTextViewLeft = (TextView) itemview.findViewById(R.id.dashboard_goods_record_left);
                recordTextViewLeft.setText(goods.getRecordLeft());
                TextView recordTextViewRight = (TextView) itemview.findViewById(R.id.dashboard_goods_record_right);
                recordTextViewRight.setText(goods.getRecordRight());

                // 点击事件
                cardViewLeft = (CardView) itemview.findViewById(R.id.dashboard_goods_left);
                cardViewRight = (CardView) itemview.findViewById(R.id.dashboard_goods_right);
                cardViewLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showOnClick(goods.getImagePathLeft(), goods.getPriceLeft(), goods.getGoodsIntroductionLeft());
                    }
                });
                cardViewRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showOnClick(goods.getImagePathRight(), goods.getPriceRight(), goods.getGoodsIntroductionRight());
                    }
                });

                return itemview;
            }
        });

        return view;
    }

    public void showOnClick(int image, String price, String introduction) {
        Intent i = new Intent(getActivity(), GoodInfoActivity.class);
        i.putExtra(Config.goods_info, introduction);
        i.putExtra(Config.goods_image, image);
        i.putExtra(Config.goods_price, price);
        startActivity(i);
    }
}
