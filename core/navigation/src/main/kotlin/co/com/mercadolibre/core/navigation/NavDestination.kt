package co.com.mercadolibre.core.navigation

/**
 * Este archivo contiene las rutas de navegación de la aplicación.
 *
 * Permite crear una herarquía de navegación a través de objetos de tipo sealed class.
 */
sealed class NavDestination(val route: String) {

  data object Home : NavDestination("home_route")
  data object ProductResults : NavDestination("product_results_route")
  data object ProductDetails : NavDestination("product_details_route")
}
