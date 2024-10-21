plugins {
  alias(libs.plugins.meli.android.feature)
  alias(libs.plugins.meli.android.library.compose)
  id("kotlinx-serialization")
}

android {
  namespace = "co.com.mercadolibre.features.details"
}

dependencies {
  implementation(projects.core.network)
}
