package co.com.mercadolibre.core.navigation.baritem

import javax.inject.Inject

class NavigationBarPluginManager @Inject constructor(
  private val pluginPoint: NavigationBarItemPluginPoint,
) {

  fun getApplicableNavBarItemPlugins() =
    pluginPoint.getPluginFactories()
      .filter { it.isApplicable() }
      .map { it.createPlugin() }
}
