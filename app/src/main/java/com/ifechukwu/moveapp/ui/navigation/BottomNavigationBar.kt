package com.ifechukwu.moveapp.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import com.ifechukwu.moveapp.ui.theme.MoveAppTheme

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val shouldShowBottomNav = currentRoute in listOf(
        Screen.Home.route,
        Screen.Calculate.route,
        Screen.Shipment.route,
        Screen.Profile.route
    )

    AnimatedVisibility(shouldShowBottomNav) {
        NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
            navigationItems.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        if (currentRoute != item.route) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = if (currentRoute == item.route) MaterialTheme.colorScheme.primary else Color.Gray
                        )
                    },
                    label = {
                        Text(
                            item.title,
                            color = if (currentRoute == item.route) MaterialTheme.colorScheme.primary else Color.Gray,
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                    )
                )
            }
        }
    }
}

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

private val navigationItems = listOf(
    NavigationItem(
        title = "Home",
        icon = Icons.Outlined.Home,
        route = Screen.Home.route
    ),
    NavigationItem(
        title = "Calculate",
        icon = Icons.Outlined.Calculate,
        route = Screen.Calculate.route
    ),
    NavigationItem(
        title = "Shipment",
        icon = Icons.Outlined.History,
        route = Screen.Shipment.route
    ),
    NavigationItem(
        title = "Profile",
        icon = Icons.Outlined.Person,
        route = Screen.Profile.route
    )
)

@Preview
@Composable
fun BottomNavigationBarPreview() {
    MoveAppTheme {
        BottomNavigationBar(navController = NavController(LocalContext.current))
    }
}