package com.example.androiddevchallenge.utils

import android.app.Activity
import android.graphics.Color
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat

/**
 * <pre>
 *     author : junwuming
 *     e-mail : zjc986812982@163.com
 *     time   : 2022/08/16
 *     desc   :
 * </pre>
 */
object EdgeToEdgeUtils {
    /**
     * Set Edge to Edge, and configure the light/dark navigation bar mode
     * @param isLight set system bar content light or not
     * */
    fun Activity.setEdgeWithLightTheme(isLight: Boolean) {
        this.window?.let { window ->
            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor = Color.TRANSPARENT
            ViewCompat.getWindowInsetsController(window.decorView)?.let { it ->
                it.isAppearanceLightStatusBars = isLight
                it.isAppearanceLightNavigationBars = isLight
            }
        }
    }

    fun setActivityEdgeToEdge(activity: Activity) {
        activity.window?.let { window ->
            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor = Color.TRANSPARENT
        }
    }

    fun setSystemBarLight(activity: Activity, isLight: Boolean) {
        ViewCompat.getWindowInsetsController(activity.window.decorView)?.let { it ->
            it.isAppearanceLightStatusBars = isLight
            it.isAppearanceLightNavigationBars = isLight
        }
    }

}