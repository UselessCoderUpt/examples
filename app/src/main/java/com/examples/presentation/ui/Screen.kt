package com.examples.presentation.ui

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object CustomerScreen: Screen("customer_list_screen")
    object PawnOsListScreen: Screen("pawn_os_list_screen")
    object TodaysRenewalScreen: Screen("pawn_todays_renewal_screen")
}
