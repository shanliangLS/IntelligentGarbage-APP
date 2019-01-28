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

public class DashboardGoodsWoman extends Fragment {
    private static List<dashboardGoods> goodsWomanList;
    private ListView goodsListView;

    private CardView cardViewLeft;
    private CardView cardViewRight;

    static {
        goodsWomanList = new ArrayList<>();
        goodsWomanList.add(new dashboardGoods(R.drawable.goods1_woman,
                "booty2018秋冬欧美女装羽绒服女中长款白鸭绒保暖时尚连帽外套",
                "4980","0",R.drawable.goods2_woman,
                "女款时尚羽绒服冬季中长款2018新款女装韩版潮大貉子毛领加厚外套",
                "17260","0"));
        goodsWomanList.add(new dashboardGoods(R.drawable.goods3_woman,
                "女装 仿羊羔绒摇粒绒廓形大衣(长袖) 409447 优衣库UNIQLO",
                "2490","0",R.drawable.goods4_woman,
                "羽绒服女中长款2018冬季新款韩版时尚女装潮大毛领长款过膝派克服",
                "10600","0"));
        goodsWomanList.add(new dashboardGoods(R.drawable.goods5_woman,
                "羽绒棉服女装2018冬季新款修身棉衣中长款冬装棉衣服棉袄外套女冬",
                "2980","0",R.drawable.goods6_woman,
                "亦朵2018冬装新款加厚过膝羽绒服女中长款白鹅绒保暖羽绒服女装",
                "11990","0"));
        goodsWomanList.add(new dashboardGoods(R.drawable.goods7_woman,
                "棉服女装2018冬季新款时尚韩版收腰中长款羽绒棉衣加厚棉袄外套潮",
                "2980","0",R.drawable.goods8_woman,
                "欧洲站长款毛呢外套女冬2018新款气质女装过膝黑色羊毛呢子大衣女",
                "6990","0"));
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
                    Log.i("goodsListInfo:", "没有缓存，重新生成"+position);
                    LayoutInflater goodsInflater = DashboardGoodsWoman.this.getLayoutInflater();
                    // 因为getView()返回的对象，adapter会自动赋给ListView
                    itemview = inflater.inflate(R.layout.dashboard_goods_list_row,null);
                } else {
                    Log.i("goodsListInfo:", "有缓存，不需要重新生成"+position);
                    itemview = convertView;
                }

                dashboardGoods goods = goodsWomanList.get(position);
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
