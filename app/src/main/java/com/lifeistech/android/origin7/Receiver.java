package com.lifeistech.android.origin7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by takashi on 2015/07/03.
 */
public class Receiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "���Ԃł���I", Toast.LENGTH_SHORT).show();

    }
}
