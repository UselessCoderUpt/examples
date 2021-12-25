package com.examples.presentation.components

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.examples.R
import com.examples.domain.data.PawnItem
import com.examples.presentation.CustomerListViewModel
import com.examples.ui.theme.ExamplesTheme
import java.util.*

@ExperimentalAnimationApi
@Composable
fun CustomerListScreen(
    navController: NavController,
    viewModel: CustomerListViewModel = hiltViewModel()
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                //.shadow(5.dp)
                                //.padding(6.dp)
                                .focusRequester(focusRequester),
//                        .onFocusChanged {focusState ->
//                            when {
//                                focusState.isFocused ->
//                                    println("I'm focused!")
//                                focusState.hasFocus ->
//                                    println("A child of mine has focus!")
//
//                            }
//                            //isHintDisplayed = !it.isFocused && text.isEmpty()
//                           // focusManager.clearFocus()
//                        },
                            value = viewModel.query.value,
                            maxLines = 1,
                            singleLine = true,
                            onValueChange = {
                                viewModel.query.value = it
                                viewModel.newSearch(viewModel.query.value)
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Search
                            ),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    //viewModel.newSearch(viewModel.query.value)
                                    focusManager.clearFocus()
                                }
                            ),
                            label = { Text(text = stringResource(R.string.Search_Customer), color = Color.LightGray) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = null,
                                    tint = MaterialTheme.colors.onPrimary
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = null,
                                    tint = MaterialTheme.colors.onPrimary,
                                    modifier = Modifier
                                        .offset(x = 10.dp)
                                        .clickable {
                                            viewModel.query.value = "" //reset grid when close is clicked
                                            viewModel.newSearch(viewModel.query.value)
                                        }
                                )
                            },
                            //shape = RoundedCornerShape(14.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                //backgroundColor = MaterialTheme.colors.secondary
                            ),
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
            )
        },
    )
    {
        //val query = viewModel.query.value

        Surface(
            elevation = 8.dp,
            color = MaterialTheme.colors.surface,
            modifier = Modifier
                .fillMaxSize()
                // clear focus when clicked outside of search bar
                .pointerInput(Unit) {
                    detectTapGestures(onPress = {
                        focusManager.clearFocus()
                    })
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CustomerList(navController = navController)
            }
        }
    }
}

@Composable
fun CustomerList(
    navController: NavController,
    viewModel: CustomerListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(width = 2.dp, color = Color.Green)
//            .pointerInput(Unit) {
//                detectTapGestures(onTap = {
//                    focusManager.clearFocus()
//                })
//            }
    ) {
        val listState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(top = 5.dp, bottom = 5.dp, start = 5.dp, end = 5.dp)
        ) {
            items(state.items) { customer ->
                CustomerCard(
                    customer = customer,
                    onItemClick = {
                        //navController.navigate()
                        //redirect to details screen
                    }
                )
            }

            //scroll to top functionality

            //listState.firstVisibleItemIndex > 3
            /*FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .padding(37.dp)
                    .align(Alignment.BottomEnd)

            ) {
                Icon(
                    imageVector = Icons.Default.ExpandLess,
                    contentDescription = stringResource(R.string.scroll_to_top),
                )
            }*/
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

/*

@Preview(
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewItemCard() {
    ExamplesTheme {
        val pawnitem = PawnItem(
            1111,
            Date(),
            "Theerthamalaijhghjghjghfbvnvn",
            "Siruvathur",
            "9976865070",
            "Stud",
            "G",
            2.100,
            105000.00,
            225.00,
            5225.0,
            2
        )
        PawnItemCard(pawnItem = pawnitem, onItemClick = { })
    }
}

*/
