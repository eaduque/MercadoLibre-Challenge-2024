package co.com.mercadolibre.features.search.data.api

import co.com.mercadolibre.features.search.data.model.SearchQueryResponseApi
import retrofit2.http.GET
import retrofit2.http.Query

internal interface SearchApi {

  @GET("resources/sites/MCO/autosuggest")
  suspend fun fetchSuggestions(
    @Query("q") searchQuery: String,
    @Query("limit") limit: Int,
  ): SearchQueryResponseApi
}
