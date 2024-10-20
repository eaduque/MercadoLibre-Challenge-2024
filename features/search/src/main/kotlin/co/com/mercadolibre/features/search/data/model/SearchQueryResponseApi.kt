package co.com.mercadolibre.features.search.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SearchQueryResponseApi(
  @SerialName("q")
  val query: String,
  @SerialName("suggested_queries")
  val items: List<SearchQueryItem>,
)

@Serializable
internal data class SearchQueryItem(
  @SerialName("q")
  val suggestion: String,
  @SerialName("match_start")
  val matchStart: Int,
  @SerialName("match_end")
  val matchEnd: Int,
  @SerialName("is_verified_store")
  val isVerifiedStore: Boolean,
)
