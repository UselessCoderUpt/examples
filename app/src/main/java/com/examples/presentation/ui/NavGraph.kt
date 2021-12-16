package com.examples.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.examples.presentation.components.CustomerScreen
import com.examples.presentation.components.HomeScreen
import com.examples.presentation.components.PawnOsListScreen
import com.examples.presentation.components.Screen

@Composable
fun NavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.CustomerScreen.route
    ){
        composable(route = Screen.CustomerScreen.route){
            CustomerScreen(navController = navController)
        }
        composable(route = Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(route = Screen.TodaysRenewalScreen.route){
            PawnTodaysRenewalScreen(navController = navController)
        }
    }
}