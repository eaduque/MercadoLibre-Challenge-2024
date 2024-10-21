package co.com.mercadolibre.features.details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.com.mercadolibre.core.designsystem.theme.MeliTheme
import co.com.mercadolibre.features.details.R

@Composable
internal fun ErrorScreen(modifier: Modifier = Modifier) {
  Box(modifier = modifier, contentAlignment = Alignment.Center) {
    Column(
      modifier = modifier,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Image(
        painterResource(R.drawable.error_img_state), contentDescription = null,
        modifier = Modifier.size(120.dp),
      )
      Spacer(Modifier.height(16.dp))
      Text(
        text = stringResource(R.string.something_went_wrong),
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal),
        color = MaterialTheme.colorScheme.surfaceVariant
      )
      Spacer(Modifier.height(8.dp))
      Text(
        text = stringResource(R.string.please_try_again),
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f)
      )
    }
  }
}

@Preview
@Composable
private fun ErrorScreenPreview() {
  MeliTheme {
    Surface {
      ErrorScreen()
    }
  }
}
