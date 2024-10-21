package co.com.mercadolibre.features.details.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ProductDescriptionApi(
  @SerialName("plain_text")
  val description: String,
)
