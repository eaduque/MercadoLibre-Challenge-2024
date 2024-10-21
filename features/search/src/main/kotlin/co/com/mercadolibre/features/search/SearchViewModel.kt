package co.com.mercadolibre.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.mercadolibre.core.common.result.Result.Error
import co.com.mercadolibre.core.common.result.Result.Success
import co.com.mercadolibre.core.navigation.NavDestination.ProductResults
import co.com.mercadolibre.core.navigation.Navigator
import co.com.mercadolibre.features.search.domain.model.SuggestionItem
import co.com.mercadolibre.features.search.domain.usecases.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
  private val searchUseCase: SearchUseCase,
  private val navigator: Navigator,
) : ViewModel() {

  private var searchJob: Job? = null
  private val _uiState = MutableStateFlow(SearchUIState())
  val uiState = _uiState.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = SearchUIState()
  )

  fun onExpandedChange(isExpanded: Boolean) {
    _uiState.update { it.copy(expanded = isExpanded) }
  }

  fun onQueryChanged(newQuery: String) {
    _uiState.update { it.copy(isLoading = true, searchQuery = newQuery) }

    searchJob?.cancel()

    searchJob = viewModelScope.launch {
      val result = searchUseCase(newQuery)
      when (result) {
        is Success -> _uiState.update { it.copy(suggestions = result.data) }
        is Error -> {}
      }.also {
        _uiState.update { it.copy(isLoading = false) }
      }
    }
  }

  fun onSearch(query: String) {
    navigator.navigateTo(destination = ProductResults, argument = query)
    _uiState.update { it.copy(searchQuery = query, expanded = false) }
  }

  fun clearQuery() {
    _uiState.update { it.copy(searchQuery = "") }
  }

  fun leadingIconClick() {
    _uiState.update { it.copy(expanded = !it.expanded) }
  }

  fun onSuggestionClick(suggestionItem: SuggestionItem) {
    onSearch(suggestionItem.suggestion)
  }
}
