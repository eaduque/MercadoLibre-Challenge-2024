package co.com.mercadolibre.core.network.di

import co.com.mercadolibre.core.data.util.NetworkMonitor
import co.com.mercadolibre.core.network.BuildConfig
import co.com.mercadolibre.core.network.interceptors.NetworkMonitorInterceptor
import co.com.mercadolibre.core.network.qualifiers.MeliBaseURL
import co.com.mercadolibre.core.network.qualifiers.MeliBasicRetrofit
import co.com.mercadolibre.core.network.qualifiers.MeliProdBaseURL
import co.com.mercadolibre.core.network.qualifiers.MeliProdRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

  @MeliBaseURL
  @Provides
  @Singleton
  fun providesBaseURL(): String = BuildConfig.MELI_BASE_URL

  @MeliProdBaseURL
  @Provides
  @Singleton
  fun providesProdBaseURL(): String = BuildConfig.MELI_PROD_BASE_URL

  @Provides
  @Singleton
  fun providesNetworkJson(): Json = Json { ignoreUnknownKeys = true }

  @Provides
  @Singleton
  fun okHttpCallFactory(
    networkMonitor: NetworkMonitor
  ): Call.Factory = OkHttpClient.Builder()
    .addInterceptor(
      HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
          setLevel(HttpLoggingInterceptor.Level.BODY)
        }
      }
    )
    .addInterceptor(NetworkMonitorInterceptor(networkMonitor))
    .build()

  @MeliBasicRetrofit
  @Provides
  @Singleton
  fun provideBaseRetrofit(
    @MeliBaseURL baseUrl: String,
    json: Json,
    okHttpCallFactory: Call.Factory,
  ): Retrofit {
    return Retrofit.Builder()
      .baseUrl(baseUrl)
      .callFactory(okHttpCallFactory)
      .addConverterFactory(json.asConverterFactory("application/json; charset=utf-8".toMediaType()))
      .build()
  }

  @MeliProdRetrofit
  @Provides
  @Singleton
  fun provideProdRetrofit(
    @MeliProdBaseURL baseUrl: String,
    json: Json,
    okHttpCallFactory: Call.Factory,
  ): Retrofit {
    return Retrofit.Builder()
      .baseUrl(baseUrl)
      .callFactory(okHttpCallFactory)
      .addConverterFactory(json.asConverterFactory("application/json; charset=utf-8".toMediaType()))
      .build()
  }
}
