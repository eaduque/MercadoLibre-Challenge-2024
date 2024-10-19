package co.com.mercadolibre.core.common.domain

sealed class ErrorEntity {

  sealed class ApiError : ErrorEntity() {
    data object Network : ApiError()
    data object Unauthorized : ApiError()
    data object NotFound : ApiError()
    data object AccessDenied : ApiError()
    data object Conflict : ApiError()
    data object ServiceUnavailable : ApiError()
  }

  data object Unknown : ErrorEntity()
}
