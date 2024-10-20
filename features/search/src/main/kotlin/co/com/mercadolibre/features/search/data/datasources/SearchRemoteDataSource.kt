package co.com.mercadolibre.features.search.data.datasources

import co.com.mercadolibre.core.common.network.Dispatcher
import co.com.mercadolibre.core.common.network.MeliDispatchers.IO
import co.com.mercadolibre.features.search.data.api.SearchApi
import co.com.mercadolibre.features.search.data.model.SearchQueryResponseApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class SearchRemoteDataSource @Inject constructor(
  private val searchApi: SearchApi,
  @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) {

  suspend fun performSearch(
    searchQuery: String,
  ): SearchQueryResponseApi = withContext(ioDispatcher) {
    searchApi.fetchSuggestions(searchQuery, 6)
  }
}
