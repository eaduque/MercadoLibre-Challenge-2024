package co.com.mercadolibre.core.common.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val meliDispatchers: MeliDispatchers)

enum class MeliDispatchers {
  Default,
  IO,
}
