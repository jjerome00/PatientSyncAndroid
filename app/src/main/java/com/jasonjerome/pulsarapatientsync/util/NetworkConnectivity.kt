package com.jasonjerome.pulsarapatientsync.util

import android.content.Context
import android.net.ConnectivityManager
import org.koin.standalone.KoinComponent

class NetworkConnectivity(private val context: Context) : KoinComponent {

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}