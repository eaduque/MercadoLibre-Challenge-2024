package co.com.mercadolibre

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/** Configure Compose-specific options */
internal fun Project.configureAndroidCompose(commonExtension: CommonExtension<*, *, *, *, *, *>) {
  commonExtension.apply {
    buildFeatures {
      compose = true
    }

    dependencies {
      val bom = libs.findLibrary("androidx-compose-bom").get()
      add("implementation", platform(bom))
      add("implementation", libs.findLibrary("androidx-ui-tooling-preview").get())
      add("androidTestImplementation", platform(bom))
      add("debugImplementation", libs.findLibrary("androidx-ui-tooling").get())
    }

    @Suppress("UnstableApiUsage")
    testOptions {
      unitTests {
        isIncludeAndroidResources = true // For Robolectric
      }
    }
  }
}