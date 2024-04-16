package com.rajat.assignment.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.rajat.assignment.viewmodels.MainViewModel


object Utils {

    private var viewModelCallback: MainViewModel? = null

    fun setViewModelCallback(callback: MainViewModel) {
        viewModelCallback = callback

    }

    fun registerNetworkChangeReceiver(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: android.net.Network) {
                super.onAvailable(network)
                viewModelCallback?.handleNetworkChange(true, context)
            }

            override fun onLost(network: android.net.Network) {
                super.onLost(network)
                viewModelCallback?.handleNetworkChange(false, context)

            }
        })

    }

    fun unregisterNetworkChangeReceiver(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(ConnectivityManager.NetworkCallback())
    }

    fun isInternetConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}