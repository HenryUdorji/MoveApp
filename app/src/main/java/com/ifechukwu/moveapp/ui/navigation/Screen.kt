package com.ifechukwu.moveapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Calculate : Screen("calculate")
    object Shipment : Screen("shipment")
    object Profile : Screen("profile")
    object Search : Screen("search")
    object Status : Screen("status")
}