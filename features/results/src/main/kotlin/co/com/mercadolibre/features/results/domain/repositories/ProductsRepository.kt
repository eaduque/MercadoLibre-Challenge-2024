package co.com.mercadolibre.features.results.domain.repositories

import androidx.paging.PagingData
import co.com.mercadolibre.features.results.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow

internal interface ProductsRepository {

  suspend fun searchProducts(searchQuery: String): Flow<PagingData<ProductItem>>
}
