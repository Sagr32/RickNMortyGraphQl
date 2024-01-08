package com.sagr.rickandmortygraphql.data.network

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkMonitor @Inject constructor(private val context: Context) {
    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }
}