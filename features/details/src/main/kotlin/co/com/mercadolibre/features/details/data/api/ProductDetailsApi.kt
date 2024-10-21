package co.com.mercadolibre.features.details.data.api

import co.com.mercadolibre.features.details.data.model.ProductApi
import co.com.mercadolibre.features.details.data.model.ProductDescriptionApi
import retrofit2.http.GET
import retrofit2.http.Path

internal interface ProductDetailsApi {

  @GET("items/{id}")
  suspend fun fetchProductDetails(@Path("id") productId: String): ProductApi

  @GET("items/{id}/description")
  suspend fun fetchProductDescription(@Path("id") productId: String): ProductDescriptionApi
}
