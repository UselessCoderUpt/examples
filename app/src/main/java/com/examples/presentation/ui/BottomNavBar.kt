package com.examples.presentation.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.examples.common.Constants
import com.examples.common.Constants.navBarItems

@Composable
fun BottomNavBar(
    navController: NavController
) {
    val iconSize: Dp = Constants.ICON_SIZE
    val selectedIconScale: Float = 1.5f
    //val navController = rememberNavController()
    var selected by remember {
        mutableStateOf(false)
    }
    var selectedIndex by remember { mutableStateOf(0) }
    val navDestinations = listOf(
        BottomNavDestination.HomeScreen,
        BottomNavDestination.CustomerScreen,
        BottomNavDestination.PawnOsListScreen,
        BottomNavDestination.TodaysRenewalScreen
    )

    //var SCALE = if (selected) 1.5f else 1f
    //var COLOR = if (selected) Constants.COLOR_SELECTED else Constants.COLOR_NORMAL

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(iconSize.times(selectedIconScale))
    ) {
       // val backStackEntry by navController.currentBackStackEntryAsState()
       // val currentDestination = backStackEntry?.destination
        var index = 0
        navDestinations.forEach{ screen ->

            // Animation params
            val animatedScale: Float by animateFloatAsState(
                targetValue = if (selected) 1.5f else 1.0f,
                // Here the animation spec serves no purpose but to demonstrate in slow speed.
                animationSpec = TweenSpec(
                    durationMillis = 1000,
                    easing = FastOutSlowInEasing
                )
            )
            val animatedColor by animateColorAsState(
                targetValue = if (selected) Constants.COLOR_SELECTED else Constants.COLOR_NORMAL,
                animationSpec = TweenSpec(
                    durationMillis = 1000,
                    easing = FastOutSlowInEasing
                )
            )

            IconButton(
                modifier = Modifier.size(Constants.ICON_SIZE),
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
                        selected = !selected
                    }
                }
            ) {
                Icon(
                    imageVector = screen.icon,
                    contentDescription = "dummy",
                    tint = animatedColor,
                    modifier = Modifier.scale(animatedScale)
                )
                selectedIndex = index
                index++
            }
        }
    }
}

