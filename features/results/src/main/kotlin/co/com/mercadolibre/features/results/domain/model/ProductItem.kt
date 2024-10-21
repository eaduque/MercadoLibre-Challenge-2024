package co.com.mercadolibre.features.results.domain.model

internal data class ProductItem(
  val id: String,
  val title: String,
  val thumbnail: String,
  val officialStoreName: String?,
  val priceDetails: PriceDetails,
  val shipping: Shipping,
  val installments: Installments?,
)

internal data class PriceDetails(
  val currencyId: String,
  val price: Price,
  val originalPrice: Price,
  val discount: String?,
)

internal data class Price(
  val price: Double,
  val label: String,
)

internal data class Shipping(
  val free: Boolean,
  val logisticType: LogisticType,
)

internal enum class LogisticType {
  FULFILLMENT,
  CROSS_DOCKING,
  UNKNOWN
}

internal data class Installments(
  val quantity: Int,
  val amount: Price,
  val rate: Int,
  val currencyId: String,
)
