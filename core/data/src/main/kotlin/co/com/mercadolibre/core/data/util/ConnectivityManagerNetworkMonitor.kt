package co.com.mercadolibre.core.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest.Builder
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.core.content.getSystemService
import co.com.mercadolibre.core.common.network.Dispatcher
import co.com.mercadolibre.core.common.network.MeliDispatchers.IO
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class ConnectivityManagerNetworkMonitor @Inject constructor(
  @ApplicationContext private val context: Context,
  @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : NetworkMonitor {

  override val isOnline: Flow<Boolean> = callbackFlow {
    val connectivityManager = context.getSystemService<ConnectivityManager>()

    if (connectivityManager == null) {
      channel.trySend(false)
      channel.close()
      return@callbackFlow
    }

    val request = Builder()
      .addCapability(NET_CAPABILITY_INTERNET)
      .build()
    val callback = object : NetworkCallback() {

      private val networks = mutableSetOf<Network>()

      override fun onAvailable(network: Network) {
        networks += network
        channel.trySend(true)
      }

      override fun onLost(network: Network) {
        networks -= network
        channel.trySend(networks.isNotEmpty())
      }
    }

    connectivityManager.registerNetworkCallback(request, callback)
    channel.trySend(connectivityManager.isCurrentlyConnected())
    awaitClose {
      connectivityManager.unregisterNetworkCallback(callback)
    }
  }
    .flowOn(ioDispatcher)
    .conflate()

  @Suppress("DEPRECATION")
  private fun ConnectivityManager.isCurrentlyConnected() = when {
    VERSION.SDK_INT >= VERSION_CODES.M ->
      activeNetwork
        ?.let(::getNetworkCapabilities)
        ?.hasCapability(NET_CAPABILITY_INTERNET)

    else -> activeNetworkInfo?.isConnected
  } ?: false
}