package co.com.mercadolibre.features.results.data.datasources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import co.com.mercadolibre.features.results.data.api.ProductsApi
import co.com.mercadolibre.features.results.data.model.ProductItemApi

/**
 * Para obtener los datos de la api estoy usando Paggin.
 */
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
      // Inicialmente el offset será 0. Valor por defecto de la api.
      val offset = params.key ?: 0
      // Hago el llamado a la api con el offset y el searchQuery.
      val response = productsApi.fetchProducts(searchQuery, offset)
      // Intento devolver el resultado actualizando el offset y la lista de resultados con base en la respuesta.
      LoadResult.Page(
        data = response.results,
        prevKey = if (offset == 0) null else offset - response.paging.limit,
        nextKey = if (response.results.isEmpty()) null else offset + response.paging.limit
      )
    } catch (e: Exception) {
      LoadResult.Error(e)
    }
}
