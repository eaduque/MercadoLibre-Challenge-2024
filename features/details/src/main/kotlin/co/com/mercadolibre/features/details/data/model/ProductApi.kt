package co.com.mercadolibre.features.details.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class ProductApi(
  val pictures: List<PictureApi>,
)

@Serializable
data class PictureApi(val id: String)
