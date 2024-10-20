package co.com.mercadolibre.features.results.data.datasources

import co.com.mercadolibre.core.common.network.Dispatcher
import co.com.mercadolibre.core.common.network.MeliDispatchers.IO
import co.com.mercadolibre.features.results.data.api.ProductsApi
import co.com.mercadolibre.features.results.data.model.ProductsQueryResponseApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductsRemoteDataSource @Inject constructor(
  private val productsApi: ProductsApi,
  @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) {

  suspend fun performSearch(
    searchQuery: String,
  ): ProductsQueryResponseApi = withContext(ioDispatcher) {
    productsApi.fetchProducts(searchQuery)
  }
}
