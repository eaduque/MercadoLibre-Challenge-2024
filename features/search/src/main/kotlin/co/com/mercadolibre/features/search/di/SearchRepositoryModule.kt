package co.com.mercadolibre.features.search.di

import co.com.mercadolibre.features.search.data.repositories.SearchRepositoryImpl
import co.com.mercadolibre.features.search.domain.repositories.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SearchRepositoryModule {

  @Binds
  abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
}
