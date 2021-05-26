package com.mr.quickbuild.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;


import com.mr.quickbuild.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * @Description: java类作用描述
 * @Author: kirali
 * @CreateDate: 2019/9/2 0002 12:15
 * @UpdateUser: kirali
 * @UpdateDate: 2019/9/2 0002 12:15
 * @UpdateRemark: 更新说明
 */
public class NotificationHelper {
    private static final String CHANNEL_ID = "my_channel_01";   //通道渠道id
    public static final String CHANEL_NAME = "baolong"; //通道渠道名称
    static NotificationManager notificationManager;
    public static void show(Context context, String content) {
        notificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANEL_NAME, NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(context)
                    .setChannelId(CHANNEL_ID)
                    .setContentTitle("")
                    .setContentText(content)
                    .setSmallIcon(R.mipmap.ic_launcher).build();
        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                    .setContentTitle("")
                    .setContentText(content)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setOngoing(true)
                    .setChannelId(CHANNEL_ID);//无效
            notification = notificationBuilder.build();
        }
        notificationManager.notify(111123, notification);
    }
}
