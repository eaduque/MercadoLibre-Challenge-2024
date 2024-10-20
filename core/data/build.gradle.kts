plugins {
  alias(libs.plugins.meli.android.library)
  alias(libs.plugins.meli.android.hilt)
}

android {
  namespace = "co.com.mercadolibre.core.data"

  testOptions {
    unitTests {
      isIncludeAndroidResources = true
      isReturnDefaultValues = true
    }
  }
}

dependencies {

  api(projects.core.common)
}
