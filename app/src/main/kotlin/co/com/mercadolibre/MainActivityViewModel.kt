package co.com.mercadolibre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

  private val _uiState = MutableStateFlow(MainActivityUIState())
  val uiState = _uiState.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = MainActivityUIState()
  )

  init {
    viewModelScope.launch {
      delay(100)
      _uiState.update { it.copy(isAnimatedIconAtEnd = true) }
      delay(800)
      _uiState.update { it.copy(isOnSplashScreen = false) }
    }
  }
}
