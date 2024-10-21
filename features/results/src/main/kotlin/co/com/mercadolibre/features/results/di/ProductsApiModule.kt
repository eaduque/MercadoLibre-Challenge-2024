package co.com.mercadolibre.features.results.di

import co.com.mercadolibre.core.network.qualifiers.MeliBasicRetrofit
import co.com.mercadolibre.features.results.data.api.ProductsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ProductsApiModule {

  @Provides
  @Singleton
  fun provideProductsApi(
    @MeliBasicRetrofit retrofit: Retrofit,
  ): ProductsApi = retrofit.create(ProductsApi::class.java)
}
