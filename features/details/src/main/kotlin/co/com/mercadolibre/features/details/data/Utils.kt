package co.com.mercadolibre.features.details.data

import co.com.mercadolibre.features.details.data.model.PictureApi
import co.com.mercadolibre.features.details.data.model.ProductApi
import co.com.mercadolibre.features.details.domain.model.Picture
import co.com.mercadolibre.features.details.domain.model.Product

internal fun ProductApi.toDomain(): Product {
  return Product(
    pictures = pictures.map { it.toDomain() },
    description = ""
  )
}

internal fun PictureApi.toDomain() = Picture(url = "https://http2.mlstatic.com/D_Q_NP_2X_$id-V.jpg")
