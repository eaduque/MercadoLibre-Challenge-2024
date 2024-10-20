package co.com.mercadolibre.features.search.data

import co.com.mercadolibre.features.search.data.model.SearchQueryItem
import co.com.mercadolibre.features.search.domain.model.SuggestionItem

internal fun SearchQueryItem.toDomain() = SuggestionItem(
  suggestion = suggestion,
  matchStart = matchStart,
  matchEnd = matchEnd,
  isVerifiedStore = isVerifiedStore,
)
