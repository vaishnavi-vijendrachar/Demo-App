package com.vaishnavi.telstratest

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkConnection {
        //return true if network connection is available
        fun checkNetworkAvailability(context: Context?) : Boolean{
            val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            return activeNetwork?.isConnected == true
        }
}