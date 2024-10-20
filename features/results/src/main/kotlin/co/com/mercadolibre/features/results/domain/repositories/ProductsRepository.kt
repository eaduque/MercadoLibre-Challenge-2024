package co.com.mercadolibre.features.results.domain.repositories

import co.com.mercadolibre.core.common.result.Result
import co.com.mercadolibre.features.results.domain.model.ProductItem

interface ProductsRepository {

  suspend fun searchProducts(searchQuery: String): Result<List<ProductItem>>
}
