package co.com.mercadolibre.features.results.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.com.mercadolibre.core.designsystem.theme.MeliTheme
import co.com.mercadolibre.features.results.domain.model.Installments
import co.com.mercadolibre.features.results.domain.model.Price

@Composable
fun InstallmentsItem(
  installments: Installments,
  modifier: Modifier = Modifier,
) {
  Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
    Text(
      text = "en",
      modifier = modifier,
      style = MaterialTheme.typography.labelMedium,
      color = MaterialTheme.colorScheme.surfaceVariant
    )
    Text(
      text = "${installments.quantity} cuotas de ${installments.amount.label} con ${installments.rate}% interés",
      modifier = modifier,
      style = MaterialTheme.typography.labelMedium,
      color = colorResource(co.com.mercadolibre.core.designsystem.R.color.meli_green_color)
    )
  }
}

@Preview
@Composable
private fun InstallmentsItemPreview() {
  MeliTheme {
    Surface {
      InstallmentsItem(
        installments = Installments(
          quantity = 3,
          amount = Price(price = 5400600.0, label = "$ 5.400.600"),
          rate = 0,
          currencyId = "COP"
        )
      )
    }
  }
}
