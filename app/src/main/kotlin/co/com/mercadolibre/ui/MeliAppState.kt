package co.com.mercadolibre.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.com.mercadolibre.MainActivityUIState
import co.com.mercadolibre.core.data.util.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
internal fun rememberMeliAppState(
  networkMonitor: NetworkMonitor,
  uiState: MainActivityUIState,
  navController: NavHostController = rememberNavController(),
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
): MeliAppState {
  return remember(coroutineScope, networkMonitor, uiState, navController) {
    MeliAppState(
      coroutineScope = coroutineScope,
      networkMonitor = networkMonitor,
      uiState = uiState,
      navController = navController,
    )
  }
}

@Stable
internal class MeliAppState(
  coroutineScope: CoroutineScope,
  networkMonitor: NetworkMonitor,
  val uiState: MainActivityUIState,
  val navController: NavHostController,
) {
  val isOffline = networkMonitor.isOnline
    .map(Boolean::not)
    .stateIn(
      scope = coroutineScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = false,
    )
}
