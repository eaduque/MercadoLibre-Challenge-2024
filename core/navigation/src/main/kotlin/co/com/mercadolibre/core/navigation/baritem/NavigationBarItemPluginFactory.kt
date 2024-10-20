package co.com.mercadolibre.core.navigation.baritem

interface NavigationBarItemPluginFactory {

  fun isApplicable(): Boolean

  fun createPlugin(): NavigationBarItemPlugin
}
