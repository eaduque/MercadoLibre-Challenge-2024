package co.com.mercadolibre.core.common.domain

/**
 * Este archivo contiene las entidades de error. Se utilizan para manejar los errores de la aplicación.
 * Acá se pueden agregar nuevos tipos de errores los cuales serán manejados por el [ErrorHandler].
 */
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
