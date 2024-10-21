package co.com.mercadolibre.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.com.mercadolibre.features.details.navigation.productDetailsScreen
import co.com.mercadolibre.features.results.navigation.productResultsScreen
import co.com.mercadolibre.ui.MeliAppState

@Composable
internal fun MeliNavHost(
  appState: MeliAppState,
  modifier: Modifier = Modifier,
) {
  //val isOffline by appState.isOffline.collectAsStateWithLifecycle()

  NavHost(
    navController = appState.navController,
    startDestination = "home_route",
    modifier = Modifier.fillMaxSize(),
  ) {
    homeScreen()
    productResultsScreen()
    productDetailsScreen()
  }
}

private fun NavGraphBuilder.homeScreen() {
  composable(route = "home_route", content = {
    Text("Soy el home!")
  })
}
