package co.com.mercadolibre.core.navigation

sealed class NavDestination(val route: String) {

  data object Home : NavDestination("home_route")
  data object ProductResults : NavDestination("product_results_route")
  data object ProductDetails : NavDestination("product_details_route")
}
