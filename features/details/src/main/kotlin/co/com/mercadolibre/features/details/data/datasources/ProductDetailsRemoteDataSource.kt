package co.com.mercadolibre.features.details.data.datasources

import co.com.mercadolibre.core.common.network.Dispatcher
import co.com.mercadolibre.core.common.network.MeliDispatchers.IO
import co.com.mercadolibre.features.details.data.api.ProductDetailsApi
import co.com.mercadolibre.features.details.data.model.ProductApi
import co.com.mercadolibre.features.details.data.model.ProductDescriptionApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ProductDetailsRemoteDataSource @Inject constructor(
  private val productDetailsApi: ProductDetailsApi,
  @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) {

  suspend fun performSearch(
    productId: String,
  ): ProductApi = withContext(ioDispatcher) {
    productDetailsApi.fetchProductDetails(productId)
  }

  suspend fun performSearchDescription(
    productId: String,
  ): ProductDescriptionApi = withContext(ioDispatcher) {
    productDetailsApi.fetchProductDescription(productId)
  }
}
