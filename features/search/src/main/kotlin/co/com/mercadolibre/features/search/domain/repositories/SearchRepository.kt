package co.com.mercadolibre.features.search.domain.repositories

import co.com.mercadolibre.core.common.result.Result
import co.com.mercadolibre.features.search.domain.model.SuggestionItem

internal interface SearchRepository {

  suspend fun searchProducts(searchQuery: String): Result<List<SuggestionItem>>
}
