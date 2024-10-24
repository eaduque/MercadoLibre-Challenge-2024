package co.com.mercadolibre.core.common.di

import co.com.mercadolibre.core.common.network.Dispatcher
import co.com.mercadolibre.core.common.network.MeliDispatchers.Default
import co.com.mercadolibre.core.common.network.MeliDispatchers.IO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

  @Provides
  @Dispatcher(IO)
  fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

  @Provides
  @Dispatcher(Default)
  fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
