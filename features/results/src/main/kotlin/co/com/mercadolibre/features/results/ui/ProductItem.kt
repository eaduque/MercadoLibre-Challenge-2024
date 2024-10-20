package co.com.mercadolibre.features.results.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import co.com.mercadolibre.core.designsystem.component.MeliAsyncImage
import co.com.mercadolibre.core.designsystem.component.product.FavoriteItem
import co.com.mercadolibre.core.designsystem.component.product.FreeShippingTag
import co.com.mercadolibre.core.designsystem.component.product.InternationalPurchaseTag
import co.com.mercadolibre.core.designsystem.component.product.OfficialStoreTag
import co.com.mercadolibre.core.designsystem.component.product.PolyFullTag
import co.com.mercadolibre.features.results.domain.model.LogisticType
import co.com.mercadolibre.features.results.domain.model.ProductItem

@Composable
fun ProductItem(
  product: ProductItem,
  includeTopDivider: Boolean,
  modifier: Modifier = Modifier,
) {
  var isMarkedAsFavorite by rememberSaveable { mutableStateOf(false) }

  Column(modifier = modifier) {
    if (includeTopDivider) HorizontalDivider(color = DividerDefaults.color.copy(alpha = 0.3f))
    Row(
      modifier = Modifier
        .background(Color.White)
        .clickable { }
        .padding(8.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Box(
        modifier = Modifier
          .fillMaxWidth(0.38f)
          .height(200.dp)
          .clip(RoundedCornerShape(8.dp))
          .background(Color.Gray.copy(alpha = 0.07f)),
      ) {
        MeliAsyncImage(
          imageUrl = product.thumbnail,
          contentDescription = null,
          modifier = Modifier.align(Alignment.Center)
        )
        FavoriteItem(
          isMarkedAsFavorite = isMarkedAsFavorite,
          modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(4.dp),
          onClick = { isMarkedAsFavorite = !isMarkedAsFavorite }
        )
      }
      Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row {
          product.officialStoreName?.let {
            OfficialStoreTag(
              storeName = it,
              modifier = Modifier.padding(bottom = 4.dp)
            )
          }
        }
        Text(
          text = product.title,
          fontSize = 14.sp,
          fontWeight = FontWeight.Normal,
          style = LocalTextStyle.current.copy(
            letterSpacing = 0.em,
            lineHeight = 1.2.em,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
          )
        )
        PriceDetailsItem(product.priceDetails, modifier = Modifier)
        if (product.installments != null) InstallmentsItem(product.installments)
        if (product.shipping.free) FreeShippingTag()
        LogisticTypeItem(product.shipping.logisticType)
      }
    }
  }
}

@Composable
private fun LogisticTypeItem(logisticType: LogisticType, modifier: Modifier = Modifier) {
  when (logisticType) {
    LogisticType.FULFILLMENT -> PolyFullTag(modifier)
    LogisticType.CROSS_DOCKING -> InternationalPurchaseTag(modifier)
    LogisticType.UNKNOWN -> {}
  }
}
