package me.bmax.apatch.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.bmax.apatch.ui.theme.LocalMainPagerState
import me.bmax.apatch.ui.theme.LocalVisibleDestinations
import me.bmax.apatch.util.VisualConfig

@Destination<RootGraph>(start = true)
@Composable
fun MainScreen(navigator: DestinationsNavigator) {
    val mainPagerState = LocalMainPagerState.current ?: return
    val visibleDestinations = LocalVisibleDestinations.current

    LaunchedEffect(mainPagerState.pagerState.currentPage) {
        mainPagerState.syncPage()
    }

    val hasBackStack = mainPagerState.pagerState.currentPage > 0
    BackHandler(enabled = hasBackStack && VisualConfig.predictiveBackGesture) {
        mainPagerState.navigateBack()
    }

    HorizontalPager(
        state = mainPagerState.pagerState,
        beyondViewportPageCount = 4,
        userScrollEnabled = true,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when (visibleDestinations.getOrNull(page)) {
            BottomBarDestination.Home -> HomeScreen(navigator)
            BottomBarDestination.KModule -> KPModuleScreen(navigator)
            BottomBarDestination.SuperUser -> SuperUserScreen()
            BottomBarDestination.AModule -> APModuleScreen(navigator)
            BottomBarDestination.Settings -> SettingScreen(navigator)
            null -> {}
        }
    }
}
