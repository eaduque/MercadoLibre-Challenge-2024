package co.com.mercadolibre.core.navigation

/**
 * Este archivo contiene las acciones de navegación de la aplicación.
 *
 * Nuestro propio dominio define la navegación a través de objetos de tipo sealed class.
 * Esta lógica luego será manejada por algún módulo que maneje la navegación. Ejemplo: Módulo :app.
 */
sealed class NavigationAction {

  /**
   * Navega a una ruta específica. Se puede personalizar el comportamiento de la navegación a través de [navOptions].
   */
  data class NavigateTo(
    val destination: NavDestination,
    val navOptions: NavOptions? = null,
    val query: String? = null,
  ) : NavigationAction()

  /**
   * Navega hacia atrás. Se agrega un timestamp para asegurarse que cada navegación sea única.
   */
  data class NavigateBack(val time: Long = System.currentTimeMillis()) : NavigationAction()
}
