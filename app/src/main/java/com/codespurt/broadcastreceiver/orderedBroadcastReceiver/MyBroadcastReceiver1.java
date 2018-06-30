package com.codespurt.broadcastreceiver.orderedBroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.codespurt.broadcastreceiver.R;

/**
 * Created by Code Spurt on 30-06-18.
 */

public class MyBroadcastReceiver1 extends BroadcastReceiver {

    private static final String TAG = MyBroadcastReceiver1.class.getSimpleName();
    private static String BREAD_CRUMB = "Breadcrumb";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, context.getResources().getString(R.string.broadcast_message_1), Toast.LENGTH_SHORT).show();

        Bundle bundle = getResultExtras(true);
        String trail = bundle.getString(BREAD_CRUMB);
        trail = (trail == null ? "Start -> " + TAG : trail + " -> " + TAG);
        bundle.putString(BREAD_CRUMB, trail);
        Log.i(TAG, context.getResources().getString(R.string.broadcast_message_1) + " " + trail);
    }
}
