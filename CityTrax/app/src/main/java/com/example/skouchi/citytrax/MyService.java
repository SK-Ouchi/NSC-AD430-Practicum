package com.example.skouchi.citytrax;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;


/**
 * Created by Skouchi on 12/5/2017.
 */

public class MyService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId)
//    {
//        Toast.makeText(this,"Service Started", Toast.LENGTH_LONG).show();
//        return START_STICKY;
//    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

    }

}
