package co.com.mercadolibre.features.details.di

import co.com.mercadolibre.core.network.qualifiers.MeliBasicRetrofit
import co.com.mercadolibre.features.details.data.api.ProductDetailsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ProductDetailsApiModule {

  @Provides
  @Singleton
  fun provideProductDetailsApi(
    @MeliBasicRetrofit retrofit: Retrofit,
  ): ProductDetailsApi = retrofit.create(ProductDetailsApi::class.java)
}
