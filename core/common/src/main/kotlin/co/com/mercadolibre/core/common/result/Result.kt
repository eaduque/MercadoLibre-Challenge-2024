package co.com.mercadolibre.core.common.result

import co.com.mercadolibre.core.common.domain.ErrorEntity

/**
 * Esta clase permite encapsular el resultado de una operación en dos posibles resultados: éxito o error.
 *
 * Se podrían soportar más resultados como: cargando, sin resultados, etc.
 */
sealed interface Result<out T> {

  data class Error(val error: ErrorEntity) : Result<Nothing>

  data class Success<T>(val data: T) : Result<T>
}
