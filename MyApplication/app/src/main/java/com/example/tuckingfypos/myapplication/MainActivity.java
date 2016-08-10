package com.example.tuckingfypos.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {
    public static final int CONNECTED = 1;
    public static final int NOT_CONNECTED = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            NetworkIsOnline();
        } else {
            NetworkIsOffline();
        }
    }

    private void NetworkIsOnline() {
        NotificationCompat.BigPictureStyle aBigPictureStyle = new NotificationCompat.BigPictureStyle();
        aBigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.pepe_online)).build();
        Intent intent = new Intent(this, SecondaryActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 123, intent, 0);
        //Build it with a builder
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        //Set the icon
        mBuilder.setSmallIcon(R.drawable.service);
        //sets the title and content of the notification
        mBuilder.setContentTitle("Such internet");
        mBuilder.setContentText("so online. wow");
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setStyle(aBigPictureStyle);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(CONNECTED, mBuilder.build());
    }

    private void NetworkIsOffline() {
        NotificationCompat.BigPictureStyle anotherBigPictureStyle = new NotificationCompat.BigPictureStyle();
        anotherBigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.pepe_offline)).build();
        Intent intent = new Intent(this, SecondaryActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 123, intent, 0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.service);
        mBuilder.setContentTitle("oh no");
        mBuilder.setContentText("such sadness. not wow");
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setStyle(anotherBigPictureStyle);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOT_CONNECTED, mBuilder.build());
    }

}

