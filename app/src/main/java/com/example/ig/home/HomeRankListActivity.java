package com.example.ig.home;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ig.Config;
import com.example.ig.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeRankListActivity extends FragmentActivity {
    public static List<String[]> rankList;
    public static List<Integer> logoList;
    private ListView rankListView;
    private TextView textView;
    private Button backButton;
    private TextView title;
    private TextView userNameText;

    static {
        rankList = new ArrayList<>();
        rankList.add(new String[]{"努力努力再努力", "12800"});
        rankList.add(new String[]{"小豌豆", "11820"});
        rankList.add(new String[]{"leftYesterday", "11700"});
        rankList.add(new String[]{"启林思雨", "11680"});
        rankList.add(new String[]{"雨花雪", "11500"});
        rankList.add(new String[]{"穆继阳", "11362"});
        rankList.add(new String[]{"Jennie", "11150"});
        rankList.add(new String[]{"木古一秋", "11000"});
        rankList.add(new String[]{"孤单寂寞冷", "9980"});
        rankList.add(new String[]{"TensorFlow", "9900"});
        logoList = new ArrayList<>();
        logoList.add(R.drawable.rank_logo1);
        logoList.add(R.drawable.rank_logo2);
        logoList.add(R.drawable.rank_logo3);
        logoList.add(R.drawable.rank_logo4);
        logoList.add(R.drawable.rank_logo5);
        logoList.add(R.drawable.rank_logo6);
        logoList.add(R.drawable.rank_logo7);
        logoList.add(R.drawable.rank_logo8);
        logoList.add(R.drawable.rank_logo9);
        logoList.add(R.drawable.rank_logo10);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_rank);
        //
        textView = findViewById(R.id.home_rank_yourself_score);
        SharedPreferences pref = getSharedPreferences("user.xml", MODE_PRIVATE);
        String userName = pref.getString(Config.username, "");
        String user_score = pref.getString("user_score", null);
        if (null != user_score) {
            textView.setText(user_score);
        }
        userNameText = findViewById(R.id.home_rank_yourself_name);
        userNameText.setText(userName);
        //
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //
        title = findViewById(R.id.titleText);
        title.setText("积分排行");
        //
        rankListView = (ListView) findViewById(R.id.home_rank_list);
        rankListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return rankList.size();
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
                    Log.i("rankListInfo:", "没有缓存，重新生成" + position);
                    LayoutInflater messagesInflater = HomeRankListActivity.this.getLayoutInflater();
                    // 因为getView()返回的对象，adapter会自动赋给ListView
                    itemview = getLayoutInflater().inflate(R.layout.home_rank_others, null);
                } else {
                    Log.i("rankListInfo:", "有缓存，不需要重新生成" + position);
                    itemview = convertView;
                }

                String[] othersInfo = rankList.get(position);
                // 用户头像
//                ImageView imageView = (ImageView)itemview.findViewById(R.id.home_rank_others_logo);
//                imageView.setImageResource(message.getLogoPath());
                // 用户名
                TextView nameTextView = (TextView) itemview.findViewById(R.id.home_rank_others_name);
                nameTextView.setText(othersInfo[0]);
                // 排名
                TextView timeTextView = (TextView) itemview.findViewById(R.id.home_rank_others_rank);
                timeTextView.setText("第" + (position + 1) + "名");
                // 积分
                TextView contentTextView = (TextView) itemview.findViewById(R.id.home_rank_others_score);
                contentTextView.setText(othersInfo[1]);
                // 头像
                CircleImageView circleImageView = itemview.findViewById(R.id.home_rank_others_logo);
                circleImageView.setImageResource(logoList.get(position));
                // 点击事件
//                itemview.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getApplicationContext(), "查看其他用户功能暂未开放", Toast.LENGTH_SHORT).show();
//                    }
//                });

                return itemview;
            }
        });

    }

}
