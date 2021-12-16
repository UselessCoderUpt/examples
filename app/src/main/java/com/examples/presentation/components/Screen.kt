package com.examples.presentation.components

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object CustomerScreen: Screen("customer_screen")
    object PawnOsListScreen: Screen("pawn_os_list_screen")
    object TodaysRenewalScreen: Screen("pawn_todays_renewal_screen")
}
