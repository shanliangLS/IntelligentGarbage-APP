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

public class DashboardGoodsHat extends Fragment {
    private static List<dashboardGoods> goodsHatList;
    private ListView goodsListView;

    private CardView cardViewLeft;
    private CardView cardViewRight;

    static {
        goodsHatList = new ArrayList<>();
        goodsHatList.add(new dashboardGoods(R.drawable.goods1_hat,
                "帽子男冬天加厚保暖针织毛线帽棉帽男士冬季韩版潮青年防寒风骑车",
                "268","0",R.drawable.goods2_hat,
                "电竞游戏鼠标有线机械光电笔记本外接usb背光家用台式机电脑无声静音金属加重lol竞技吃鸡办公家用网吧外设cf",
                "490","0"));
        goodsHatList.add(new dashboardGoods(R.drawable.goods3_hat,
                "kingston/金士顿 DDR3L 1600 4G 内存 笔记本电脑内存条 兼容1333",
                "2190","0",R.drawable.goods4_hat,
                "键盘台式套装机械lol英雄联盟台式电脑青轴有线鼠标笔记本USB电竞",
                "797","0"));
        goodsHatList.add(new dashboardGoods(R.drawable.goods5_hat,
                "男鞋韩版百搭潮鞋潮流冬鞋休闲运动跑步鞋子男秋冬季加绒保暖棉鞋",
                "1590","0",R.drawable.goods6_hat,
                "梦特娇女包女士小包青年休闲时尚小方包潮流简约单肩斜跨包38311",
                "3990","0"));
        goodsHatList.add(new dashboardGoods(R.drawable.goods7_hat,
                "触屏手套男士冬季防滑开车女学生针织毛线情侣加绒保暖户外骑行全",
                "450","0",R.drawable.goods8_hat,
                "塔菲克 耳机原装正品入耳式通用男女生6s适用iPhone苹果vivo华为小米oppo手机安卓有线控x9x20重低音炮耳塞",
                "268","0"));
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
                    Log.i("goodsListInfo:", "没有缓存，重新生成"+position);
                    LayoutInflater goodsInflater = DashboardGoodsHat.this.getLayoutInflater();
                    // 因为getView()返回的对象，adapter会自动赋给ListView
                    itemview = inflater.inflate(R.layout.dashboard_goods_list_row,null);
                } else {
                    Log.i("goodsListInfo:", "有缓存，不需要重新生成"+position);
                    itemview = convertView;
                }

                dashboardGoods goods = goodsHatList.get(position);
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
