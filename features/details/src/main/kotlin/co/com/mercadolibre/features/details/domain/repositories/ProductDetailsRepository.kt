package co.com.mercadolibre.features.details.domain.repositories

import co.com.mercadolibre.core.common.result.Result
import co.com.mercadolibre.features.details.domain.model.Product

internal interface ProductDetailsRepository {

  suspend fun fetchProductDetails(productId: String): Result<Product>
}
