package co.com.mercadolibre.core.designsystem.component.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.com.mercadolibre.core.designsystem.R
import co.com.mercadolibre.core.designsystem.theme.MeliTheme

@Composable
fun PolyFullTag(modifier: Modifier = Modifier) {
  Row(
    modifier = modifier.padding(vertical = 2.dp),
    horizontalArrangement = Arrangement.spacedBy(3.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = stringResource(R.string.core_designsystem_enviado_por_tag),
      modifier = modifier,
      color = Color.Gray.copy(alpha = 0.8f),
      fontSize = 12.sp,
    )
    Icon(
      painter = painterResource(R.drawable.ic__poly_full),
      contentDescription = null,
      tint = Color.Unspecified,
    )
  }
}

@Preview
@Composable
private fun PolyFullTagPreview() {
  MeliTheme {
    Surface {
      PolyFullTag()
    }
  }
}
