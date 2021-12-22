package com.examples.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object Constants {
    const val BASE_URL = "https://script.google.com/macros/s/"
    const val TAG = "Rj"
     val navBarItems = arrayOf(
         Icons.Outlined.Home,
         Icons.Outlined.People,
        Icons.Outlined.Summarize,
        Icons.Outlined.Today,
    )
     val COLOR_NORMAL = Color(0xffEDEFF4)
     val COLOR_SELECTED = Color(0xff496DE2)
     val ICON_SIZE = 24.dp
}