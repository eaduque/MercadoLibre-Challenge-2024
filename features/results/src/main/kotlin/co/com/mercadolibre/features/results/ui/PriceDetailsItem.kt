package co.com.mercadolibre.features.results.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.com.mercadolibre.core.designsystem.theme.MeliTheme
import co.com.mercadolibre.features.results.domain.model.Price
import co.com.mercadolibre.features.results.domain.model.PriceDetails

@Composable
fun PriceDetailsItem(priceDetails: PriceDetails, modifier: Modifier = Modifier) {
  Column(modifier = modifier) {
    if (priceDetails.discount != null) {
      Text(
        text = priceDetails.originalPrice.label,
        style = MaterialTheme.typography.labelMedium.copy(
          textDecoration = TextDecoration.LineThrough
        ),
        color = MaterialTheme.colorScheme.surfaceVariant
      )
    }
    Row(
      horizontalArrangement = Arrangement.spacedBy(4.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = priceDetails.price.label,
        style = MaterialTheme.typography.titleLarge
      )
      if (priceDetails.discount != null) {
        Text(
          text = priceDetails.discount,
          style = MaterialTheme.typography.labelMedium,
          color = colorResource(co.com.mercadolibre.core.designsystem.R.color.meli_green_color)
        )
      }
    }
  }
}

@Preview
@Composable
private fun MyPreview() {
  MeliTheme {
    Surface {
      PriceDetailsItem(
        priceDetails = PriceDetails(
          currencyId = "COP",
          price = Price(price = 5959834.0, label = "$ 5.959.834"),
          originalPrice = Price(price = 3119900.0, label = "$ 3.119.900"),
          discount = "47% OFF"
        )
      )
    }
  }
}
