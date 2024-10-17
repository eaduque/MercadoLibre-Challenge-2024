package co.com.mercadolibre

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import co.com.mercadolibre.ui.theme.MercadolibreChallengeTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen().apply {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        setOnExitAnimationListener { it.remove() }
      }
    }
    super.onCreate(savedInstanceState)

    enableEdgeToEdge()

    setContent {
      MercadolibreChallengeTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          MainScreen(modifier = Modifier.padding(innerPadding))
        }
      }
    }
  }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(colorResource(R.color.meli_yellow_color)), contentAlignment = Alignment.Center
  ) {
    AnimatedVectorDrawable()
  }
}

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun AnimatedVectorDrawable() {
  val image = AnimatedImageVector.animatedVectorResource(R.drawable.splash_screen_meli_icon_anim)
  var atEnd by remember { mutableStateOf(false) }
  LaunchedEffect(Unit) {
    delay(100)
    atEnd = true
  }
  Image(
    painter = rememberAnimatedVectorPainter(image, atEnd),
    contentDescription = null,
    contentScale = ContentScale.Crop
  )
}
