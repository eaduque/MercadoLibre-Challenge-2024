package co.com.mercadolibre.features.results.data.api

import co.com.mercadolibre.features.results.data.model.ProductsQueryResponseApi
import retrofit2.http.GET
import retrofit2.http.Query

internal interface ProductsApi {

  @GET("sites/MCO/search")
  suspend fun fetchProducts(@Query("q") searchQuery: String): ProductsQueryResponseApi
}
