package com.codespurt.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codespurt.broadcastreceiver.simpleBroadcastReceiver.MyBroadcastReceiver;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // views
    private Button btnCustom, btnOrdered;

    private LocalBroadcastManager localBroadcastManager;

    private BroadcastReceiver broadcastReceiver;
    private String CUSTOM_ACTION_TAG = "com.codespurt.custom.action.tag";
    private String ORDERED_ACTION_TAG = "com.codespurt.custom.action.tag.ordered";

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String BREAD_CRUMB = "Breadcrumb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize views
        btnCustom = findViewById(R.id.btn_custom_broadcast_intent);
        btnOrdered = findViewById(R.id.btn_ordered_broadcast_intent);

        localBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());

        // initialize simple broadcast receiver
        broadcastReceiver = new MyBroadcastReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // register simple broadcast receiver
        /*IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(broadcastReceiver, intentFilter);*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnCustom.setOnClickListener(this);
        btnOrdered.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_custom_broadcast_intent:
                // send custom broadcast
                Intent intent1 = new Intent();
                intent1.setAction(CUSTOM_ACTION_TAG);
                intent1.addCategory(Intent.CATEGORY_DEFAULT);
                // localBroadcastManager.sendBroadcast(intent1);
                sendBroadcast(intent1);
                break;

            case R.id.btn_ordered_broadcast_intent:
                // send ordered broadcast
                Intent intent2 = new Intent();
                intent2.setAction(ORDERED_ACTION_TAG);
                intent2.addCategory(Intent.CATEGORY_DEFAULT);
                // localBroadcastManager.sendBroadcast(intent2);
                sendOrderedBroadcast(intent2, null, new BroadcastReceiver() {

                    @Override
                    public void onReceive(Context context, Intent intent) {
                        Toast.makeText(context, context.getResources().getString(R.string.broadcast_from_main_activity), Toast.LENGTH_SHORT).show();

                        Bundle bundle = getResultExtras(true);
                        String breadcrumb = bundle.getString(BREAD_CRUMB);
                        breadcrumb = breadcrumb + " -> " + TAG;
                        Log.i(TAG, breadcrumb);
                    }
                }, null, MainActivity.RESULT_OK, null, null);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        btnCustom.setOnClickListener(null);
        btnOrdered.setOnClickListener(null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // unregister simple broadcast receiver
        /*unregisterReceiver(broadcastReceiver);*/
    }
}
