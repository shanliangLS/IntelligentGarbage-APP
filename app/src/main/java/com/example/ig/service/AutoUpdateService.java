package com.example.ig.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Toast;


public class AutoUpdateService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateContent();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int aTime = 1000 * 10; // 10000 ms
        long triggerAtTime = SystemClock.elapsedRealtime() + aTime;
        Intent i = new Intent(this, AutoUpdateService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }


    private void updateContent() {
        // 打开蓝牙
        openBluetooth();

    }

    // 打开蓝牙
    private void openBluetooth() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter == null) {
            Toast.makeText(getApplicationContext(), "该设备不支持蓝牙", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!adapter.enable()) {
            adapter.enable();
            Toast.makeText(getApplicationContext(), "APP已开启蓝牙", Toast.LENGTH_SHORT).show();
        }
    }

    private void addScore(int val) {
        SharedPreferences pref = getSharedPreferences("user.xml", MODE_PRIVATE);
        String user_score = pref.getString("user_score", null);
        if (user_score != null) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("user_score", Integer.parseInt(user_score) + val + "");
            editor.apply();
        }
    }

}




