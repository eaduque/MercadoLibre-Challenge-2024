package co.com.mercadolibre.core.common.domain

interface ErrorHandler {

  fun getError(throwable: Throwable): ErrorEntity
}
