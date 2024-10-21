package co.com.mercadolibre.core.navigation

/**
 * Estas clases no se utilizan en este challenge pero se dejan aquí para visualización de los revisores
 * a modo de ejemplo de cómo se podría estructurar la navegación de una forma más escalable.
 */
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
