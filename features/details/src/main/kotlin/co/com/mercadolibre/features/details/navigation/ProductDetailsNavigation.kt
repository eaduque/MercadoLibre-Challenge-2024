package co.com.mercadolibre.features.details.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import co.com.mercadolibre.core.navigation.NavDestination.ProductDetails
import co.com.mercadolibre.features.details.ProductDetailsRoute

/**
 * Esta es la única entrada a la pantalla de resultados ya que la lógica de este módulo es interna.
 */
fun NavGraphBuilder.productDetailsScreen() {
  composable(
    route = ProductDetails.route + "/{productId}",
    arguments = listOf(navArgument("productId") { type = NavType.StringType })
  ) {
    ProductDetailsRoute()
  }
}
