package co.com.mercadolibre.features.details

import co.com.mercadolibre.features.details.domain.model.Product

internal data class ProductDetailsUIState(
  val isLoading: Boolean = true,
  val product: Product = Product(emptyList(), ""),
  val error: String? = null,
)
