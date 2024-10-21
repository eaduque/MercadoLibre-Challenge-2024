package co.com.mercadolibre.features.results.data.datasources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import co.com.mercadolibre.features.results.data.api.ProductsApi
import co.com.mercadolibre.features.results.data.model.ProductItemApi

internal class ProductsRemoteDataSource(
  private val productsApi: ProductsApi,
  private val searchQuery: String,
) : PagingSource<Int, ProductItemApi>() {


  override fun getRefreshKey(state: PagingState<Int, ProductItemApi>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    }
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductItemApi> =
    try {
      val offset = params.key ?: 0
      val response = productsApi.fetchProducts(searchQuery, offset)
      LoadResult.Page(
        data = response.results,
        prevKey = if (offset == 0) null else offset - response.paging.limit,
        nextKey = if (response.results.isEmpty()) null else offset + response.paging.limit
      )
    } catch (e: Exception) {
      LoadResult.Error(e)
    }
}
