package me.bmax.apatch.util

import android.content.Context
import android.content.res.Configuration
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.edit
import me.bmax.apatch.APApplication

object DPIUtils {
    private const val KEY_APP_DPI = "app_dpi"
    private const val DEFAULT_DPI = -1 // Follow System

    var currentDpi: Int by mutableIntStateOf(DEFAULT_DPI)
        private set

    fun load(context: Context) {
        val prefs = APApplication.sharedPreferences
        currentDpi = prefs.getInt(KEY_APP_DPI, DEFAULT_DPI)
    }

    fun setDpi(context: Context, dpi: Int) {
        val prefs = APApplication.sharedPreferences
        prefs.edit { putInt(KEY_APP_DPI, dpi) }
        currentDpi = dpi
        // Note: To apply the change immediately, the activity usually needs to be recreated.
        // We handle the configuration update in MainActivity.
    }

    fun applyDpi(context: Context) {
        if (currentDpi == DEFAULT_DPI) return

        val res = context.resources
        val config = res.configuration
        val metrics = res.displayMetrics

        if (config.densityDpi != currentDpi) {
            config.densityDpi = currentDpi
            metrics.densityDpi = currentDpi
            // This API is deprecated but kept for compatibility with already-created resources.
            @Suppress("DEPRECATION")
            res.updateConfiguration(config, metrics)
        }
    }

    // Helper for attachBaseContext
    fun updateContext(context: Context): Context {
        val prefs = APApplication.sharedPreferences
        val dpi = prefs.getInt(KEY_APP_DPI, DEFAULT_DPI)

        if (dpi == DEFAULT_DPI) return context

        val config = Configuration(context.resources.configuration)
        config.densityDpi = dpi
        return context.createConfigurationContext(config)
    }

    val presets = listOf(
        DpiPreset("System Default", -1),
        DpiPreset("Small (320)", 320),
        DpiPreset("Medium (400)", 400),
        DpiPreset("Large (480)", 480),
        DpiPreset("XLarge (560)", 560)
    )
}

data class DpiPreset(val name: String, val value: Int)
