package co.com.mercadolibre.features.details.di

import co.com.mercadolibre.features.details.data.repositories.ProductDetailsRepositoryImpl
import co.com.mercadolibre.features.details.domain.repositories.ProductDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProductDetailsRepositoryModule {

  @Binds
  abstract fun bindProductDetailsRepository(
    productDetailsRepositoryImpl: ProductDetailsRepositoryImpl,
  ): ProductDetailsRepository
}
