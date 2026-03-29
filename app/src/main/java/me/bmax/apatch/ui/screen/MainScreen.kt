package me.bmax.apatch.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import me.bmax.apatch.ui.theme.LocalMainPagerState
import me.bmax.apatch.ui.theme.LocalVisibleDestinations
import me.bmax.apatch.util.VisualConfig

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
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
        modifier = modifier.fillMaxSize()
    ) { page ->
        when (visibleDestinations.getOrNull(page)) {
            BottomBarDestination.Home -> HomeTabNavHost(Modifier.fillMaxSize())
            BottomBarDestination.KModule -> KModuleTabNavHost(Modifier.fillMaxSize())
            BottomBarDestination.SuperUser -> SuperUserScreen()
            BottomBarDestination.AModule -> AModuleTabNavHost(Modifier.fillMaxSize())
            BottomBarDestination.Settings -> SettingsTabNavHost(Modifier.fillMaxSize())
            null -> {}
        }
    }
}
