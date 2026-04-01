package me.bmax.apatch.ui.screen

import android.net.Uri
import androidx.compose.runtime.staticCompositionLocalOf

interface TabNavigator {
    fun navigate(route: String)
    fun popBackStack(): Boolean
    fun navigateUp(): Boolean
}

sealed class ExternalNavEvent {
    data class InstallApk(val uri: Uri) : ExternalNavEvent()
    data class ExecuteAction(val moduleId: String) : ExternalNavEvent()
}

val LocalExternalNavEvent = staticCompositionLocalOf<ExternalNavEvent?> { null }
