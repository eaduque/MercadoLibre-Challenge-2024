package co.com.mercadolibre.features.results

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.com.mercadolibre.features.results.domain.model.ProductItem
import co.com.mercadolibre.features.results.ui.EmptyResultsWidget
import co.com.mercadolibre.features.results.ui.LoadingWidget
import co.com.mercadolibre.features.results.ui.ProductItem

@Composable
internal fun ProductResultsRoute(
  modifier: Modifier = Modifier,
  viewModel: ProductResultsViewModel = hiltViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  ProductResultsScreen(
    uiState = uiState,
    modifier = modifier,
    onProductItemClick = viewModel::onProductItemClick
  )
}

@Composable
internal fun ProductResultsScreen(
  uiState: ProductResultsUIState,
  modifier: Modifier = Modifier,
  onProductItemClick: (ProductItem) -> Unit,
) {
  Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
    Box {
      AnimatedVisibility(
        visible = uiState.products.isEmpty() && uiState.isLoading,
        modifier = Modifier.align(Alignment.Center),
        content = { LoadingWidget() }
      )

      AnimatedVisibility(
        visible = uiState.products.isEmpty() && !uiState.isLoading,
        modifier = Modifier.align(Alignment.Center),
        content = { EmptyResultsWidget() }
      )

      AnimatedVisibility(
        visible = uiState.products.isNotEmpty(),
        modifier = Modifier.fillMaxWidth()
      ) {
        LazyColumn {
          itemsIndexed(uiState.products, key = { _, product -> product.id }) { index, product ->
            ProductItem(
              product = product,
              includeTopDivider = index != 0,
              onClick = onProductItemClick
            )
          }
        }
      }
    }
  }
}
