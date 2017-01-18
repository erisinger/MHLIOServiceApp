package com.example.erikrisinger.experiment;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by erikrisinger on 1/17/17.
 */

public class MHLAgentService extends Service {

    public static final String TO_AGENT_MESSAGE = "to-agent";
    public static final String FROM_AGENT_MESSAGE = "from-agent";

    LocalBroadcastManager localBroadcastManager;

    @Override
    public void onCreate() {
//        Log.d("agent", "starting up...");
//        localBroadcastManager = LocalBroadcastManager.getInstance(this);
//        localBroadcastManager.registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Log.d("agent", intent.getAction());
//            }
//        }, new IntentFilter(TO_AGENT_MESSAGE));
//
//        localBroadcastManager.sendBroadcast(new Intent(FROM_AGENT_MESSAGE));
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("agent", "onstartCommand");

        new Thread(new Runnable() {
            @Override
            public void run() {

                Log.d("agent", "starting up...");
                localBroadcastManager = LocalBroadcastManager.getInstance(MHLAgentService.this);
                localBroadcastManager.registerReceiver(new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        Log.d("agent", intent.getAction());
//                        localBroadcastManager.sendBroadcast(new Intent(MHLAgentService.FROM_AGENT_MESSAGE));
                    }
                }, new IntentFilter(MHLAgentService.TO_AGENT_MESSAGE));

//                localBroadcastManager.sendBroadcast(new Intent(MHLAgentService.FROM_AGENT_MESSAGE));

                for (int i = 0; i < 10; i++) {
//                    Log.d("agent", "round " + i);
                    localBroadcastManager.sendBroadcast(new Intent(MHLAgentService.FROM_AGENT_MESSAGE));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return START_STICKY;
    }

    public void onDestroy() {
        Log.d("agent", "onDestroy");
    }


    public IBinder onBind(Intent intent) {
        return null;
    }
}