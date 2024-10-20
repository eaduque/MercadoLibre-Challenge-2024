package co.com.mercadolibre.features.results.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import co.com.mercadolibre.core.designsystem.icon.MeliIcons

@Composable
fun EmptyResultsWidget(modifier: Modifier = Modifier) {
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      imageVector = MeliIcons.FilledSearch, contentDescription = null,
      modifier = Modifier
        .size(80.dp)
        .clip(CircleShape)
        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.1f))
        .padding(12.dp),
      colorFilter = ColorFilter.tint(Color.White)
    )
    Spacer(Modifier.height(16.dp))
    Text(
      text = "No encontramos publicaciones",
      style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal),
      color = MaterialTheme.colorScheme.surfaceVariant
    )
    Spacer(Modifier.height(8.dp))
    Text(
      text = "Revisa que la palabra esta bien escrita.",
      style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal),
      color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f)
    )
  }
}
