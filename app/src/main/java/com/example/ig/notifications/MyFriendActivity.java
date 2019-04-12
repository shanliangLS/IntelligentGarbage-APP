package com.example.ig.notifications;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.ig.controller.NotificationMessage;
import com.example.ig.controller.dashboardGoods;
import com.example.ig.tabs.TabDashboardFragment;

import java.util.ArrayList;
import java.util.List;

public class MyFriendActivity extends Fragment {
    public static List<NotificationMessage> notificationMessagesList;
    private ListView messagesListView;

    static {
        notificationMessagesList = new ArrayList<>();
        notificationMessagesList.add(new NotificationMessage(R.drawable.user_logo2,"分类小精灵",
                "12-12  21:01","【打招呼】亲爱的用户您好，我是分类小精灵，有问题可以向我咨询哦。"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.notifications_friend, container, false);

        messagesListView = (ListView) view.findViewById(R.id.notifications_friend_message_list);
        messagesListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return notificationMessagesList.size();
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
                    Log.i("messagesListInfo:", "没有缓存，重新生成"+position);
                    LayoutInflater messagesInflater = MyFriendActivity.this.getLayoutInflater();
                    // 因为getView()返回的对象，adapter会自动赋给ListView
                    itemview = inflater.inflate(R.layout.notifications_friend_message,null);
                } else {
                    Log.i("goodsListInfo:", "有缓存，不需要重新生成"+position);
                    itemview = convertView;
                }

                NotificationMessage message = notificationMessagesList.get(position);
                // 图片
                ImageView imageView = (ImageView)itemview.findViewById(R.id.notifications_friend_logo);
                imageView.setImageResource(message.getLogoPath());
                // 用户名
                TextView nameTextView = (TextView)itemview.findViewById(R.id.notifications_friend_name);
                nameTextView.setText(message.getUserName());
                // 时间
                TextView timeTextView = (TextView)itemview.findViewById(R.id.notifications_friend_time);
                timeTextView.setText(message.getSentTime());
                // 内容
                TextView contentTextView = (TextView)itemview.findViewById(R.id.notifications_friend_content);
                contentTextView.setText(message.getMsgContent());
                // 点击事件
                itemview.setOnClickListener(new View.OnClickListener() {
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
