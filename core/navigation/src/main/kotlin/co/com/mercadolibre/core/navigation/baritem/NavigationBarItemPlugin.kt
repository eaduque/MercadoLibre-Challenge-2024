package co.com.mercadolibre.core.navigation.baritem

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import co.com.mercadolibre.core.navigation.NavDestination

interface NavigationBarItemPlugin {
  fun destination(): NavDestination
  @StringRes fun labelResId(): Int
  fun icon(): ImageVector
  fun selectedIcon(): ImageVector
}
