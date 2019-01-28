package com.example.ig.dashboard;

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
import android.widget.Toast;

import com.example.ig.R;
import com.example.ig.controller.dashboardGoods;

import java.util.ArrayList;
import java.util.List;

public class DashboardGoodsEat extends Fragment {
    private static List<dashboardGoods> goodsEatList;
    private ListView goodsListView;

    private CardView cardViewLeft;
    private CardView cardViewRight;

    static {
        goodsEatList = new ArrayList<>();
        goodsEatList.add(new dashboardGoods(R.drawable.goods1_eat,
                "卜珂蔓越莓曲奇饼干200g*3包整箱批发网红零食品大礼包小吃店",
                "40450","0",R.drawable.goods2_eat,
                "【三只松鼠零食大礼包】网红圣诞节一箱组合超大批发混装抖音美食",
                "680","0"));
        goodsEatList.add(new dashboardGoods(R.drawable.goods3_eat,
                "【单品包邮】良品铺子鸭肉大礼包490g鸭脖鸭舌特产卤味零食小吃",
                "549","0",R.drawable.goods4_eat,
                "豪士乳酸菌酸奶小口袋面包整箱680g*2小吃蛋糕早餐吐司休闲零食品",
                "499","0"));
        goodsEatList.add(new dashboardGoods(R.drawable.goods5_eat,
                "百草味零食大礼包女生空投箱组合超大混装一整箱网红休闲食品小吃",
                "1290","0",R.drawable.goods6_eat,
                "老街口 焦糖/山核桃味瓜子500g*4袋葵花籽坚果炒货零食品特产批发",
                "399","0"));
        goodsEatList.add(new dashboardGoods(R.drawable.goods7_eat,
                "味芝元香辣鱼排26g*30包湖南特产零食洞庭湖鱼尾鱼块即食麻辣小吃",
                "389","0",R.drawable.goods8_eat,
                "甜甜乐星球杯桶装大杯1000g巧克力杯夹心饼干批发儿童零食大礼包",
                "399","0"));
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
                    Log.i("goodsListInfo:", "没有缓存，重新生成"+position);
                    LayoutInflater goodsInflater = DashboardGoodsEat.this.getLayoutInflater();
                    // 因为getView()返回的对象，adapter会自动赋给ListView
                    itemview = inflater.inflate(R.layout.dashboard_goods_list_row,null);
                } else {
                    Log.i("goodsListInfo:", "有缓存，不需要重新生成"+position);
                    itemview = convertView;
                }

                dashboardGoods goods = goodsEatList.get(position);
                // 图片
                ImageView imageViewLeft = (ImageView)itemview.findViewById(R.id.dashboard_goods_img_left);
                imageViewLeft.setImageResource(goods.getImagePathLeft());
                ImageView imageViewRight = (ImageView)itemview.findViewById(R.id.dashboard_goods_img_right);
                imageViewRight.setImageResource(goods.getImagePathRight());
                // 简介
                TextView introductionTextViewdLeft = (TextView)itemview.findViewById(R.id.dashboard_goods_introduction_left);
                introductionTextViewdLeft.setText(goods.getGoodsIntroductionLeft());
                TextView introductionTextViewdRight = (TextView)itemview.findViewById(R.id.dashboard_goods_introduction_right);
                introductionTextViewdRight.setText(goods.getGoodsIntroductionRight());
                // 价格
                TextView priceTextViewdLeft = (TextView)itemview.findViewById(R.id.dashboard_goods_price_left);
                priceTextViewdLeft.setText(goods.getPriceLeft());
                TextView priceTextViewdRight = (TextView)itemview.findViewById(R.id.dashboard_goods_price_right);
                priceTextViewdRight.setText(goods.getPriceRight());
                // 记录
                TextView recordTextViewLeft = (TextView)itemview.findViewById(R.id.dashboard_goods_record_left);
                recordTextViewLeft.setText(goods.getRecordLeft());
                TextView recordTextViewRight = (TextView)itemview.findViewById(R.id.dashboard_goods_record_right);
                recordTextViewRight.setText(goods.getRecordRight());

                // 点击事件
                cardViewLeft = (CardView)itemview.findViewById(R.id.dashboard_goods_left);
                cardViewRight = (CardView)itemview.findViewById(R.id.dashboard_goods_right);
                cardViewLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "该功能暂未开放", Toast.LENGTH_SHORT).show();
                    }
                });
                cardViewRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "该功能暂未开放", Toast.LENGTH_SHORT).show();
                    }
                });

                return itemview;
            }
        });

        return view;
    }
}
