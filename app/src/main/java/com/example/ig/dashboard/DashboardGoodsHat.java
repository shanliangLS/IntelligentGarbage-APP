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
import android.widget.Toast;

import com.example.ig.Config;
import com.example.ig.R;
import com.example.ig.controller.dashboardGoods;
import com.example.ig.dashboard.info.GoodInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class DashboardGoodsHat extends Fragment {
    private static List<dashboardGoods> goodsHatList;
    private ListView goodsListView;

    private CardView cardViewLeft;
    private CardView cardViewRight;

    static {
        goodsHatList = new ArrayList<>();
        goodsHatList.add(new dashboardGoods(R.drawable.goods1_hat,
                "帽子男冬天加厚保暖针织毛线帽棉帽男士冬季韩版潮青年防寒风骑车",
                "680", "0", R.drawable.goods2_hat,
                "帽子女冬羊毛贝雷帽女英伦秋冬韩版日系针织画家帽南瓜帽女",
                "890", "0"));
        goodsHatList.add(new dashboardGoods(R.drawable.goods3_hat,
                "CHARLES＆KEITH信封包CK2-20160030流苏装饰单肩链条小方包",
                "4690", "0", R.drawable.goods4_hat,
                "鞋子男潮鞋春季新款男士韩版潮流百搭休闲透气跑步鞋旅游运动男鞋",
                "1380", "0"));
        goodsHatList.add(new dashboardGoods(R.drawable.goods5_hat,
                "男鞋韩版百搭潮鞋潮流冬鞋休闲运动跑步鞋子男秋冬季加绒保暖棉鞋",
                "1590", "0", R.drawable.goods6_hat,
                "梦特娇女包女士小包青年休闲时尚小方包潮流简约单肩斜跨包38311",
                "3990", "0"));
        goodsHatList.add(new dashboardGoods(R.drawable.goods7_hat,
                "简约百搭英伦男士大礼帽秋冬季羊毛呢帽子黑色绅士帽毡帽爵士帽女",
                "450", "0", R.drawable.goods8_hat,
                "毛线帽女秋冬天雷锋帽子保暖韩版甜美可爱冬季护耳毛球潮针织帽",
                "580", "0"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dashboard_goods_hat, container, false);

        goodsListView = view.findViewById(R.id.dashboard_goods_hat_listview);
        goodsListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return goodsHatList.size();
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
                    LayoutInflater goodsInflater = DashboardGoodsHat.this.getLayoutInflater();
                    // 因为getView()返回的对象，adapter会自动赋给ListView
                    itemview = inflater.inflate(R.layout.dashboard_goods_list_row, null);
                } else {
                    Log.i("goodsListInfo:", "有缓存，不需要重新生成" + position);
                    itemview = convertView;
                }

                final dashboardGoods goods = goodsHatList.get(position);
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
