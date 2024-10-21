package co.com.mercadolibre.features.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.com.mercadolibre.features.details.ui.PicturesPager

@Composable
internal fun ProductDetailsRoute(
  modifier: Modifier = Modifier,
  viewModel: ProductDetailsViewModel = hiltViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  BackHandler(onBack = viewModel::onBackPressed)

  ProductDetailsScreen(
    uiState = uiState,
    modifier = modifier,
  )
}

@Composable
private fun ProductDetailsScreen(
  uiState: ProductDetailsUIState,
  modifier: Modifier = Modifier,
) {
  Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
      PicturesPager(uiState.product.pictures)
      Text(text = uiState.product.description, Modifier.padding(horizontal = 16.dp))
      Spacer(
        modifier = Modifier.height(
          WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
        )
      )
    }
  }
}
