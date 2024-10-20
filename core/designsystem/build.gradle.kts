plugins {
  alias(libs.plugins.meli.android.library)
  alias(libs.plugins.meli.android.library.compose)
}

android {
  namespace = "co.com.mercadolibre.core.designsystem"

  defaultConfig {
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
}

dependencies {

  //api(projects.libraries.compose)
  api(projects.core.navigation)

  api(libs.androidx.core.ktx)
  api(libs.androidx.constraintlayout)
  api(libs.androidx.compose.material.iconsExtended)
  api(libs.androidx.material3)
  api(libs.androidx.compose.runtime)
  api(libs.androidx.compose.ui.util)
  api(libs.androidx.animation.graphics.android)

  implementation(libs.coil.kt.compose)

  testImplementation(libs.junit)

  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}
