package co.com.mercadolibre.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import co.com.mercadolibre.R

@Composable
internal fun MeliApp(
  appState: MeliAppState,
  modifier: Modifier = Modifier,
) {
  Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
    Scaffold(
      containerColor = Color.Transparent,
      contentColor = MaterialTheme.colorScheme.onBackground,
      contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { innerPadding ->
      MainScreen(appState = appState, modifier = Modifier.padding(innerPadding))
    }
  }
}

@Composable
internal fun MainScreen(appState: MeliAppState, modifier: Modifier = Modifier) {
  val uiState = appState.uiState

  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    if (!uiState.isOnSplashScreen) {
      Text(text = "MercadoLibre!")
    }
    AnimatedVisibility(visible = uiState.isOnSplashScreen, exit = fadeOut()) {
      SplashScreen(uiState.isAnimatedIconAtEnd)
    }
  }
}

@Composable
fun SplashScreen(isAnimatedIconAtEnd: Boolean, modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .fillMaxSize()
      .background(colorResource(R.color.meli_yellow_color)),
    contentAlignment = Alignment.Center
  ) {
    AnimatedVectorDrawable(isAnimatedIconAtEnd)
  }
}

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun AnimatedVectorDrawable(
  isAnimatedIconAtEnd: Boolean,
) {
  val image = AnimatedImageVector.animatedVectorResource(R.drawable.splash_screen_meli_icon_anim)

  Image(
    painter = rememberAnimatedVectorPainter(image, isAnimatedIconAtEnd),
    contentDescription = null,
    contentScale = ContentScale.Crop
  )
}
