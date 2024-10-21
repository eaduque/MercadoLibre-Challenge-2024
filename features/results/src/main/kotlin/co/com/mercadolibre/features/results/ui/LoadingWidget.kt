package co.com.mercadolibre.features.results.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.com.mercadolibre.core.designsystem.theme.MeliTheme

@Composable
internal fun LoadingWidget(modifier: Modifier = Modifier) {
  CircularProgressIndicator(
    modifier = modifier.size(64.dp),
    strokeWidth = 4.dp,
    strokeCap = StrokeCap.Square,
  )
}

@Preview
@Composable
private fun LoadingWidgetPreview() {
  MeliTheme {
    Surface {
      LoadingWidget()
    }
  }
}
