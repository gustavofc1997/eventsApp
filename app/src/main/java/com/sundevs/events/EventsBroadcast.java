package com.sundevs.events;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by Latitude on 20/12/2016.
 */

public class EventsBroadcast extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.load();
    }
}
