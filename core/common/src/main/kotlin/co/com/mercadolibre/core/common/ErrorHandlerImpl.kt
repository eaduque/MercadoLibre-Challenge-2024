package co.com.mercadolibre.core.common

import co.com.mercadolibre.core.common.domain.ErrorEntity
import co.com.mercadolibre.core.common.domain.ErrorEntity.ApiError
import co.com.mercadolibre.core.common.domain.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection.HTTP_CONFLICT
import java.net.HttpURLConnection.HTTP_FORBIDDEN
import java.net.HttpURLConnection.HTTP_NOT_FOUND
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED
import java.net.HttpURLConnection.HTTP_UNAVAILABLE
import javax.inject.Inject

/**
 * Esta clase se encarga de transformar los errores de la aplicación en [ErrorEntity] que son errores
 * que nuestro dominio de la aplicación conoce y sabe cómo manejarlos.
 */
internal class ErrorHandlerImpl @Inject constructor() : ErrorHandler {

  override fun getError(throwable: Throwable): ErrorEntity {
    return when (throwable) {
      is ConnectException -> ApiError.Network
      is IOException -> ApiError.NotFound
      is HttpException -> when (throwable.code()) {
        HTTP_UNAUTHORIZED -> ApiError.Unauthorized
        HTTP_NOT_FOUND -> ApiError.NotFound
        HTTP_FORBIDDEN -> ApiError.AccessDenied
        HTTP_CONFLICT -> ApiError.Conflict
        HTTP_UNAVAILABLE -> ApiError.ServiceUnavailable
        // Agregar más casos según sea necesario....
        else -> ErrorEntity.Unknown
      }

      else -> ErrorEntity.Unknown
    }
  }
}
