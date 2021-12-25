package com.examples.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.examples.R

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.geetha_jewellery),
                        color = MaterialTheme.colors.onPrimary,
                        //fontStyle = MaterialTheme.typography.h2
                    )
                },
                backgroundColor = MaterialTheme.colors.primary,
            )
        }) {
        Card(
            elevation = 6.dp,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)
            // .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(30.dp))
        ) {
            ConstraintLayout(modifier = Modifier.verticalScroll(rememberScrollState())) {
                val (image, nameText, placeText, rowStats) = createRefs()
                val guideline = createGuidelineFromTop(0.05f)
                Image(
                    painter = painterResource(id = R.drawable.geetha),
                    contentDescription = "Geetha",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(2.dp, color = Color.Blue, shape = CircleShape)
                        .constrainAs(image) {
                            //top.linkTo(parent.top)
                            top.linkTo(guideline)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            //centerVerticallyTo(parent)
                        },
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = stringResource(id = R.string.geetha_jewellery),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    modifier = Modifier.constrainAs(nameText) {
                        top.linkTo(image.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.wrapContent
                    }
                )
                Text(
                    text = "Ulundurpet",
                    modifier = Modifier.constrainAs(placeText) {
                        top.linkTo(nameText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .constrainAs(rowStats) {
                            top.linkTo(placeText.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ProfileStats("10", "pledges")
                    ProfileStats("2", "renewals")
                    ProfileStats("0", "outstanding")
                }
            }
        }
    }
}

@Composable
fun ProfileStats(count: String, title: String) {
    Column(
//        modifier = Modifier
//            .border(2.dp, color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$count", fontWeight = FontWeight.Bold)
        Text(text = "$title")
    }

}