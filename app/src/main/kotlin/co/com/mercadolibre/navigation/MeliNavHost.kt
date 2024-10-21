package co.com.mercadolibre.navigation

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.com.mercadolibre.features.details.navigation.productDetailsScreen
import co.com.mercadolibre.features.results.navigation.productResultsScreen
import co.com.mercadolibre.ui.MeliAppState
import co.com.mercadolibre.ui.components.InternetStatusToast

@Composable
internal fun BoxScope.MeliNavHost(
  appState: MeliAppState,
  modifier: Modifier = Modifier,
) {
  val isOffline by appState.isOffline.collectAsStateWithLifecycle()

  NavHost(
    navController = appState.navController,
    startDestination = "home_route",
    modifier = modifier.fillMaxSize(),
  ) {
    homeScreen()
    productResultsScreen()
    productDetailsScreen()
  }
  InternetStatusToast(
    isOffline,
    Modifier
      .align(Alignment.BottomCenter)
      .padding(
        bottom = WindowInsets.navigationBars
          .asPaddingValues()
          .calculateBottomPadding()
      )
  )
}

private fun NavGraphBuilder.homeScreen() {
  composable(route = "home_route", content = {
    Text("Soy el home!")
  })
}
