package co.com.mercadolibre.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import co.com.mercadolibre.MainActivityUIState

@Composable
internal fun rememberMeliAppState(
  uiState: MainActivityUIState,
): MeliAppState {
  return remember(
    uiState
  ) {
    MeliAppState(uiState = uiState)
  }
}

@Stable
internal class MeliAppState(
  val uiState: MainActivityUIState,
)