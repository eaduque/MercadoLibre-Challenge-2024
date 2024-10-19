plugins {
  alias(libs.plugins.meli.android.library)
  alias(libs.plugins.meli.android.hilt)
  alias(libs.plugins.secrets)
  id("kotlinx-serialization")
}

android {
  namespace = "co.com.mercadolibre.core.network"

    buildFeatures {
    buildConfig = true
  }

  @Suppress("UnstableApiUsage")
  testOptions {
    unitTests {
      isIncludeAndroidResources = true
      isReturnDefaultValues = true
    }
  }
}

secrets {
  defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {

  api(projects.core.common)
  api(libs.retrofit.core)
  api(libs.retrofit.kotlin.serialization)

  implementation(libs.kotlinx.serialization.json)
  implementation(libs.okhttp.logging)
}
