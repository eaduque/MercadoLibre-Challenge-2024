package co.com.mercadolibre.features.results.data.repositories

import co.com.mercadolibre.core.common.domain.ErrorHandler
import co.com.mercadolibre.core.common.network.Dispatcher
import co.com.mercadolibre.core.common.network.MeliDispatchers.IO
import co.com.mercadolibre.core.common.result.Result
import co.com.mercadolibre.core.common.result.Result.Error
import co.com.mercadolibre.core.common.result.Result.Success
import co.com.mercadolibre.features.results.data.datasources.ProductsRemoteDataSource
import co.com.mercadolibre.features.results.data.toDomain
import co.com.mercadolibre.features.results.domain.model.ProductItem
import co.com.mercadolibre.features.results.domain.repositories.ProductsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ProductsRepositoryImpl @Inject constructor(
  private val remoteDataSource: ProductsRemoteDataSource,
  private val errorHandler: ErrorHandler,
  @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : ProductsRepository {

  override suspend fun searchProducts(
    searchQuery: String,
  ): Result<List<ProductItem>> = withContext(ioDispatcher) {
    try {
      Success(remoteDataSource.performSearch(searchQuery).results.map { it.toDomain() })
    } catch (err: Throwable) {
      Error(errorHandler.getError(err))
    }
  }
}
