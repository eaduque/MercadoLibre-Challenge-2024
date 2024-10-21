package co.com.mercadolibre.features.details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.com.mercadolibre.core.designsystem.component.HorizontalPagerIndicator
import co.com.mercadolibre.core.designsystem.component.MeliAsyncImage
import co.com.mercadolibre.core.designsystem.component.PicturesPositionIndicator
import co.com.mercadolibre.core.designsystem.component.product.FavoriteItem
import co.com.mercadolibre.core.designsystem.theme.MeliTheme
import co.com.mercadolibre.features.details.domain.model.Picture

@Composable
internal fun PicturesPager(
  pictures: List<Picture>,
  modifier: Modifier = Modifier,
) {
  val pagerState = rememberPagerState { pictures.size }

  Column(
    modifier = modifier
      .fillMaxWidth()
      .height(300.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
    ) {
      HorizontalPager(
        state = pagerState, Modifier.fillMaxSize(),
        beyondViewportPageCount = 2
      ) { page ->
        MeliAsyncImage(
          imageUrl = pictures[page].url,
          contentDescription = null,
          modifier = Modifier.fillMaxSize()
        )
      }
      PicturesPositionIndicator(
        currentPos = if (pagerState.pageCount > 0) pagerState.currentPage.inc() else 0,
        totalCount = pictures.size,
        modifier = Modifier.align(Alignment.TopStart)
      )
      FavoriteItem(
        isMarkedAsFavorite = false,
        modifier = Modifier
          .align(Alignment.TopEnd)
          .padding(top = 8.dp, end = 16.dp),
        onClick = {},
      )
    }
    HorizontalPagerIndicator(pagerState)
  }
}

@Preview
@Composable
private fun PicturesPagerPreview() {
  MeliTheme {
    Surface {
      PicturesPager(emptyList())
    }
  }
}
