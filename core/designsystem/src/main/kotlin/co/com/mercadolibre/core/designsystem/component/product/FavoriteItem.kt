package co.com.mercadolibre.core.designsystem.component.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.com.mercadolibre.core.designsystem.icon.MeliIcons
import co.com.mercadolibre.core.designsystem.theme.MeliTheme

@Composable
fun FavoriteItem(
  isMarkedAsFavorite: Boolean,
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
) {
  Box(modifier = modifier) {
    IconButton(
      onClick = onClick,
      modifier = Modifier
        .clip(CircleShape)
        .background(Color.White.copy(alpha = 0.8f))
        .size(32.dp)
    ) {
      Icon(
        painter = painterResource(if (isMarkedAsFavorite) MeliIcons.FilledBookMark else MeliIcons.OutlinedBookMark),
        contentDescription = null,
        modifier = Modifier.size(18.dp),
        tint = Color.Unspecified
      )
    }
  }
}

@Preview
@Composable
private fun FavoriteItemPreview() {
  MeliTheme {
    Surface {
      Row {
        FavoriteItem(isMarkedAsFavorite = true, onClick = {})
        FavoriteItem(isMarkedAsFavorite = false, onClick = {})
      }
    }
  }
}
