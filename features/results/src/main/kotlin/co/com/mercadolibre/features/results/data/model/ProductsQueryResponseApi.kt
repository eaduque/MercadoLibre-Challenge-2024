package co.com.mercadolibre.features.results.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductsQueryResponseApi(
  @SerialName("site_id")
  val siteId: String,
  val query: String,
  val results: List<ProductItemApi>,
)

@Serializable
data class ProductItemApi(
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
data class ShippingApi(
  @SerialName("free_shipping")
  val free: Boolean,
  @SerialName("logistic_type")
  val logisticType: String? = null,
)

@Serializable
data class InstallmentsApi(
  val quantity: Int,
  val amount: Double,
  val rate: Int,
  @SerialName("currency_id")
  val currencyId: String,
)
