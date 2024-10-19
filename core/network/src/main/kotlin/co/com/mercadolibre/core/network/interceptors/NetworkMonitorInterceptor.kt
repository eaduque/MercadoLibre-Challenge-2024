package co.com.mercadolibre.core.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.net.ConnectException

internal class NetworkMonitorInterceptor : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val isOnline = true

    if (!isOnline) throw ConnectException("No Internet Connection")
    return chain.proceed(chain.request())
  }
}
