package co.com.mercadolibre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Este ViewModel se usa para manejar el estado de la actividad principal.
 * Sirve para casos más complejos donde, por ejemplo, se necesite sacar al usuario de alguna
 * pantalla específica (soliticando permisos, etc).
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

  private val _uiState = MutableStateFlow(MainActivityUIState())
  val uiState = _uiState.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = MainActivityUIState()
  )

  init {
    viewModelScope.launch {
      // Simular un delay para mostrar el splash screen
      delay(100)
      _uiState.update { it.copy(isAnimatedIconAtEnd = true) }
      // Simular un delay para ocultar el splash screen mientras finaliza la animación
      delay(800)
      _uiState.update { it.copy(isOnSplashScreen = false) }
    }
  }
}
