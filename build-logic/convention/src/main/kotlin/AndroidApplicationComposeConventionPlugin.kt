import co.com.mercadolibre.configureAndroidCompose
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.dependencies
import co.com.mercadolibre.libs

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.application")
        apply("org.jetbrains.kotlin.plugin.compose")
      }

      val extension = extensions.getByType<ApplicationExtension>()
      configureAndroidCompose(extension)


      dependencies {
        add("implementation", libs.findLibrary("androidx.activity.compose").get())
      }
    }
  }
}
