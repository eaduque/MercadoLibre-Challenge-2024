package co.com.mercadolibre.core.common.result

import co.com.mercadolibre.core.common.domain.ErrorEntity

sealed interface Result<out T> {

  data object Loading : Result<Nothing>

  data class Error(val error: ErrorEntity) : Result<Nothing>

  data class Success<T>(val data: T) : Result<T>
}
