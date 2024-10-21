plugins {
  alias(libs.plugins.meli.android.feature)
  alias(libs.plugins.meli.android.library.compose)
  id("kotlinx-serialization")
}

android {
  namespace = "co.com.mercadolibre.features.results"
}

dependencies {
  implementation(projects.core.network)

  implementation(libs.androidx.paging.runtime.ktx)
  implementation(libs.androidx.paging.compose)
}
