package co.com.mercadolibre.features.results.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import co.com.mercadolibre.core.navigation.NavDestination.ProductResults
import co.com.mercadolibre.features.results.ProductResultsRoute

fun NavGraphBuilder.productResultsScreen() {
  composable(
    route = ProductResults.route + "/{query}",
    arguments = listOf(navArgument("query") { type = NavType.StringType })
  ) { backStackEntry ->
    ProductResultsRoute()
  }
}
