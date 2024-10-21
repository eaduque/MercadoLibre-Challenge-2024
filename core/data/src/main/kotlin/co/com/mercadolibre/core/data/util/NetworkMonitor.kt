package co.com.mercadolibre.core.data.util

import kotlinx.coroutines.flow.Flow

/** Utilidad para reportar el estado de la red. */
interface NetworkMonitor {
  val isOnline: Flow<Boolean>
}
