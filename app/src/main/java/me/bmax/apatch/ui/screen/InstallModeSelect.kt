package me.bmax.apatch.ui.screen

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.dropUnlessResumed
import me.bmax.apatch.ui.screen.TabNavigator
import me.bmax.apatch.R
import me.bmax.apatch.ui.component.rememberConfirmDialog
import me.bmax.apatch.ui.viewmodel.PatchesViewModel
import me.bmax.apatch.util.isABDevice
import me.bmax.apatch.util.rootAvailable
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.extra.SuperArrow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.remember

var selectedBootImage: Uri? = null

@Composable
fun InstallModeSelectScreen(navigator: TabNavigator) {

    Scaffold(
        modifier = Modifier.padding(16.dp),
        topBar = {
            TopBar(
                onBack = dropUnlessResumed { navigator.popBackStack() },
            )
        }, popupHost = {}
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Card {
                SelectInstallMethod(
                    navigator = navigator
                )
            }
        }
    }
}

sealed class InstallMethod {
    data class SelectFile(
        val uri: Uri? = null,
        @param:StringRes override val label: Int = R.string.mode_select_page_select_file,
    ) : InstallMethod()

    data object DirectInstall : InstallMethod() {
        override val label: Int
            get() = R.string.mode_select_page_patch_and_install
    }

    data object DirectInstallToInactiveSlot : InstallMethod() {
        override val label: Int
            get() = R.string.mode_select_page_install_inactive_slot
    }

    abstract val label: Int
    open val summary: String? = null
}

@Composable
private fun SelectInstallMethod(
    onSelected: (InstallMethod) -> Unit = {},
    navigator: TabNavigator,
) {
    val rootAvailable = rootAvailable()
    val isAbDevice = isABDevice()

    val radioOptions =
        mutableListOf<InstallMethod>(InstallMethod.SelectFile())
    if (rootAvailable) {
        radioOptions.add(InstallMethod.DirectInstall)
        if (isAbDevice) {
            radioOptions.add(InstallMethod.DirectInstallToInactiveSlot)
        }
    }

    val selectImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            it.data?.data?.let { uri ->
                val option = InstallMethod.SelectFile(uri)
                onSelected(option)
                selectedBootImage = option.uri
                navigator.navigate("patches/${PatchesViewModel.PatchMode.PATCH_ONLY.ordinal}")
            }
        }
    }

    val confirmDialog = rememberConfirmDialog(onConfirm = {
        onSelected(InstallMethod.DirectInstallToInactiveSlot)
        navigator.navigate("patches/${PatchesViewModel.PatchMode.INSTALL_TO_NEXT_SLOT.ordinal}")
    }, onDismiss = null)

    val dialogTitle = stringResource(id = android.R.string.dialog_alert_title)
    val dialogContent = stringResource(id = R.string.mode_select_page_install_inactive_slot_warning)

    val onClick = { option: InstallMethod ->
        when (option) {
            is InstallMethod.SelectFile -> {
                // Reset before selecting
                selectedBootImage = null
                selectImageLauncher.launch(
                    Intent(Intent.ACTION_GET_CONTENT).apply {
                        type = "application/octet-stream"
                    }
                )
            }

            is InstallMethod.DirectInstall -> {
                onSelected(option)
                navigator.navigate("patches/${PatchesViewModel.PatchMode.PATCH_AND_INSTALL.ordinal}")
            }

            is InstallMethod.DirectInstallToInactiveSlot -> {
                confirmDialog.showConfirm(dialogTitle, dialogContent, true)
            }
        }
    }

    Column {
        radioOptions.forEach { option ->
            SuperArrow(
                title = stringResource(id = option.label),
                summary = null,
                onClick = { onClick(option) }
            )
        }
    }
}

@Composable
private fun TopBar(onBack: () -> Unit = {}) {
    SmallTopAppBar(
        title = stringResource(R.string.mode_select_page_title),
        navigationIcon = {
            IconButton(
                onClick = onBack,
            ) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null) }
        },
    )
}