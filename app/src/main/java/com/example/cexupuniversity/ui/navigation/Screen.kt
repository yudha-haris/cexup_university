package com.example.cexupuniversity.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{courseId}"){
        fun createRoute(courseId: Int) = "detail/$courseId"
    }
}