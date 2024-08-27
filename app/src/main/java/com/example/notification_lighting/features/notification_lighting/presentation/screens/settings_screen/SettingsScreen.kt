package com.example.notification_lighting.features.notification_lighting.presentation.screens.settings_screen

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.notification_lighting.core.navigation.NavigationGraph

@Composable
@ExperimentalMaterial3Api
fun SettingsScreen(navController: NavController) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(text = "Settings") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            NotificationAndOverlayPermissionCard()
            NotificationLightingSettingsCard(navController = navController)
            AppsExcludedCard(navController = navController)
        }
    }
}


@Composable
fun NotificationAndOverlayPermissionCard() {
    val context = LocalContext.current

    fun startListenersSettings() {
        context.startActivity(
            Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    fun isNotificationServiceEnabled(): Boolean {
        val listeners = Settings.Secure.getString(
            context.contentResolver,
            "enabled_notification_listeners"
        )
        return listeners.contains(context.packageName)
    }

    fun startOverlaySetting() {
        context.startActivity(
            Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:${context.packageName}")
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    fun isOverlayPermissionEnabled(): Boolean {
        return Settings.canDrawOverlays(context)
    }

    var isNotificationServiceEnabled by remember {
        mutableStateOf(isNotificationServiceEnabled())
    }
    var isOverlayEnabled by remember {
        mutableStateOf(isOverlayPermissionEnabled())
    }

    LifecycleEventEffect(event = Lifecycle.Event.ON_START) {
        isNotificationServiceEnabled = isNotificationServiceEnabled()
        isOverlayEnabled = isOverlayPermissionEnabled()
    }
    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        isNotificationServiceEnabled = isNotificationServiceEnabled()
        isOverlayEnabled = isOverlayPermissionEnabled()
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Notification Access",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Necessary for notification lighting",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Switch(
                    checked = isNotificationServiceEnabled,
                    onCheckedChange = {
                        startListenersSettings()
                    }
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Appear On Top",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Necessary for notification lighting",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Switch(
                    checked = isOverlayEnabled,
                    onCheckedChange = {
                        startOverlaySetting()
                    }
                )
            }
        }
    }
}

@Composable
fun NotificationLightingSettingsCard(navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
        onClick = {
            navController.navigate(NavigationGraph.ScreenNotificationLightingSettings)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Notification Lighting Settings",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Customize notification lighting screen",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
fun AppsExcludedCard(navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
        onClick = {
            navController.navigate(NavigationGraph.ScreenExcludeApp)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Exclude app from Notification Lighting",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Enable or disable notification lighting for a specific app",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "icon"
            )
        }
    }
}
