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

public class DashboardGoodsClothing extends Fragment {
    private static List<dashboardGoods> goodsManList;
    private ListView goodsListView;

    private CardView cardViewLeft;
    private CardView cardViewRight;

    static {
        goodsManList = new ArrayList<>();


        goodsManList.add(new dashboardGoods(R.drawable.goods1_man,
                "新款棉衣男 棉袄中长款 棉服外套韩版潮流男士冬装羽绒服潮冬",
                "3680", "0", R.drawable.goods2_man,
                "男装 无缝羽绒连帽外套 409330 优衣库UNIQLO",
                "6790", "0"));

        goodsManList.add(new dashboardGoods(R.drawable.goods3_man,
                "2018冬季新款韩版潮流连帽面包棉衣服工装学生短款男装帅气外套袄",
                "3880", "0", R.drawable.goods4_man,
                "春秋季男士V领长袖T恤秋衣韩版修身卫衣秋装男装加绒打底衫上衣服",
                "4990", "0"));

        goodsManList.add(new dashboardGoods(R.drawable.goods5_man,
                "booty2018秋冬欧美女装羽绒服女中长款白鸭绒保暖时尚连帽外套",
                "4980", "0", R.drawable.goods6_man,
                "JANE毛呢大衣女黑色中长款",
                "4260", "0"));

        goodsManList.add(new dashboardGoods(R.drawable.goods7_man,
                "女装 仿羊羔绒摇粒绒廓形大衣(长袖) 409447 优衣库UNIQLO",
                "4490", "0", R.drawable.goods8_man,
                "沧海蝴蝶 森系蝴蝶刺绣 大摆复古吊带连衣裙",
                "4600", "0"));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dashboard_goods_man, container, false);

        goodsListView = view.findViewById(R.id.dashboard_goods_man_listview);
        goodsListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return goodsManList.size();
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
                    LayoutInflater goodsInflater = DashboardGoodsClothing.this.getLayoutInflater();
                    // 因为getView()返回的对象，adapter会自动赋给ListView
                    itemview = inflater.inflate(R.layout.dashboard_goods_list_row, null);
                } else {
                    Log.i("goodsListInfo:", "有缓存，不需要重新生成" + position);
                    itemview = convertView;
                }

                final dashboardGoods goods = goodsManList.get(position);
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
