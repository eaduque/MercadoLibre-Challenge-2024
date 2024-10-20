package co.com.mercadolibre.features.search.di

import co.com.mercadolibre.core.network.qualifiers.MeliProdRetrofit
import co.com.mercadolibre.features.search.data.api.SearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SearchApiModule {

  @Provides
  @Singleton
  fun provideSearchApi(
    @MeliProdRetrofit retrofit: Retrofit,
  ): SearchApi = retrofit.create(SearchApi::class.java)
}
