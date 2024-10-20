package co.com.mercadolibre.core.designsystem.component.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import co.com.mercadolibre.core.designsystem.theme.MeliTheme

@Composable
fun OfficialStoreTag(storeName: String, modifier: Modifier = Modifier) {
  Text(
    text = "${storeName.uppercase()} TIENDA OFICIAL",
    modifier = modifier
      .clip(RoundedCornerShape(4.dp))
      .background(Color.Black)
      .padding(horizontal = 2.dp),
    color = Color.White,
    fontSize = 10.sp,
    style = LocalTextStyle.current.copy(
      lineHeight = 1.5.em,
      platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
  )
}


@Preview
@Composable
private fun OfficialStoreTagPreview() {
  MeliTheme {
    OfficialStoreTag("Official")
  }
}
