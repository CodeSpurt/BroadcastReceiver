package com.codespurt.broadcastreceiver.simpleBroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.codespurt.broadcastreceiver.R;

/**
 * Created by Code Spurt on 30-06-18.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, context.getResources().getString(R.string.simple_broadcast_message), Toast.LENGTH_SHORT).show();
    }
}
