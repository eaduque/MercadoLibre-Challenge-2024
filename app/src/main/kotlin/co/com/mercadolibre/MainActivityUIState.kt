package co.com.mercadolibre

/**
 * El estado de la actividad principal.
 *
 * Este patrón de diseño es útil para manejar el estado de la aplicación en una sola ubicación. Se
 * usa en todos los módulos de este proyecto.
 */
data class MainActivityUIState(
  val isOnSplashScreen: Boolean = true,
  val isAnimatedIconAtEnd: Boolean = false,
)
