package co.com.mercadolibre.core.common.di

import co.com.mercadolibre.core.common.ErrorHandlerImpl
import co.com.mercadolibre.core.common.domain.ErrorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ErrorModule {

  @Binds
  abstract fun bindsErrorHandler(errorHandlerImpl: ErrorHandlerImpl): ErrorHandler
}
