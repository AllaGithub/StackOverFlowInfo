package com.moqod.stackoverflowinfo.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import javax.inject.Inject

class NetworkStatus @Inject constructor(private val context: Context) {

    fun hasNetworkConnectivity(): Boolean {
        try {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        } catch (e: Exception) {
            Log.e("Network Status ", "Error " + e.message)
        }

        return true
    }
}
