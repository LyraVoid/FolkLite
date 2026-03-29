package me.bmax.apatch.ui.screen

import android.net.Uri
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import me.bmax.apatch.ui.viewmodel.PatchesViewModel
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin
import kotlin.math.sqrt

@Immutable
private class TabNavTransitionEasing(
    response: Float,
    damping: Float,
) : Easing {
    private val r: Float
    private val w: Float
    private val c2: Float

    init {
        val omega = 2.0 * PI / response
        val k = omega * omega
        val c = damping * 4.0 * PI / response
        w = (sqrt(4.0 * k - c * c) / 2.0).toFloat()
        r = (-c / 2.0).toFloat()
        c2 = r / w
    }

    override fun transform(fraction: Float): Float {
        val t = fraction.toDouble()
        val decay = exp(r * t)
        return (decay * (-cos(w * t) + c2 * sin(w * t)) + 1.0).toFloat()
    }
}

private val TabNavEasing = TabNavTransitionEasing(0.8f, 0.95f)
private fun <T> tabTween() = tween<T>(durationMillis = 500, easing = TabNavEasing)

private fun tabEnterTransition(): EnterTransition = slideInHorizontally(
    initialOffsetX = { it },
    animationSpec = tabTween()
)

private fun tabExitTransition(): ExitTransition = slideOutHorizontally(
    targetOffsetX = { -it / 4 },
    animationSpec = tabTween()
) + fadeOut(animationSpec = tabTween())

private fun tabPopEnterTransition(): EnterTransition = slideInHorizontally(
    initialOffsetX = { -it / 4 },
    animationSpec = tabTween()
) + fadeIn(animationSpec = tabTween())

private fun tabPopExitTransition(): ExitTransition = slideOutHorizontally(
    targetOffsetX = { it },
    animationSpec = tabTween()
) + fadeOut(animationSpec = tabTween())

private class NavControllerTabNavigator(
    private val navController: NavHostController
) : TabNavigator {
    override fun navigate(route: String) = navController.navigate(route)
    override fun popBackStack(): Boolean = navController.popBackStack()
    override fun navigateUp(): Boolean = navController.navigateUp()
}

@Composable
private fun rememberTabNavigator(navController: NavHostController): TabNavigator {
    return remember(navController) { NavControllerTabNavigator(navController) }
}

object TabRoutes {
    const val HOME = "home"
    const val INSTALL_MODE_SELECT = "install_mode_select"
    const val UNINSTALL_MODE_SELECT = "uninstall_mode_select"
    const val PATCHES = "patches/{mode}"
    const val ABOUT = "about"

    const val KMODULE = "kmodule"
    const val ONLINE_KPM_MODULE = "online_kpm_module"
    const val KPM_AUTO_LOAD_CONFIG = "kpm_auto_load_config"
    const val INSTALL_KPM = "install_kpm/{uri}/{type}"

    const val AMODULE = "amodule"
    const val ONLINE_APM_MODULE = "online_apm_module"
    const val INSTALL_APM = "install_apm/{uri}/{type}"
    const val EXECUTE_APM_ACTION = "execute_apm_action/{moduleId}"

    const val SETTINGS = "settings"
    const val NAVIGATION_LAYOUT = "navigation_layout"
    const val UMOUNT_CONFIG = "umount_config"
}

@Composable
fun HomeTabNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navigator = rememberTabNavigator(navController)

    NavHost(
        navController = navController,
        startDestination = TabRoutes.HOME,
        modifier = modifier,
        enterTransition = { tabEnterTransition() },
        exitTransition = { tabExitTransition() },
        popEnterTransition = { tabPopEnterTransition() },
        popExitTransition = { tabPopExitTransition() },
    ) {
        composable(TabRoutes.HOME) {
            HomeScreen(navigator)
        }
        composable(TabRoutes.INSTALL_MODE_SELECT) {
            InstallModeSelectScreen(navigator)
        }
        composable(TabRoutes.UNINSTALL_MODE_SELECT) {
            UninstallModeSelectScreen(navigator)
        }
        composable(
            TabRoutes.PATCHES,
            arguments = listOf(navArgument("mode") { type = NavType.IntType })
        ) { backStackEntry ->
            val modeOrdinal = backStackEntry.arguments?.getInt("mode") ?: 0
            val mode = PatchesViewModel.PatchMode.entries[modeOrdinal]
            Patches(mode)
        }
        composable(TabRoutes.ABOUT) {
            AboutScreen(navigator)
        }
    }
}

@Composable
fun KModuleTabNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navigator = rememberTabNavigator(navController)

    NavHost(
        navController = navController,
        startDestination = TabRoutes.KMODULE,
        modifier = modifier,
        enterTransition = { tabEnterTransition() },
        exitTransition = { tabExitTransition() },
        popEnterTransition = { tabPopEnterTransition() },
        popExitTransition = { tabPopExitTransition() },
    ) {
        composable(TabRoutes.KMODULE) {
            KPModuleScreen(navigator)
        }
        composable(TabRoutes.ONLINE_KPM_MODULE) {
            OnlineKPMModuleScreen(navigator)
        }
        composable(TabRoutes.KPM_AUTO_LOAD_CONFIG) {
            KpmAutoLoadConfigScreen(navigator)
        }
        composable(
            TabRoutes.INSTALL_KPM,
            arguments = listOf(
                navArgument("uri") { type = NavType.StringType },
                navArgument("type") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val uri = Uri.parse(backStackEntry.arguments?.getString("uri"))
            val type = MODULE_TYPE.valueOf(backStackEntry.arguments?.getString("type") ?: "KPM")
            InstallScreen(navigator, uri, type)
        }
        composable(
            TabRoutes.PATCHES,
            arguments = listOf(navArgument("mode") { type = NavType.IntType })
        ) { backStackEntry ->
            val modeOrdinal = backStackEntry.arguments?.getInt("mode") ?: 0
            val mode = PatchesViewModel.PatchMode.entries[modeOrdinal]
            Patches(mode)
        }
    }
}

@Composable
fun AModuleTabNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navigator = rememberTabNavigator(navController)

    NavHost(
        navController = navController,
        startDestination = TabRoutes.AMODULE,
        modifier = modifier,
        enterTransition = { tabEnterTransition() },
        exitTransition = { tabExitTransition() },
        popEnterTransition = { tabPopEnterTransition() },
        popExitTransition = { tabPopExitTransition() },
    ) {
        composable(TabRoutes.AMODULE) {
            APModuleScreen(navigator)
        }
        composable(TabRoutes.ONLINE_APM_MODULE) {
            OnlineAPMModuleScreen(navigator)
        }
        composable(
            TabRoutes.INSTALL_APM,
            arguments = listOf(
                navArgument("uri") { type = NavType.StringType },
                navArgument("type") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val uri = Uri.parse(backStackEntry.arguments?.getString("uri"))
            val type = MODULE_TYPE.valueOf(backStackEntry.arguments?.getString("type") ?: "APM")
            InstallScreen(navigator, uri, type)
        }
        composable(
            TabRoutes.EXECUTE_APM_ACTION,
            arguments = listOf(navArgument("moduleId") { type = NavType.StringType })
        ) { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getString("moduleId") ?: ""
            ExecuteAPMActionScreen(navigator, moduleId)
        }
        composable(TabRoutes.INSTALL_MODE_SELECT) {
            InstallModeSelectScreen(navigator)
        }
        composable(TabRoutes.UNINSTALL_MODE_SELECT) {
            UninstallModeSelectScreen(navigator)
        }
        composable(
            TabRoutes.PATCHES,
            arguments = listOf(navArgument("mode") { type = NavType.IntType })
        ) { backStackEntry ->
            val modeOrdinal = backStackEntry.arguments?.getInt("mode") ?: 0
            val mode = PatchesViewModel.PatchMode.entries[modeOrdinal]
            Patches(mode)
        }
    }
}

@Composable
fun SettingsTabNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navigator = rememberTabNavigator(navController)

    NavHost(
        navController = navController,
        startDestination = TabRoutes.SETTINGS,
        modifier = modifier,
        enterTransition = { tabEnterTransition() },
        exitTransition = { tabExitTransition() },
        popEnterTransition = { tabPopEnterTransition() },
        popExitTransition = { tabPopExitTransition() },
    ) {
        composable(TabRoutes.SETTINGS) {
            SettingScreen(navigator)
        }
        composable(TabRoutes.NAVIGATION_LAYOUT) {
            NavigationLayoutScreen(navigator)
        }
        composable(TabRoutes.UMOUNT_CONFIG) {
            UmountConfigScreen(navigator)
        }
        composable(TabRoutes.ABOUT) {
            AboutScreen(navigator)
        }
    }
}
