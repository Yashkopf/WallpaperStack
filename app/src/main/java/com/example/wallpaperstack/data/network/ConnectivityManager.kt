package com.example.wallpaperstack.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn


class ConnectivityManager(context: Context) {

    private val connectivityManager = context.getSystemService(ConnectivityManager::class.java)

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    val connectionAsStateFlow: StateFlow<Boolean>
        get() = _connectionFlow
            .stateIn(
                scope = scope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = isConnected
            )

    private val _connectionFlow = callbackFlow {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onLost(network : Network) {
                trySend(false)
            }

            override fun onCapabilitiesChanged(network : Network, networkCapabilities : NetworkCapabilities) {
                if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                    trySend(true)
                }
            }
        }
        subscribe(networkCallback)
        awaitClose {
            unsubscribe(networkCallback)
        }
    }

    private val isConnected: Boolean
        get() {
            val activeNetwork = connectivityManager.activeNetwork
            return if (activeNetwork == null) {
                false
            } else {
                val netCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
                (netCapabilities != null
                        && netCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                        && netCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                        && netCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
            }
        }

    private fun subscribe(networkCallback: ConnectivityManager.NetworkCallback) {
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    private fun unsubscribe(networkCallback: ConnectivityManager.NetworkCallback) {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}