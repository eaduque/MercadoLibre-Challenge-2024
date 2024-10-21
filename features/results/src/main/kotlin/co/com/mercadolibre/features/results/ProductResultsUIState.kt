package co.com.mercadolibre.features.results

import androidx.paging.PagingData
import co.com.mercadolibre.features.results.domain.model.ProductItem

internal data class ProductResultsUIState(
  val isLoading: Boolean = true,
  val products: PagingData<ProductItem> = PagingData.empty(),
)
