plugins {
  alias(libs.plugins.meli.android.library)
  alias(libs.plugins.meli.android.hilt)
}

android {
  namespace = "co.com.mercadolibre.core.navigation"
}

dependencies {
  implementation(libs.androidx.navigation.compose)
}
