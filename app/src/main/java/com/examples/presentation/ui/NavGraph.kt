package com.examples.presentation.ui


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.examples.presentation.components.CustomerListScreen
import com.examples.presentation.components.HomeScreen
import com.examples.presentation.components.PawnOsListScreen
import com.examples.presentation.components.PawnTodaysRenewalScreen
import com.examples.R


@ExperimentalAnimationApi
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val navDestinations = listOf(
        BottomNavDestination.HomeScreen,
        BottomNavDestination.CustomerScreen,
        BottomNavDestination.PawnOsListScreen,
        BottomNavDestination.TodaysRenewalScreen
    )
    Scaffold(

        bottomBar = {
            BottomNavigation {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = backStackEntry?.destination
                navDestinations.forEach() { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(stringResource(screen.label)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        })
                }
//            BottomNavBar2()
            }
        }
    ) { innerPadding ->


            NavHost(
                navController = navController,
                startDestination = BottomNavDestination.HomeScreen.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = BottomNavDestination.HomeScreen.route) {
                    HomeScreen(navController = navController)
                }
                composable(route = BottomNavDestination.CustomerScreen.route) {
                    CustomerListScreen(navController = navController)
                }
                composable(route = BottomNavDestination.PawnOsListScreen.route) {
                    PawnOsListScreen(navController = navController)
                }
                composable(route = BottomNavDestination.TodaysRenewalScreen.route) {
                    PawnTodaysRenewalScreen(navController = navController)
                }
            }





    }
}