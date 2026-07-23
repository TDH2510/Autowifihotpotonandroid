package com.example.autohotspot

import android.content.Context
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Looper

object HotspotControl {
    fun turnOnHotspot(context: Context) {
        try {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val method = connectivityManager.javaClass.getDeclaredMethod(
                "startTethering", Int::class.javaPrimitiveType, Boolean::class.javaPrimitiveType,
                Class.forName("android.net.ConnectivityManager\$OnStartTetheringCallback"), Handler::class.java
            )
            val callbackClass = Class.forName("android.net.ConnectivityManager\$OnStartTetheringCallback")
            val callback = java.lang.reflect.Proxy.newProxyInstance(callbackClass.classLoader, arrayOf(callbackClass)) { _, _, _ -> null }
            method.invoke(connectivityManager, 0, false, callback, Handler(Looper.getMainLooper()))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}