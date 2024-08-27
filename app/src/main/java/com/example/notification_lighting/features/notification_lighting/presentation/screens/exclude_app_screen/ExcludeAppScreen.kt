package com.example.notification_lighting.features.notification_lighting.presentation.screens.exclude_app_screen

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExcludeAppScreen(
    navController: NavController,
    excludeAppViewModel: ExcludeAppViewModel = hiltViewModel()
) {
    val state = excludeAppViewModel.state.value
    val context = LocalContext.current

    var excludedApps by remember {
        mutableStateOf(Json.decodeFromString<List<PackageModel>>(state.packages).toMutableList())
    }

    LaunchedEffect(state) {
        excludedApps = Json.decodeFromString<List<PackageModel>>(state.packages).toMutableList()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = { Text(text = "Exclude App") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (excludedApps.isNotEmpty() && excludedApps.first().packageName == "empty") {
                val apps = remember {
                    context.packageManager.getInstalledPackages(0)
                        .filter { installedPackage ->
                            installedPackage.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0
                        }
                        .map { installedPackage ->
                            val packageName =
                                installedPackage.applicationInfo.packageName.toString()
                            val appName =
                                installedPackage.applicationInfo.loadLabel(context.packageManager)
                                    .toString()
                            val isExcluded = excludedApps.any { it.packageName == packageName }
                            PackageModel(packageName, appName, isExcluded)
                        }
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    content = {
                        items(
                            items = apps,
                            key = { it.packageName }
                        ) { packageModel ->
                            // Update the exclusion state correctly
                            var isAppExcluded by remember { mutableStateOf(packageModel.isAppExcluded) }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .padding(vertical = 5.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.wrapContentSize()
                                    ) {
                                        Image(
                                            modifier = Modifier
                                                .width(48.dp)
                                                .height(48.dp)
                                                .padding(
                                                    top = 10.dp,
                                                    bottom = 10.dp,
                                                    end = 10.dp
                                                ),
                                            painter = rememberDrawablePainter(
                                                drawable = context.packageManager.getApplicationIcon(
                                                    packageModel.packageName
                                                )
                                            ),
                                            contentDescription = "app icon"
                                        )
                                        Text(
                                            text = packageModel.appName,
                                            style = MaterialTheme.typography.titleSmall
                                        )
                                    }
                                    Switch(
                                        modifier = Modifier.scale(scaleX = 0.6f, scaleY = 0.6f),
                                        checked = isAppExcluded,
                                        onCheckedChange = { checked ->
                                            isAppExcluded = checked
                                            val updatedApps = excludedApps.toMutableList()

                                            if (checked)
                                                updatedApps.add(packageModel)
                                            else
                                                updatedApps.removeAll { it.packageName == packageModel.packageName }

                                            excludedApps = updatedApps
                                            excludeAppViewModel.setExcludedPackages(
                                                Json.encodeToString(excludedApps)
                                            )
                                        }
                                    )
                                }
                                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                            }
                        }
                    }
                )
            }
        }
    }
}

@Serializable
data class PackageModel(
    val packageName: String,
    val appName: String,
    val isAppExcluded: Boolean
)