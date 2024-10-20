package co.com.mercadolibre.core.navigation

class PopUpTo(
  val route: String,
  val inclusive: Boolean = false,
  val saveState: Boolean = false,
)

sealed class PopUpToOptions {
  data class PopUpToDestination(val popUpTo: PopUpTo) : PopUpToOptions()
  data class PopUpToStart(val inclusive: Boolean = false) : PopUpToOptions()
}

class NavOptions(
  val popUpToOptions: PopUpToOptions?,
)
