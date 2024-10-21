package co.com.mercadolibre.features.results.data.api

import co.com.mercadolibre.features.results.data.model.ProductsQueryResponseApi
import retrofit2.http.GET
import retrofit2.http.Query

internal interface ProductsApi {

  /**
   * Se consulta la api con el searchQuery y el offset. el offset permite realizar el paginado de los resultados.
   */
  @GET("sites/MCO/search")
  suspend fun fetchProducts(
    @Query("q") searchQuery: String,
    @Query("offset") offset: Int,
  ): ProductsQueryResponseApi
}
