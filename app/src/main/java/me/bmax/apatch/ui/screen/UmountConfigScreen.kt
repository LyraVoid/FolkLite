package me.bmax.apatch.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import me.bmax.apatch.ui.screen.TabNavigator
import me.bmax.apatch.R
import me.bmax.apatch.ui.component.UmountConfig
import me.bmax.apatch.ui.component.UmountConfigManager
import top.yukonga.miuix.kmp.basic.Button
import top.yukonga.miuix.kmp.basic.ButtonDefaults
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.basic.TextField
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.extra.SuperDialog
import top.yukonga.miuix.kmp.extra.SuperSwitch
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.overScrollVertical

@Composable
fun UmountConfigScreen(navigator: TabNavigator) {
    val context = LocalContext.current
    var isEnabled by remember { mutableStateOf(UmountConfigManager.isEnabled.value) }
    var paths by remember { mutableStateOf(UmountConfigManager.paths.value) }
    val showSaveDialog = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val config = UmountConfigManager.loadConfig(context)
        isEnabled = config.enabled
        paths = config.paths
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = stringResource(R.string.umount_config_title),
                navigationIcon = {
                    IconButton(onClick = { navigator.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(android.R.string.cancel)
                        )
                    }
                }
            )
        },
        popupHost = {
            SuperDialog(
                title = stringResource(R.string.umount_config_save_confirm),
                show = showSaveDialog.value,
                onDismissRequest = { showSaveDialog.value = false }
            ) {
                Spacer(Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        text = stringResource(android.R.string.cancel),
                        onClick = { showSaveDialog.value = false },
                        modifier = Modifier.weight(1f),
                    )

                    Spacer(Modifier.width(20.dp))

                    TextButton(
                        text = stringResource(android.R.string.ok),
                        onClick = {
                            val config = UmountConfig(enabled = isEnabled, paths = paths)
                            val success = UmountConfigManager.saveConfig(context, config)
                            if (success) {
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.umount_config_save_success),
                                    Toast.LENGTH_SHORT
                                ).show()
                                navigator.navigateUp()
                            } else {
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.umount_config_save_failed),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            showSaveDialog.value = false
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.textButtonColorsPrimary(),
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .overScrollVertical(),
            contentPadding = PaddingValues(
                start = 10.dp,
                top = innerPadding.calculateTopPadding() + 16.dp,
                end = 10.dp,
                bottom = innerPadding.calculateBottomPadding() + 16.dp
            )
        ) {
            item {
                Card {
                    SuperSwitch(
                        title = stringResource(R.string.umount_config_enabled),
                        summary = stringResource(R.string.umount_config_enabled_summary),
                        checked = isEnabled,
                        onCheckedChange = {
                            isEnabled = it
                        }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Card {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.umount_config_paths_label),
                            style = MiuixTheme.textStyles.title1,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        TextField(
                            value = paths,
                            onValueChange = { input ->
                                paths = input
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = stringResource(R.string.umount_config_paths_label),
                            minLines = 5,
                            maxLines = 10
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = stringResource(R.string.umount_config_paths_helper),
                            style = MiuixTheme.textStyles.body2,
                            color = MiuixTheme.colorScheme.secondary
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Button(
                    onClick = {
                        showSaveDialog.value = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.umount_config_save))
                }
            }
        }
    }
}
