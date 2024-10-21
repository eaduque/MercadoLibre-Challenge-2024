package co.com.mercadolibre.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.mercadolibre.core.common.result.Result.Error
import co.com.mercadolibre.core.common.result.Result.Success
import co.com.mercadolibre.core.navigation.Navigator
import co.com.mercadolibre.features.details.domain.usecases.ProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductDetailsViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  private val productDetailsUseCase: ProductDetailsUseCase,
  private val navigator: Navigator,
) : ViewModel() {

  private val _uiState = MutableStateFlow(ProductDetailsUIState())
  val uiState = _uiState.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = ProductDetailsUIState()
  )

  init {
    savedStateHandle.get<String>("productId")?.let { productId ->
      viewModelScope.launch {
        val result = productDetailsUseCase(productId)
        when (result) {
          is Success -> _uiState.update { it.copy(product = result.data) }
          is Error -> _uiState.update { it.copy(error = result.error.toString()) }
        }.also {
          _uiState.update { it.copy(isLoading = false) }
        }
      }
    }
  }

  fun onBackPressed() = navigator.navigateBack()
}
