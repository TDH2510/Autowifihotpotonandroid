package com.example.autohotspot

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED || intent.action == "android.intent.action.LOCKED_BOOT_COMPLETED") {
            Handler(Looper.getMainLooper()).postDelayed({
                HotspotControl.turnOnHotspot(context)
            }, 5000)
        }
    }
}