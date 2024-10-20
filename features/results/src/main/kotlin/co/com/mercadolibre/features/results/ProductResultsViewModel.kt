package co.com.mercadolibre.features.results

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.mercadolibre.core.common.result.Result.Error
import co.com.mercadolibre.core.common.result.Result.Success
import co.com.mercadolibre.core.navigation.Navigator
import co.com.mercadolibre.features.results.domain.usecases.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductResultsViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle,
  private val productsUseCase: ProductsUseCase,
  private val navigator: Navigator,
) : ViewModel() {

  private val _uiState = MutableStateFlow(ProductResultsUIState())
  val uiState = _uiState.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = ProductResultsUIState()
  )

  init {
    savedStateHandle.get<String>("query")?.let { query ->
      viewModelScope.launch {
        delay(5000)
        val result = productsUseCase(query)
        when (result) {
          is Success -> _uiState.update { it.copy(products = result.data) }
          is Error -> {}
        }.also {
          _uiState.update { it.copy(isLoading = false) }
        }
      }
    }
  }
}
