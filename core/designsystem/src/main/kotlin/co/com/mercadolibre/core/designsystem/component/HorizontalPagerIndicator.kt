package co.com.mercadolibre.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.com.mercadolibre.core.designsystem.theme.MeliTheme

@Composable
fun HorizontalPagerIndicator(
  pagerState: PagerState,
  modifier: Modifier = Modifier,
  indicatorCount: Int = 5,
  indicatorSize: Dp = 12.dp,
  indicatorShape: Shape = CircleShape,
  space: Dp = 4.dp,
  activeColor: Color = MaterialTheme.colorScheme.primary,
  inActiveColor: Color = Color.LightGray,
) {
  val listState = rememberLazyListState()
  val totalWidth: Dp = indicatorSize * indicatorCount + space * (indicatorCount - 1)
  val widthInPx = LocalDensity.current.run { indicatorSize.toPx() }
  val currentItem by remember { derivedStateOf { pagerState.currentPage } }
  val itemCount = pagerState.pageCount

  LaunchedEffect(key1 = currentItem) {
    val viewportSize = listState.layoutInfo.viewportSize
    listState.animateScrollToItem(currentItem, (widthInPx / 2 - viewportSize.width / 2).toInt())
  }

  LazyRow(
    modifier = modifier.width(totalWidth),
    state = listState,
    contentPadding = PaddingValues(vertical = space),
    horizontalArrangement = Arrangement.spacedBy(space),
    userScrollEnabled = false
  ) {
    indicatorItems(
      itemCount,
      currentItem,
      indicatorCount,
      indicatorShape,
      activeColor,
      inActiveColor,
      indicatorSize,
    )
  }
}

private fun LazyListScope.indicatorItems(
  itemCount: Int,
  currentItem: Int,
  indicatorCount: Int,
  indicatorShape: Shape,
  activeColor: Color,
  inActiveColor: Color,
  indicatorSize: Dp,
) {
  items(itemCount) { index ->
    val isSelected = (index == currentItem)
    val centerItemIndex = indicatorCount / 2
    val right1 = (currentItem < centerItemIndex && index >= indicatorCount - 1)

    val right2 =
      (currentItem >= centerItemIndex &&
          index >= currentItem + centerItemIndex &&
          index < itemCount - centerItemIndex + 1)
    val isRightEdgeItem = right1 || right2

    val isLeftEdgeItem =
      index <= currentItem - centerItemIndex &&
          currentItem > centerItemIndex &&
          index < itemCount - indicatorCount + 1

    Box(
      modifier = Modifier
        .graphicsLayer {
          val scale = when {
            isLeftEdgeItem || isRightEdgeItem -> .4f
            else -> .7f
          }
          scaleX = scale
          scaleY = scale

        }
        .clip(indicatorShape)
        .size(indicatorSize)
        .background(
          color = if (isSelected) activeColor else inActiveColor,
          shape = indicatorShape
        )
    )
  }
}

@Preview
@Composable
private fun HorizontalPagerIndicatorPreview() {
  MeliTheme {
    Surface {
      HorizontalPagerIndicator(rememberPagerState { 9 })
    }
  }
}
