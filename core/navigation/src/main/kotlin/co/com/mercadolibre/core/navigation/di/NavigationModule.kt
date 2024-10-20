package co.com.mercadolibre.core.navigation.di

import co.com.mercadolibre.core.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

  @Provides
  @Singleton
  fun provideNavigator(): Navigator = Navigator()
}
