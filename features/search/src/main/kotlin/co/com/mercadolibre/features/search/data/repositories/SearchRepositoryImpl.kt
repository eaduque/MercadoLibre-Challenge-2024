package co.com.mercadolibre.features.search.data.repositories

import co.com.mercadolibre.core.common.domain.ErrorHandler
import co.com.mercadolibre.core.common.network.Dispatcher
import co.com.mercadolibre.core.common.network.MeliDispatchers.IO
import co.com.mercadolibre.core.common.result.Result
import co.com.mercadolibre.core.common.result.Result.Error
import co.com.mercadolibre.core.common.result.Result.Success
import co.com.mercadolibre.features.search.data.datasources.SearchRemoteDataSource
import co.com.mercadolibre.features.search.data.toDomain
import co.com.mercadolibre.features.search.domain.model.SuggestionItem
import co.com.mercadolibre.features.search.domain.repositories.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
  private val remoteDataSource: SearchRemoteDataSource,
  private val errorHandler: ErrorHandler,
  @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : SearchRepository {

  override suspend fun searchProducts(
    searchQuery: String,
  ): Result<List<SuggestionItem>> = withContext(ioDispatcher) {
    try {
      Success(remoteDataSource.performSearch(searchQuery).items.map { it.toDomain() })
    } catch (err: Throwable) {
      Error(errorHandler.getError(err))
    }
  }
}
