package co.com.mercadolibre.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.com.mercadolibre.R
import co.com.mercadolibre.core.navigation.NavDestination.Home
import co.com.mercadolibre.features.details.navigation.productDetailsScreen
import co.com.mercadolibre.features.results.navigation.productResultsScreen
import co.com.mercadolibre.ui.MeliAppState
import co.com.mercadolibre.ui.components.InternetStatusToast

/**
 * NavHost de la aplicación que permite navegar entre pantallas.
 * Adicionalmente, se muestra un Toast en caso de que el usuario no cuente con conexión a internet.
 */
@Composable
internal fun BoxScope.MeliNavHost(
  appState: MeliAppState,
  modifier: Modifier = Modifier,
) {
  val isOffline by appState.isOffline.collectAsStateWithLifecycle()

  NavHost(
    navController = appState.navController,
    startDestination = Home.route,
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

/**
 * Una ruta a manera de bienvenida a la app. Bastante sencilla ya que el enfoque de diseño se
 * centra en la funcionalidad requerida en el challenge.
 */
private fun NavGraphBuilder.homeScreen() {
  composable(route = Home.route, content = {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = stringResource(R.string.welcome_to_mercadolibre),
          textAlign = TextAlign.Center,
          style = MaterialTheme.typography.titleLarge,
        )
        Image(painterResource(R.drawable.splash_screen_meli_icon), contentDescription = null)
      }
    }
  })
}
