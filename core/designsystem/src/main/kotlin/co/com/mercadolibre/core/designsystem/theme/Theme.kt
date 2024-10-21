package co.com.mercadolibre.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Sólo utilizaré los colores claros para Mercado Libre
 */
private val LightColorScheme = lightColorScheme(
  primary = Color(0XFF3483FA),
  secondary = PurpleGrey40,
  tertiary = Pink40,
  background = Color.White,
  surfaceVariant = Color(0x8C000000)
)

@Composable
fun MeliTheme(content: @Composable () -> Unit) {
  MaterialTheme(
    colorScheme = LightColorScheme,
    typography = Typography,
    content = content
  )
}
