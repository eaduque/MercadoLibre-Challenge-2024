package co.com.mercadolibre.core.navigation.baritem

interface NavigationBarItemPluginPoint {

  fun getPluginFactories(): List<NavigationBarItemPluginFactory>
}
