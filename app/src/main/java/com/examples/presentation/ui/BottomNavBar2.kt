package com.examples.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.examples.common.Constants
import com.examples.common.Constants.navBarItems

@Composable
fun BottomNavBar2(
    modifier: Modifier = Modifier,
    iconSize: Dp = Constants.ICON_SIZE,
    selectedIconScale: Float = 1.5f
) {
    var selectedIndex by remember {
        mutableStateOf(0)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(iconSize.times(selectedIconScale))
    ) {
        for ((index, icon) in navBarItems.withIndex()) {
            AnimateIcon(
                imageVector = icon,
                scale = if (selectedIndex == index) 1.5f else 1.0f,
                color = if (selectedIndex == index) Constants.COLOR_SELECTED else Constants.COLOR_NORMAL,
                iconSize = Constants.ICON_SIZE,
               // onClick = {}
            ) {
                selectedIndex = index
            }
        }
    }
}

@Preview(group = "Main", name = "Bottom bar - animated")
@Composable
fun PreviewBottomNavBar2() {
    Surface {
        BottomNavBar2()
    }
}
