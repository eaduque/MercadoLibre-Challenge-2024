package co.com.mercadolibre.features.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import co.com.mercadolibre.core.designsystem.icon.MeliIcons.FilledArrowForward
import co.com.mercadolibre.core.designsystem.icon.MeliIcons.FilledSearch
import co.com.mercadolibre.features.search.domain.model.SuggestionItem

@Composable
internal fun SuggestionItem(
  item: SuggestionItem,
  modifier: Modifier = Modifier,
  onSuggestionClick: (SuggestionItem) -> Unit,
) {
  val itemAlpha = LocalContentColor.current.copy(alpha = 0.3f)

  Row(
    modifier = modifier
      .clickable { onSuggestionClick(item) }
      .padding(horizontal = 16.dp, vertical = 12.dp)
      .semantics { contentDescription = item.suggestion },
    horizontalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    Icon(imageVector = FilledSearch, contentDescription = null, tint = itemAlpha)
    Text(
      text = item.suggestion,
      modifier = modifier.weight(1f),
      color = Color.Unspecified.copy(alpha = 0.5f)
    )
    Icon(painter = painterResource(FilledArrowForward), contentDescription = null, tint = itemAlpha)
  }
}
