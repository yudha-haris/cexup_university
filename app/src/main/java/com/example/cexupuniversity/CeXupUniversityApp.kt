package com.example.cexupuniversity

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cexupuniversity.ui.navigation.Screen
import com.example.cexupuniversity.ui.screens.detail.DetailScreen
import com.example.cexupuniversity.ui.screens.home.HomeScreen
import com.example.cexupuniversity.ui.theme.CexupUniversityTheme

@Composable
fun CeXupUniversityApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController
            )
        }
        composable(
            Screen.Detail.route,
            listOf(navArgument("courseId") { type = NavType.IntType })
        ) { args ->
            val id = args.arguments?.getInt("courseId", 0) ?: 0
            DetailScreen(id)
        }
    }
}