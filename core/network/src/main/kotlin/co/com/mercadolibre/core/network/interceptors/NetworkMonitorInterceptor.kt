package co.com.mercadolibre.core.network.interceptors

import co.com.mercadolibre.core.data.util.NetworkMonitor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.net.ConnectException

internal class NetworkMonitorInterceptor(
  private val networkMonitor: NetworkMonitor
) : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val isOnline = runBlocking {
      networkMonitor.isOnline.first()
    }

    if (!isOnline) throw ConnectException("No Internet Connection")
    return chain.proceed(chain.request())
  }
}
