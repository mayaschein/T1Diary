package com.example.t1diary.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.t1diary.R
import com.example.t1diary.ui.food.FoodDatabaseScreen
import com.example.t1diary.ui.home.HomeFab
import com.example.t1diary.ui.home.HomeScreen
import com.example.t1diary.ui.logbook.LogbookScreen
import com.example.t1diary.ui.navigation.Screen
import com.example.t1diary.ui.settings.SettingsScreen
import com.example.t1diary.ui.theme.T1DiaryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun T1DiaryApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screen.fromRoute(navBackStackEntry?.destination?.route)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(currentScreen.titleRes)) },
                actions = {
                    IconButton(onClick = { navController.navigateTo(Screen.Home) }) {
                        Icon(Icons.Default.Home, contentDescription = stringResource(R.string.action_home))
                    }
                    IconButton(onClick = { navController.navigateTo(Screen.Settings) }) {
                        Icon(Icons.Default.Settings, contentDescription = stringResource(R.string.action_settings))
                    }
                }
            )
        },
        floatingActionButton = {
            if (currentScreen == Screen.Home) {
                HomeFab()
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onLogbookClick = { navController.navigateTo(Screen.Logbook) },
                    onFoodDatabaseClick = { navController.navigateTo(Screen.FoodDatabase) },
                    onSettingsClick = { navController.navigateTo(Screen.Settings) }
                )
            }
            composable(Screen.Logbook.route) { LogbookScreen() }
            composable(Screen.FoodDatabase.route) { FoodDatabaseScreen() }
            composable(Screen.Settings.route) { SettingsScreen() }
        }
    }
}

private fun NavHostController.navigateTo(screen: Screen) {
    navigate(screen.route) {
        launchSingleTop = true
    }
}

@Preview(showBackground = true)
@Composable
fun T1DiaryAppPreview() {
    T1DiaryTheme {
        T1DiaryApp()
    }
}