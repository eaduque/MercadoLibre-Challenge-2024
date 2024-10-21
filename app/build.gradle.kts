import co.com.mercadolibre.MeliBuildType

plugins {
  alias(libs.plugins.meli.android.application)
  alias(libs.plugins.meli.android.application.compose)
  alias(libs.plugins.meli.android.application.flavors)
  alias(libs.plugins.meli.android.hilt)
}

android {
  namespace = "co.com.mercadolibre"

  defaultConfig {
    applicationId = "co.com.mercadolibre"
    versionCode = 1
    versionName = "1.0.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    debug {
      applicationIdSuffix = MeliBuildType.DEBUG.applicationIdSuffix
    }
    release {
      isMinifyEnabled = true
      applicationIdSuffix = MeliBuildType.RELEASE.applicationIdSuffix
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {

  implementation(projects.core.designsystem)
  implementation(projects.core.network)

  implementation(projects.features.details)
  implementation(projects.features.results)
  implementation(projects.features.search)

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.navigation.compose)

  implementation(libs.androidx.core.splashscreen)
  implementation(libs.androidx.animation.graphics.android)

  testImplementation(libs.junit)

  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(libs.androidx.ui.test.junit4)

  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
}
