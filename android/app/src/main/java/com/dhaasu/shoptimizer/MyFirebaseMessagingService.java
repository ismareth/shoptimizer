package com.dhaasu.shoptimizer;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import java.util.ArrayList;

/**
 * Created by ramitsuri on 9/24/16.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //sendNotification("heello", remoteMessage.getNotification().getBody());
        int mode = 0;
        String messageBody = remoteMessage.getNotification().getBody();
        String[] message = messageBody.split("-");
        if(message[0].equals("store"))
            mode = 1;
        else if (message[0].equals("bill"))
            mode = 0;
        Object ob = new Object();
        ob.shoppingList = MainActivity.items;
        ob.storeID = message[1];
        Gson gson = new Gson();
        String json = gson.toJson(ob);
        Intent serviceIntent = new Intent(getApplicationContext(), ServerIntentService.class);
        serviceIntent.putExtra(ServerIntentService.MODE, mode);
        serviceIntent.putExtra(ServerIntentService.OBJECT, json);
        startService(serviceIntent);
    }

    public class Object{
        public ArrayList<String> shoppingList;
        public String storeID;
    }

    private void sendToServer(int mode, ArrayList<Item> items, String body) {

    }

    private void sendNotification(String title, String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }

}
