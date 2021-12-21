package com.examples.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.examples.presentation.CustomerCard
import com.examples.presentation.CustomerListViewModel

@Composable
fun CustomerScreen(
    navController: NavController,
    viewModel: CustomerListViewModel = hiltViewModel()
) {
    //val query = viewModel.query.value
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    Surface(
        elevation = 8.dp,
        color = MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxSize()
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
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        //.shadow(5.dp)
                        .padding(6.dp)
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
                    label = { Text(text = "Search") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary
                        )
                    },
                    shape = RoundedCornerShape(4.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.secondary
                    ),
                )
            }
            CustomerList(navController = navController)
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
        Text(
            text = "OUTSTANDING LIST",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.items) { customer ->
                CustomerCard(
                    customer = customer,
                    onItemClick = {
                        //navController.navigate()
                        //redirect to details screen
                    }
                )
            }
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
