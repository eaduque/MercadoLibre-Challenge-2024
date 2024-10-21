package co.com.mercadolibre.core.navigation

sealed class NavigationAction {

  data class NavigateTo(
    val destination: NavDestination,
    val navOptions: NavOptions? = null,
    val query: String? = null,
  ) : NavigationAction()

  data class NavigateBack(val time: Long = System.currentTimeMillis()) : NavigationAction()
}
