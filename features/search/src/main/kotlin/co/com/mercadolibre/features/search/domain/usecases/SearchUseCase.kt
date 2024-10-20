package co.com.mercadolibre.features.search.domain.usecases

import co.com.mercadolibre.core.common.result.Result
import co.com.mercadolibre.features.search.domain.model.SuggestionItem
import co.com.mercadolibre.features.search.domain.repositories.SearchRepository
import javax.inject.Inject

internal class SearchUseCase @Inject constructor(private val searchRepository: SearchRepository) {

  suspend operator fun invoke(searchTerm: String): Result<List<SuggestionItem>> {
    return searchRepository.searchProducts(searchTerm)
  }
}
