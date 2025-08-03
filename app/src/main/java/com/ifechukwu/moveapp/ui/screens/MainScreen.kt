package com.ifechukwu.moveapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.ifechukwu.moveapp.ui.navigation.BottomNavigationBar
import com.ifechukwu.moveapp.ui.navigation.Screen

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->

        val graph =
            navController.createGraph(startDestination = Screen.Home.route) {
                composable(Screen.Home.route) {
                    HomeScreen {
                        navController.navigate(Screen.Search.route)
                    }
                }
                composable(Screen.Calculate.route) {
                    CalculateScreen {
                        navController.navigate(Screen.Status.route)
                    }
                }
                composable(Screen.Shipment.route) {
                    ShipmentScreen()
                }
                composable(Screen.Profile.route) {
                    ProfileScreen()
                }
                composable(Screen.Search.route) {
                    SearchScreen(
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }
                composable(Screen.Status.route) {
                    StatusScreen(
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        NavHost(
            navController = navController,
            graph = graph,
            modifier = Modifier.padding(innerPadding)
        )
    }
}