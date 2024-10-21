package co.com.mercadolibre.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import co.com.mercadolibre.core.navigation.NavigationAction
import co.com.mercadolibre.core.navigation.Navigator

/**
 * Maneja las acciones de navegación que se producen en el [Navigator].
 * Las acciones de navegación se producen cuando se llama a [Navigator.navigateTo] o [Navigator.navigateBack].
 */
@Composable
internal fun HandleNavigation(
  navigator: Navigator,
  navController: NavHostController,
) {
  val navigationActions by navigator.navigationActions.collectAsState(initial = null)

  LaunchedEffect(navigationActions) {
    navigationActions?.let { action ->
      when (action) {
        is NavigationAction.NavigateTo -> navController.navigate(
          route = action.destination.route + if (action.query != null) "/${action.query}" else ""
        ) {

          // Este es un ejemplo de cómo se podrían usar los navOptions para personalizar la navegación.
          // Para efectos de este challenge no es necesario esta implementación avanzada.
          /*action.navOptions?.let { navOptions ->
            navOptions.popUpToOptions?.let { popUpToOption ->
              when (popUpToOption) {
                is PopUpToDestination -> {
                  popUpTo(popUpToOption.popUpTo.route) {
                    inclusive = popUpToOption.popUpTo.inclusive
                    saveState = popUpToOption.popUpTo.saveState
                  }
                }

                is PopUpToStart -> {
                  popUpTo(navController.graph.id) {
                    inclusive = popUpToOption.inclusive
                  }
                }
              }
            }
          }*/
        }

        is NavigationAction.NavigateBack -> navController.popBackStack()
      }
    }
  }
}
