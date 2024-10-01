package com.modil.screentranslate.presentation.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher

class PermissionHandler(private val context: Context) {


    // Проверка разрешения для отображения поверх других окон
    fun isOverlayPermissionGranted(): Boolean {
        return Settings.canDrawOverlays(context)
    }

    // Запрос разрешения для отображения поверх других окон
    fun requestOverlayPermission(launcher: ActivityResultLauncher<Intent>) {
        if (!isOverlayPermissionGranted()) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:${context.packageName}")
            )
            launcher.launch(intent)
        }
    }
}