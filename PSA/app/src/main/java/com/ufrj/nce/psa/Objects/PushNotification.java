package com.ufrj.nce.psa.Objects;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.ufrj.nce.psa.Activities.EmergencyReceiverView;
import com.ufrj.nce.psa.R;

/**
 * Created by fabiofilho on 4/1/15.
 */
public class PushNotification {

    private static final String TITLE_DEFAULT = "Emergência Recebida";


    public static void createNotification(Context context, String title, String message, int id_icon){

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(id_icon)
                        .setContentTitle(title)
                        .setContentText(message);


        int mNotificationId = 001;
        Intent resultIntent = new Intent(context, EmergencyReceiverView.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        stackBuilder.addParentStack(EmergencyReceiverView.class);

        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(mNotificationId, mBuilder.build());
    }


    public static void createNotification(Context context, String message){

        createNotification(context, TITLE_DEFAULT, message, R.mipmap.ic_emergency);
    }

    public static void createNotification(Context context, String title, String message){

        createNotification(context, TITLE_DEFAULT, message, R.mipmap.ic_emergency);
    }

    public static void createNotification(Context context, String message, int id_icon){

        createNotification(context, TITLE_DEFAULT, message, id_icon);
    }

}
