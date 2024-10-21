package co.com.mercadolibre.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.com.mercadolibre.core.designsystem.theme.MeliTheme

@Composable
fun PicturesPositionIndicator(
  currentPos: Int,
  totalCount: Int,
  modifier: Modifier = Modifier,
) {
  Text(
    text = "$currentPos / $totalCount",
    modifier = modifier
      .padding(horizontal = 16.dp, vertical = 8.dp)
      .clip(RoundedCornerShape(40.dp))
      .background(Color(0XFFF5F5F5))
      .padding(horizontal = 6.dp, vertical = 2.dp),
    style = MaterialTheme.typography.labelLarge,
  )
}


@Preview
@Composable
private fun PicturesPositionIndicatorPreview() {
  MeliTheme {
    Surface {
      PicturesPositionIndicator(currentPos = 1, totalCount = 10)
    }
  }
}
