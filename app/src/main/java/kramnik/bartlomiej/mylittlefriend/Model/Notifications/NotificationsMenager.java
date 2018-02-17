package kramnik.bartlomiej.mylittlefriend.Model.Notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Agent;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Observation;
import kramnik.bartlomiej.mylittlefriend.R;
import kramnik.bartlomiej.mylittlefriend.View.ViewObservations.ShowObservationsActivity;

/**
 * Created by Mao on 15.02.2018.
 */

public class NotificationsMenager {


    NotificationManager notificationManager;

    public NotificationsMenager(Context context) {

         notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("id", "name", importance);

            channel.setDescription("description");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 50});
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * push notification about new Observation from Agent
     *
     * @param context
     * @param observation that is shown
     * @param agent       agent which send observation
     */
    public void postNewObservation(Context context, Observation observation, Agent agent) {
        Intent resultIntent = new Intent(context, ShowObservationsActivity.class);
        resultIntent.putExtra("observation", observation);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context, "id")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("New observation!")
                        .setContentText(agent.getName() + " send new Observation!");

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(1, mBuilder.build());
    }
}
