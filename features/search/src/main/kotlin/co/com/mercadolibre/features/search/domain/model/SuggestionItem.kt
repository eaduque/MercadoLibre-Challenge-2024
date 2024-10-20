package co.com.mercadolibre.features.search.domain.model

internal data class SuggestionItem(
  val suggestion: String,
  val matchStart: Int,
  val matchEnd: Int,
  val isVerifiedStore: Boolean,
)
