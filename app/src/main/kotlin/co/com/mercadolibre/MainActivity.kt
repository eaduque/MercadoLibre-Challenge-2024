package co.com.mercadolibre

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import co.com.mercadolibre.core.data.util.NetworkMonitor
import co.com.mercadolibre.core.designsystem.theme.MeliTheme
import co.com.mercadolibre.core.navigation.Navigator
import co.com.mercadolibre.navigation.HandleNavigation
import co.com.mercadolibre.ui.MeliApp
import co.com.mercadolibre.ui.rememberMeliAppState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject lateinit var networkMonitor: NetworkMonitor
  @Inject lateinit var navigator: Navigator
  private val viewModel: MainActivityViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen().apply {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        setOnExitAnimationListener { it.remove() }
      }
    }
    super.onCreate(savedInstanceState)

    var uiState: MainActivityUIState by mutableStateOf(MainActivityUIState())

    lifecycleScope.launch {
      lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.uiState
          .onEach { uiState = it }
          .collect()
      }
    }

    enableEdgeToEdge()

    setContent {
      val appState = rememberMeliAppState(networkMonitor, uiState)

      HandleNavigation(navigator, appState.navController)

      MeliTheme {
        MeliApp(appState = appState)
      }
    }
  }
}
