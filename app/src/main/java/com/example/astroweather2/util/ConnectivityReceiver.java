package com.example.astroweather2.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.example.astroweather2.util.ConnectivityUtil.isConnected;

public class ConnectivityReceiver extends BroadcastReceiver {
    public ConnectivityReceiverListener connectivityReceiverListener = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (connectivityReceiverListener != null) {
            connectivityReceiverListener.onNetworkConnectionChanged(isConnected(context));
        }
    }

}
