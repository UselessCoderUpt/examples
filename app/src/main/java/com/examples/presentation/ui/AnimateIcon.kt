package com.examples.presentation.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import com.examples.common.Constants
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp

@Composable
fun AnimateIcon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    iconSize: Dp = Constants.ICON_SIZE,
    scale: Float = 1f,
    color: Color = Constants.COLOR_NORMAL,
    onClick: () -> Unit
) {
    // Animation params
    val animatedScale: Float by animateFloatAsState(
        targetValue = scale,
        // Here the animation spec serves no purpose but to demonstrate in slow speed.
        animationSpec = TweenSpec(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        )
    )
    val animatedColor by animateColorAsState(
        targetValue = color,
        animationSpec = TweenSpec(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        )
    )

    IconButton(
        onClick = onClick,
        modifier = modifier.size(iconSize)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "dummy",
            tint = animatedColor,
            modifier = modifier.scale(animatedScale)
        )
    }
}


@Preview(group = "Icon")
@Composable
fun PreviewIcon() {
    Surface {

        var selected by remember {
            mutableStateOf(false)
        }

        AnimateIcon(
            imageVector = Icons.Outlined.FavoriteBorder,
            scale = if (selected) 1.5f else 1f,
            color = if (selected) Constants.COLOR_SELECTED else Constants.COLOR_NORMAL,
        ) {
            selected = !selected
        }
    }
}
