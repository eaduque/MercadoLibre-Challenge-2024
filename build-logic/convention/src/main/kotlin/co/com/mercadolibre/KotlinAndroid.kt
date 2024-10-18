package co.com.mercadolibre

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/** Configure base Kotlin with Android options */
internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension<*, *, *, *, *, *>) {
  commonExtension.apply {
    compileSdk = 34

    defaultConfig {
      minSdk = 21
    }

    compileOptions {
      // Up to Java 11 APIs are available through desugaring
      // https://developer.android.com/studio/write/java11-minimal-support-table
      sourceCompatibility = JavaVersion.VERSION_11
      targetCompatibility = JavaVersion.VERSION_11
    }
  }

  configureKotlin()
}

/** Configure base Kotlin options */
private fun Project.configureKotlin() {
  // Use withType to workaround https://youtrack.jetbrains.com/issue/KT-55947
  tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
      // Set JVM target to 11
      jvmTarget.set(JvmTarget.JVM_11)

      val warningsAsErrors: String? by project
      // Report an error if there are any warnings
      allWarningsAsErrors.set(warningsAsErrors.toBoolean())
      // Additional compiler arguments
      freeCompilerArgs.addAll(
        listOf(
          // Enable experimental coroutines APIs, including Flow
          "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        )
      )
    }
  }
}
