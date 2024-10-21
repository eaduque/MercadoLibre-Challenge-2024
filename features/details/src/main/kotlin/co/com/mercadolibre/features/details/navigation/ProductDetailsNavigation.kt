package co.com.mercadolibre.features.details.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import co.com.mercadolibre.core.navigation.NavDestination.ProductDetails
import co.com.mercadolibre.features.details.ProductDetailsRoute

fun NavGraphBuilder.productDetailsScreen() {
  composable(
    route = ProductDetails.route + "/{productId}",
    arguments = listOf(navArgument("productId") { type = NavType.StringType })
  ) {
    ProductDetailsRoute()
  }
}
