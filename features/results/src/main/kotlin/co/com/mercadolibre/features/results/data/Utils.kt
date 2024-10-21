package co.com.mercadolibre.features.results.data

import co.com.mercadolibre.features.results.data.model.InstallmentsApi
import co.com.mercadolibre.features.results.data.model.ProductItemApi
import co.com.mercadolibre.features.results.data.model.ShippingApi
import co.com.mercadolibre.features.results.domain.model.Installments
import co.com.mercadolibre.features.results.domain.model.LogisticType
import co.com.mercadolibre.features.results.domain.model.Price
import co.com.mercadolibre.features.results.domain.model.PriceDetails
import co.com.mercadolibre.features.results.domain.model.ProductItem
import co.com.mercadolibre.features.results.domain.model.Shipping
import java.text.NumberFormat
import java.util.Locale

/**
 * Estas utilidades permiten pasar de los modelos de la api a los modelos de nuestro dominio.
 */

/**
 * Se convierten los modelos de la api a los modelos de nuestro dominio.
 */
internal fun ProductItemApi.toDomain() = ProductItem(
  id = id,
  title = title,
  // Uso este template para cargar las imagenes en una calidad alta.
  thumbnail = "https://http2.mlstatic.com/D_Q_NP_2X_$thumbnailId-V.jpg",
  officialStoreName = officialStoreName,
  priceDetails = getDomainPrice(currencyId, price, originalPrice),
  shipping = shipping.toDomain(),
  installments = installments?.toDomain()
)

/**
 * Se obtienen los detalles del precio del producto en un formato de fácil uso en el dominio.
 */
internal fun getDomainPrice(
  currencyId: String,
  price: Double,
  originalPrice: Double,
): PriceDetails {
  val discountPercentage = calculateDiscountPercentage(price, originalPrice)

  return PriceDetails(
    currencyId = currencyId,
    price = Price(price, formatPrice(currencyId, price)),
    originalPrice = Price(originalPrice, formatPrice(currencyId, originalPrice)),
    discount = if (discountPercentage > 0) "$discountPercentage% OFF" else null
  )
}

/**
 * Se obtiene el locale de acuerdo a la moneda.
 */
internal fun getLocaleByCurrency(currencyId: String): Locale {
  return when (currencyId) {
    "COP" -> Locale("es", "CO")
    "USD" -> Locale("en", "US")
    // Agregar más casos según sea necesario
    else -> Locale.getDefault()
  }
}


/**
 * Se formatea el precio de acuerdo a la moneda.
 */
internal fun formatPrice(currencyId: String, price: Double): String {
  val locale = getLocaleByCurrency(currencyId)
  val numberFormat = NumberFormat.getCurrencyInstance(locale)
  // Generalmente, los precios colombianos no muestran los centavos. Por eso elimino los decimales.
  if (currencyId == "COP") numberFormat.maximumFractionDigits = 0
  return numberFormat.format(price)
}

/**
 * Se calcula el descuento de un producto asegurándome de redondear el valor al convertirlo a un Int.
 */
internal fun calculateDiscountPercentage(price: Double, originalPrice: Double): Int {
  return if (originalPrice > 0) {
    ((originalPrice - price) / originalPrice * 100).toInt()
  } else {
    0
  }
}

/**
 * Se convierten los modelos de la api a los modelos de nuestro dominio.
 */
internal fun ShippingApi.toDomain() = Shipping(
  free = free,
  logisticType = when (logisticType) {
    "fulfillment" -> LogisticType.FULFILLMENT
    "cross_docking" -> LogisticType.CROSS_DOCKING
    else -> LogisticType.UNKNOWN
  }
)

/**
 * Se convierten los modelos de la api a los modelos de nuestro dominio.
 */
internal fun InstallmentsApi.toDomain(): Installments {
  return Installments(
    quantity = quantity,
    amount = Price(amount, formatPrice(currencyId, amount)),
    rate = rate,
    currencyId = currencyId
  )
}
