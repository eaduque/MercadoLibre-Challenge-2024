package co.com.mercadolibre.features.results.domain.usecases

import androidx.paging.PagingData
import co.com.mercadolibre.features.results.domain.model.ProductItem
import co.com.mercadolibre.features.results.domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ProductsUseCase @Inject constructor(
  private val productsRepository: ProductsRepository,
) {

  suspend operator fun invoke(query: String): Flow<PagingData<ProductItem>> {
    return productsRepository.searchProducts(query)
  }
}
