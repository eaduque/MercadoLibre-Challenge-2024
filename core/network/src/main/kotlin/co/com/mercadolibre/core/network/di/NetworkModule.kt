package co.com.mercadolibre.core.network.di

import co.com.mercadolibre.core.network.BuildConfig
import co.com.mercadolibre.core.network.interceptors.NetworkMonitorInterceptor
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

  @Provides
  @Singleton
  fun providesBaseURL(): String = BuildConfig.MELI_BASE_URL

  @Provides
  @Singleton
  fun providesNetworkJson(): Json = Json { ignoreUnknownKeys = true }

  @Provides
  @Singleton
  fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
    .addInterceptor(
      HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
          setLevel(HttpLoggingInterceptor.Level.BODY)
        }
      }
    )
    .addInterceptor(NetworkMonitorInterceptor())
    .build()

  @Provides
  @Singleton
  fun provideRetrofit(baseUrl: String, json: Json, okHttpCallFactory: Call.Factory): Retrofit {
    return Retrofit.Builder()
      .baseUrl(baseUrl)
      .callFactory(okHttpCallFactory)
      .addConverterFactory(json.asConverterFactory("application/json; charset=utf-8".toMediaType()))
      .build()
  }
}