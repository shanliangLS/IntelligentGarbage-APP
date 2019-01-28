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

public class DashboardGoodsTotal extends Fragment {
    private static List<dashboardGoods> goodsTatolList;
    private ListView goodsListView;

    private CardView cardViewLeft;
    private CardView cardViewRight;

    static {
        goodsTatolList = new ArrayList<>();
        goodsTatolList.add(new dashboardGoods(R.drawable.goods1_man,
                "连帽羽绒服男中长款休闲男装冬季青年纯色白鸭绒加厚保暖外套",
                "4980","0",R.drawable.goods2_man,
                "羽绒服男士中长款保暖外套冬季加厚韩版连帽大毛领白鸭绒男装",
                "5680","0"));
        goodsTatolList.add(new dashboardGoods(R.drawable.goods3_hat,
                "kingston/金士顿 DDR3L 1600 4G 内存 笔记本电脑内存条 兼容1333",
                "2190","719",R.drawable.goods3_man,
                "新款棉衣男 棉袄中长款 棉服外套韩版潮流男士冬装羽绒服潮冬",
                "2580","0"));
        goodsTatolList.add(new dashboardGoods(R.drawable.goods1_woman,
                "booty2018秋冬欧美女装羽绒服女中长款白鸭绒保暖时尚连帽外套",
                "4980","0",R.drawable.goods2_woman,
                "女款时尚羽绒服冬季中长款2018新款女装韩版潮大貉子毛领加厚外套",
                "17260","0"));
        goodsTatolList.add(new dashboardGoods(R.drawable.goods1_eat,
                "卜珂蔓越莓曲奇饼干200g*3包整箱批发网红零食品大礼包小吃店",
                "40450","0",R.drawable.goods1_hat,
                "帽子男冬天加厚保暖针织毛线帽棉帽男士冬季韩版潮青年防寒风骑车",
                "268","0"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dashboard_goods_total, container, false);

        goodsListView = view.findViewById(R.id.dashboard_goods_total_listview);
        goodsListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return goodsTatolList.size();
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
                    LayoutInflater goodsInflater = DashboardGoodsTotal.this.getLayoutInflater();
                    // 因为getView()返回的对象，adapter会自动赋给ListView
                    itemview = inflater.inflate(R.layout.dashboard_goods_list_row,null);
                } else {
                    Log.i("goodsListInfo:", "有缓存，不需要重新生成"+position);
                    itemview = convertView;
                }

                dashboardGoods goods = goodsTatolList.get(position);
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
