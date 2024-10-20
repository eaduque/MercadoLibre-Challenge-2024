package co.com.mercadolibre.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun AnimatedVectorDrawable(
  isAnimatedIconAtEnd: Boolean,
  @DrawableRes animatedVectorResourceId: Int,
) {
  val image = AnimatedImageVector.animatedVectorResource(animatedVectorResourceId)

  Image(
    painter = rememberAnimatedVectorPainter(image, isAnimatedIconAtEnd),
    contentDescription = null,
    contentScale = ContentScale.Crop
  )
}
