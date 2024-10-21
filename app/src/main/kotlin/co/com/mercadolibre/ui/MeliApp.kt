package co.com.mercadolibre.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import co.com.mercadolibre.R
import co.com.mercadolibre.core.designsystem.component.AnimatedVectorDrawable
import co.com.mercadolibre.features.search.MeliSearchBar
import co.com.mercadolibre.navigation.MeliNavHost

/**
 * El punto de entrada de la aplicación.
 *
 * Se provee un [Scaffold] con un [MeliSearchBar] en la parte superior que sólo se muestra si no se está
 * en el modo splash screen.
 */
@Composable
internal fun MeliApp(
  appState: MeliAppState,
  modifier: Modifier = Modifier,
) {
  Scaffold(
    modifier = modifier.fillMaxSize(),
    topBar = { if (!appState.uiState.isOnSplashScreen) MeliSearchBar() },
    containerColor = Color.Transparent,
    contentColor = MaterialTheme.colorScheme.onBackground,
    contentWindowInsets = WindowInsets(0, 0, 0, 0),
  ) { innerPadding ->
    MainScreen(appState = appState, modifier = Modifier.padding(innerPadding))
  }
}

/**
 * Es la pantalla principal de la aplicación que contiene el [MeliNavHost].
 */
@Composable
internal fun MainScreen(appState: MeliAppState, modifier: Modifier = Modifier) {
  val uiState = appState.uiState

  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    if (!uiState.isOnSplashScreen) {
      MeliNavHost(appState = appState)
    }
    AnimatedVisibility(visible = uiState.isOnSplashScreen, exit = fadeOut()) {
      SplashScreen(uiState.isAnimatedIconAtEnd)
    }
  }
}

/**
 * Es la pantalla de splash screen de la aplicación. Simula un splash screen real con una animación.
 */
@Composable
fun SplashScreen(isAnimatedIconAtEnd: Boolean, modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .fillMaxSize()
      .background(colorResource(R.color.meli_yellow_color)),
    contentAlignment = Alignment.Center
  ) {
    AnimatedVectorDrawable(isAnimatedIconAtEnd, R.drawable.splash_screen_meli_icon_anim)
  }
}
