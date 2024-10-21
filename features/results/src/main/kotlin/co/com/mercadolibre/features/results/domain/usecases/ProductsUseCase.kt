package co.com.mercadolibre.features.results.domain.usecases

import co.com.mercadolibre.core.common.result.Result
import co.com.mercadolibre.features.results.domain.model.ProductItem
import co.com.mercadolibre.features.results.domain.repositories.ProductsRepository
import javax.inject.Inject

internal class ProductsUseCase @Inject constructor(private val productsRepository: ProductsRepository) {

  suspend operator fun invoke(query: String): Result<List<ProductItem>> {
    return productsRepository.searchProducts(query)
  }
}
