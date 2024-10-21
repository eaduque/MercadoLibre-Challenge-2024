package co.com.mercadolibre.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import co.com.mercadolibre.core.designsystem.R

/**
 * Un simple composable que muestra un mensaje de error cuando el dispositivo no tiene conexión a internet.
 * El mensaje es permantente y se oculta cuando el dispositivo vuelve a tener conexión a internet.
 */
@Composable
internal fun InternetStatusToast(
  isOffline: Boolean,
  modifier: Modifier = Modifier,
) {
  Box(contentAlignment = Alignment.BottomCenter, modifier = modifier) {
    // Este elemento se usa como placeholder para retrasar la animación del verdadero texto
    val errorFontSize = 12.sp
    AnimatedVisibility(
      visible = isOffline,
      enter = expandVertically(
        tween(
          durationMillis = 400,
          delayMillis = 800,
          easing = LinearEasing
        )
      ),
      exit = shrinkVertically(
        tween(durationMillis = 400, delayMillis = 1700, easing = LinearEasing)
      )
    ) {
      Text(
        text = "",
        modifier = Modifier.fillMaxWidth(),
        fontSize = errorFontSize,
      )
    }
    AnimatedVisibility(
      visible = isOffline,
      enter = fadeIn(tween(durationMillis = 200, delayMillis = 200, easing = LinearEasing))
          + expandVertically(
        animationSpec = tween(durationMillis = 200, easing = LinearEasing),
        expandFrom = Alignment.Top
      ),
      exit = shrinkVertically(
        animationSpec = tween(durationMillis = 300, delayMillis = 1500, easing = LinearEasing),
        shrinkTowards = Alignment.Top
      )
    ) {
      val bgColor by animateColorAsState(
        targetValue = if (isOffline) {
          colorResource(R.color.core_designsystem_slim_offline)
        } else {
          colorResource(R.color.core_designsystem_slim_online)
        },
        animationSpec = tween(400, easing = LinearEasing),
        label = "no_internet_connection_label_color_animation"
      )
      Text(
        text = stringResource(id = if (isOffline) R.string.core_designsystem_no_connection else R.string.core_designsystem_back_online),
        modifier = Modifier
          .background(bgColor)
          .fillMaxWidth(),
        color = Color(0xFFFFFFFF),
        fontSize = errorFontSize,
        textAlign = TextAlign.Center
      )
    }
  }
}
