package com.example.t1diary.ui.navigation

import androidx.annotation.StringRes
import com.example.t1diary.R

/**
 * All top-level destinations in the app.
 * [route] is the key used by the NavHost, [titleRes] is shown in the top bar.
 */
enum class Screen(val route: String, @StringRes val titleRes: Int) {
    Home("home", R.string.app_name),
    Logbook("logbook", R.string.title_logbook),
    FoodDatabase("food_database", R.string.title_food_database),
    Settings("settings", R.string.title_settings);

    companion object {
        /** Returns the screen for a route, or [Home] if the route is unknown or null. */
        fun fromRoute(route: String?): Screen =
            entries.find { it.route == route } ?: Home
    }
}