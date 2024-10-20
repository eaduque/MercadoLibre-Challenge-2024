import co.com.mercadolibre.libs
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      pluginManager.apply {
        apply("meli.android.library")
        apply("meli.android.hilt")
      }
      extensions.configure<LibraryExtension> {
        defaultConfig {
          /* Define this variable for testing
          testInstrumentationRunner = ""
           */
        }
        testOptions.animationsDisabled = true
      }

      dependencies {
        add("implementation", project(":core:designsystem"))

        add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
        add("implementation", libs.findLibrary("androidx.lifecycle.runtime.compose").get())
        add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())

        add(
          "androidTestImplementation",
          libs.findLibrary("androidx.lifecycle.runtime.testing").get()
        )
      }
    }
  }
}
