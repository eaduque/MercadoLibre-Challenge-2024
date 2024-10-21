package co.com.mercadolibre.features.results.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import co.com.mercadolibre.core.common.domain.ErrorHandler
import co.com.mercadolibre.core.common.network.Dispatcher
import co.com.mercadolibre.core.common.network.MeliDispatchers.IO
import co.com.mercadolibre.features.results.data.api.ProductsApi
import co.com.mercadolibre.features.results.data.datasources.ProductsRemoteDataSource
import co.com.mercadolibre.features.results.data.toDomain
import co.com.mercadolibre.features.results.domain.model.ProductItem
import co.com.mercadolibre.features.results.domain.repositories.ProductsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class ProductsRepositoryImpl @Inject constructor(
  private val productsApi: ProductsApi,
  private val errorHandler: ErrorHandler,
  @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : ProductsRepository {

  override suspend fun searchProducts(searchQuery: String): Flow<PagingData<ProductItem>> {
    return Pager(
      config = PagingConfig(pageSize = 50, enablePlaceholders = false),
      pagingSourceFactory = { ProductsRemoteDataSource(productsApi, searchQuery) }
    ).flow
      .flowOn(ioDispatcher)
      .map { pagingData -> pagingData.map { it.toDomain() } }
      .catch { emit(PagingData.empty()) }
  }
}
