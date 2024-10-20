package co.com.mercadolibre.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.com.mercadolibre.MainActivityUIState

@Composable
internal fun rememberMeliAppState(
  uiState: MainActivityUIState,
  navController: NavHostController = rememberNavController(),
): MeliAppState {
  return remember(uiState, navController) {
    MeliAppState(uiState = uiState, navController = navController)
  }
}

@Stable
internal class MeliAppState(
  val uiState: MainActivityUIState,
  val navController: NavHostController,
)
