package co.com.mercadolibre.features.results

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
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
  val productsFlow by remember(uiState.products) { derivedStateOf { snapshotFlow { uiState.products } } }
  val lazyProducts = productsFlow.collectAsLazyPagingItems()

  Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
    Box {
      AnimatedVisibility(
        visible = lazyProducts.itemCount == 0 && uiState.isLoading,
        modifier = Modifier.align(Alignment.Center),
        content = { LoadingWidget() }
      )

      AnimatedVisibility(
        visible = lazyProducts.itemCount == 0 && lazyProducts.loadState.refresh !is LoadState.Loading,
        modifier = Modifier.align(Alignment.Center),
        content = { EmptyResultsWidget() }
      )

      LazyColumn {
        items(lazyProducts.itemCount) { index ->
          lazyProducts[index]?.let { product ->
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
