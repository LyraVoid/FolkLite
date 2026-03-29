package me.bmax.apatch.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.dropUnlessResumed
import me.bmax.apatch.ui.screen.TabNavigator
import me.bmax.apatch.BuildConfig
import me.bmax.apatch.R
import me.bmax.apatch.util.Version
import top.yukonga.miuix.kmp.basic.Button
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.basic.Surface
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun AboutScreen(navigator: TabNavigator) {
    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            TopBar(onBack = dropUnlessResumed { navigator.popBackStack() })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Surface(
                modifier = Modifier.size(95.dp),
                color = colorResource(id = R.color.ic_launcher_folkpatch_background),
                shape = CircleShape
            ) {
                Image(
                    painter = painterResource(id = R.drawable.about),
                    contentDescription = stringResource(R.string.app_name),
                    modifier = Modifier.scale(1.15f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.app_name),
                style = MiuixTheme.textStyles.title4
            )
            Text(
                text = stringResource(
                    id = R.string.about_app_version,
                    if (BuildConfig.VERSION_NAME.contains(BuildConfig.VERSION_CODE.toString())) "${BuildConfig.VERSION_CODE}" else "${BuildConfig.VERSION_CODE} (${BuildConfig.VERSION_NAME})"
                ),
                style = MiuixTheme.textStyles.body2,
                color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                modifier = Modifier.padding(top = 5.dp)
            )
            Text(
                text = stringResource(
                    id = R.string.about_powered_by,
                    "KernelPatch (${Version.buildKPVString()})"
                ),
                style = MiuixTheme.textStyles.body2,
                color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                modifier = Modifier.padding(top = 5.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { uriHandler.openUri("https://github.com/matsuzaka-yuki/FolkLite") }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.github),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(id = R.string.about_github))
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    onClick = { uriHandler.openUri("https://t.me/FolkPatch") }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.telegram),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(id = R.string.about_telegram_channel))
                }
            }

            Card(
                modifier = Modifier.padding(vertical = 30.dp, horizontal = 20.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 12.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.about_app_desc),
                        style = MiuixTheme.textStyles.body2,
                        color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                    )
                }
            }

        }
    }
}

@Composable
private fun TopBar(onBack: () -> Unit = {}) {
    SmallTopAppBar(
        title = stringResource(R.string.about),
        navigationIcon = {
            IconButton(
                onClick = onBack
            ) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null) }
        },
    )
}
