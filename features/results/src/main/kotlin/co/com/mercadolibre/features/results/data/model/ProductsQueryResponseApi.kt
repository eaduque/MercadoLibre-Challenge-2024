package co.com.mercadolibre.features.results.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO: Por disponibilidad de tiempo no alcancé a implementar la db. En cuyo caso todos estos modelos debieron
 * moverse al módulo data para centralizarlos en un solo lugar y que cualquier módulo accediera al
 * objeto global Productitem.
 */

@Serializable
internal data class ProductsQueryResponseApi(
  @SerialName("site_id")
  val siteId: String,
  val query: String,
  val paging: PagingApi,
  val results: List<ProductItemApi>,
)

@Serializable
internal data class PagingApi(
  val total: Int,
  val offset: Int,
  val limit: Int,
  @SerialName("primary_results")
  val primaryResults: Int,
)

@Serializable
internal data class ProductItemApi(
  val id: String,
  val title: String,
  @SerialName("thumbnail_id")
  val thumbnailId: String,
  @SerialName("official_store_name")
  val officialStoreName: String? = null,
  @SerialName("currency_id")
  val currencyId: String,
  val price: Double,
  @SerialName("original_price")
  val originalPrice: Double,
  val shipping: ShippingApi,
  val installments: InstallmentsApi? = null,
)

@Serializable
internal data class ShippingApi(
  @SerialName("free_shipping")
  val free: Boolean,
  @SerialName("logistic_type")
  val logisticType: String? = null,
)

@Serializable
internal data class InstallmentsApi(
  val quantity: Int,
  val amount: Double,
  val rate: Int,
  @SerialName("currency_id")
  val currencyId: String,
)
