package co.com.mercadolibre.core.designsystem.component.product

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.com.mercadolibre.core.designsystem.R
import co.com.mercadolibre.core.designsystem.theme.MeliTheme

@Composable
fun FreeShippingTag(modifier: Modifier = Modifier) {
  Text(
    text = stringResource(R.string.core_designsystem_free_shipping_tag),
    modifier = modifier.padding(vertical = 4.dp),
    color = colorResource(R.color.meli_green_color),
    fontSize = 12.sp,
    fontWeight = FontWeight.SemiBold,
  )
}

@Preview
@Composable
private fun FreeShippingTagPreview() {
  MeliTheme {
    FreeShippingTag()
  }
}
