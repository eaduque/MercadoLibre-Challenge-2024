package co.com.mercadolibre.core.common.domain

/**
 * Esta interfaz se encarga de transformar los errores de la aplicación en [ErrorEntity] que son errores
 * que manejamos a nivel de dominio de la aplicación.
 */
interface ErrorHandler {

  /**
   * Transforma el error de la aplicación en un [ErrorEntity].
   */
  fun getError(throwable: Throwable): ErrorEntity
}
