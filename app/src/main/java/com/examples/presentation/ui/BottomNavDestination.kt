package com.examples.presentation.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Summarize
import androidx.compose.material.icons.outlined.Today
import androidx.compose.ui.graphics.vector.ImageVector
import com.examples.R

sealed class BottomNavDestination(val route: String, val icon: ImageVector, @StringRes val label: Int){
    object HomeScreen: BottomNavDestination("home_screen", Icons.Outlined.Home, R.string.Home)
    object CustomerScreen: BottomNavDestination("customer_screen", Icons.Outlined.People, R.string.Customer)
    object PawnOsListScreen: BottomNavDestination("pawn_os_list_screen", Icons.Outlined.Summarize, R.string.Pledges)
    object TodaysRenewalScreen: BottomNavDestination("pawn_todays_renewal_screen", Icons.Outlined.Today, R.string.TodaysOsPledges)
}
