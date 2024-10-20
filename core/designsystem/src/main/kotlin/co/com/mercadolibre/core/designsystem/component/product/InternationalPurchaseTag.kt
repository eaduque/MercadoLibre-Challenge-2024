package co.com.mercadolibre.core.designsystem.component.product

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.com.mercadolibre.core.designsystem.R
import co.com.mercadolibre.core.designsystem.theme.MeliTheme

@Composable
fun InternationalPurchaseTag(modifier: Modifier = Modifier) {
  Icon(
    painter = painterResource(R.drawable.ic__international),
    contentDescription = null,
    modifier = modifier.padding(vertical = 4.dp),
    tint = Color.Unspecified
  )
}

@Preview
@Composable
private fun InternationalPurchaseTagPreview() {
  MeliTheme {
    InternationalPurchaseTag()
  }
}
