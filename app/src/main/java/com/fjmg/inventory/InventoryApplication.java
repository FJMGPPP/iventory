package com.fjmg.inventory;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.util.Arrays;

public class InventoryApplication extends Application
{
    public static String IDCHANEL = "MainChannel";
    @Override
    public void onCreate()
    {
        super.onCreate();
        createNotificationChannel();
    }
    //Da fallo por debajo de la api 26
    private void createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {

            //Definir la importancia
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            //Definir el nombre del canal;
            String nameChannel = getString(R.string.name_channel);
            //Crear el canal
            NotificationChannel notificationChannel = new NotificationChannel(IDCHANEL,
                                                                              nameChannel,
                                                                              importance);
            long[] ArrayVibraciones = new long[]{200,200,10,2000,30};
            //Opcionales:
            notificationChannel.setVibrationPattern(ArrayVibraciones);
            //Añadir este canal a notificationManager
            getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);
        }
    }
}
