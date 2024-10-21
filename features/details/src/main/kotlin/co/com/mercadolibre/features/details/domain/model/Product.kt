package co.com.mercadolibre.features.details.domain.model

internal data class Product(
  val pictures: List<Picture>,
  val description: String,
)

internal data class Picture(val url: String)
