package co.com.mercadolibre.features.results.di

import co.com.mercadolibre.features.results.data.repositories.ProductsRepositoryImpl
import co.com.mercadolibre.features.results.domain.repositories.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProductsRepositoryModule {

  @Binds
  abstract fun bindProductsRepository(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository
}
