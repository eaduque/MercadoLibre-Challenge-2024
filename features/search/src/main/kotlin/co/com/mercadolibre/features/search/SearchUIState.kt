package co.com.mercadolibre.features.search

import co.com.mercadolibre.features.search.domain.model.SuggestionItem

internal data class SearchUIState(
  val isLoading: Boolean = false,
  val searchQuery: String = "",
  val expanded: Boolean = false,
  val suggestions: List<SuggestionItem> = emptyList(),
)
