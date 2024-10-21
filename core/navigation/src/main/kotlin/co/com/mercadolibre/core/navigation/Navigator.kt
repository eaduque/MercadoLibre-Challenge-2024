package co.com.mercadolibre.core.navigation

import co.com.mercadolibre.core.navigation.NavigationAction.NavigateBack
import co.com.mercadolibre.core.navigation.NavigationAction.NavigateTo
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class Navigator @Inject constructor() {

  private val _navigationActions = MutableSharedFlow<NavigationAction>(
    extraBufferCapacity = 1,
    onBufferOverflow = BufferOverflow.DROP_OLDEST
  )
  val navigationActions = _navigationActions.asSharedFlow()

  fun navigateTo(
    destination: NavDestination,
    navOptions: NavOptions? = null,
    argument: String? = null
  ) {
    _navigationActions.tryEmit(NavigateTo(destination, navOptions, argument))
  }

  fun navigateBack() {
    _navigationActions.tryEmit(NavigateBack())
  }
}
