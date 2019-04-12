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

public class DashboardGoodsStationery extends Fragment {
    private static List<dashboardGoods> goodsWomanList;
    private ListView goodsListView;

    private CardView cardViewLeft;
    private CardView cardViewRight;

    static {
        goodsWomanList = new ArrayList<>();
        goodsWomanList.add(new dashboardGoods(R.drawable.goods1_woman,
                "文具线圈本网格本笔记本Z记事本子活页手帐本手账计划本",
                "147", "0", R.drawable.goods2_woman,
                "简约小清新大容量笔袋文具铅笔袋学习用品",
                "268", "0"));
        goodsWomanList.add(new dashboardGoods(R.drawable.goods3_woman,
                "软面抄b5笔记本文具可爱创意车线本学生日记本记事本子",
                "185", "0", R.drawable.goods4_woman,
                "日本PILOT百乐 JUICE果汁笔 百果乐彩色中性笔 0.5MM按动笔 手帐学生用文具 36色啫喱笔",
                "165", "0"));
        goodsWomanList.add(new dashboardGoods(R.drawable.goods5_woman,
                "斜插式透明磨砂桌面笔筒办公室女收纳盒笔桶学生文具用品",
                "238", "0", R.drawable.goods6_woman,
                "创意简约铅笔盒学生纯色笔袋女 韩国文具袋男女生文具盒",
                "114", "0"));
        goodsWomanList.add(new dashboardGoods(R.drawable.goods7_woman,
                "牛皮纸横线便签本小本子可撕留言记事N次贴便利贴",
                "180", "0", R.drawable.goods8_woman,
                "麻球列国游系列套尺Z学生绘图直尺四件套三角尺子文具",
                "280", "0"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dashboard_goods_woman, container, false);

        goodsListView = view.findViewById(R.id.dashboard_goods_woman_listview);
        goodsListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return goodsWomanList.size();
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
                    LayoutInflater goodsInflater = DashboardGoodsStationery.this.getLayoutInflater();
                    // 因为getView()返回的对象，adapter会自动赋给ListView
                    itemview = inflater.inflate(R.layout.dashboard_goods_list_row, null);
                } else {
                    Log.i("goodsListInfo:", "有缓存，不需要重新生成" + position);
                    itemview = convertView;
                }

                final dashboardGoods goods = goodsWomanList.get(position);
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
