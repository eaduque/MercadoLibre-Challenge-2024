package co.com.mercadolibre.features.details.domain.usecases

import co.com.mercadolibre.core.common.result.Result
import co.com.mercadolibre.features.details.domain.model.Product
import co.com.mercadolibre.features.details.domain.repositories.ProductDetailsRepository
import javax.inject.Inject

internal class ProductDetailsUseCase @Inject constructor(
  private val productDetailsRepository: ProductDetailsRepository,
) {

  suspend operator fun invoke(productId: String): Result<Product> {
    return productDetailsRepository.fetchProductDetails(productId)
  }
}
