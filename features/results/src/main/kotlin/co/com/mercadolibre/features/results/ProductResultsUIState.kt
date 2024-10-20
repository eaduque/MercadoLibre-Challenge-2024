package co.com.mercadolibre.features.results

import co.com.mercadolibre.features.results.domain.model.ProductItem

internal data class ProductResultsUIState(
  val isLoading: Boolean = true,
  val products: List<ProductItem> = emptyList(),
)
