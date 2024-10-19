plugins {
  alias(libs.plugins.meli.android.library)
  alias(libs.plugins.meli.android.hilt)
}

android {
  namespace = "co.com.mercadolibre.core.common"
}

dependencies {
  api(libs.retrofit.core)
}
