package com.dei.ceo;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class FireBaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    SharedPreferences sp;
    SharedPreferences.Editor edit;

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d("newToken" , s);
        sendRegistrationToServer(s);

      /* sp = getSharedPreferences("token" , Activity.MODE_PRIVATE);
       edit = sp.edit();
        edit.putString("token", s);
        edit.commit();
        */
    }

    public void sendRegistrationToServer(String s) {
        new HttpTask().execute(s);

    }

    public class HttpTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... params) {
            String token = params[0];
            OkHttpClient client = new OkHttpClient();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault());
            long now = System.currentTimeMillis();
            Date date = new Date(now);
            String strDate = dateFormat.format(date);
            RequestBody body = new FormBody.Builder()
                    .add("regid", token)
                    .add("regdate", strDate)
                    .build();

            //request
            Request request = new Request.Builder()
                    .url("http://dei.hivecom.co.kr/dei/reg.php")
                    .post(body)
                    .build();
            try {
                client.newCall(request).execute();

            } catch (IOException e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String result) {
        }
    }

    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            String title = remoteMessage.getData().get("title");
            String body = remoteMessage.getData().get("body");
            sendNotification(title,body);
            //Log.d(TAG, title + " - " + body);

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param title FCM message body received.
     * @param body
     */
    private void sendNotification(String title, String body) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);

        String channelId = "Notice";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setStyle( new NotificationCompat.BigTextStyle()
                                .bigText(body)
                                .setSummaryText("동의대학교 산업대학원 알림")
                        )
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelName = "Notice";
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("공지사항");
            channel.enableLights(true); channel.setLightColor(Color.GREEN);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 2000});
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(channel);
        }
        Intent i = new Intent(this, showAlert.class);
        Bundle b = new Bundle();
        b.putString("title", title);
        b.putString("body", body);
        i.putExtras(b);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(i);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}