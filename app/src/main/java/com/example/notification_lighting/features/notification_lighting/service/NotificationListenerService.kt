package com.example.notification_lighting.features.notification_lighting.service

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.PowerManager
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.example.notification_lighting.features.notification_lighting.data.sources.local.data_store.LightingDataStoreManager
import com.example.notification_lighting.features.notification_lighting.domain.model.PackageModel
import com.example.notification_lighting.features.notification_lighting.presentation.screens.lighting_screen.NotificationLightingActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class NotificationListenerService : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        CoroutineScope(Dispatchers.Main).launch {
            LightingDataStoreManager(baseContext).getExcludedPackages().collect { packages ->
                val excludedPackages = Json.decodeFromString<List<PackageModel>>(packages)
                val isExcluded = excludedPackages.any { it.packageName == sbn!!.packageName }
                if (!isExcluded && !isScreenOn() && !sbn!!.isOngoing) lockScreenLightning(sbn.packageName)
            }
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
    }

    private fun isScreenOn(): Boolean {
        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        return powerManager.isInteractive
    }

    private fun lockScreenLightning(packageName: String) {
        val intent = Intent(this, NotificationLightingActivity::class.java).apply {
            putExtra(PACKAGE_NAME, packageName)
            addFlags(FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            addFlags(FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }

    companion object {
        const val PACKAGE_NAME = "PACKAGE_NAME"
    }
}