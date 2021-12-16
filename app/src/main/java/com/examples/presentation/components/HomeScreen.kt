package com.examples.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.examples.presentation.PawnItemCard
import com.examples.presentation.PawnOsListViewModel
import java.util.logging.Logger
import androidx.compose.material.Icon

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: PawnOsListViewModel = hiltViewModel()
) {
    Surface(
        elevation = 8.dp,
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SearchBar(
                hint = "Search",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                //viewModel.searchPokemonList(it)
            }
            PawnList(navController = navController)
        }
    }
}

@Composable
fun PawnList(
    navController: NavController,
    viewModel: PawnOsListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Log.d("Rj pi count", state.items.size.toString())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(width = 2.dp, color = Color.Green)
    ) {
        Text(
            text = "OUTSTANDING LIST",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.items) { pawnItem ->
                PawnItemCard(
                    pawnItem = pawnItem,
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

@Composable
fun SearchBarMitch(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
//    var text by remember {
//        mutableStateOf("")
//    }
//    var isHintDisplayed by remember {
//        mutableStateOf(hint != "")
//    }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(5.dp, CircleShape)
            .background(Color.White, CircleShape)
            .padding(8.dp)
            .onFocusChanged {
                //isHintDisplayed = it != FocusState.Active && text.isEmpty()
            },
        value = hint,
        label = { Text(text = hint) }, //TODO: Hardcoded string
        maxLines = 1,
        singleLine = true,
        onValueChange = { newValue ->
            onSearch(newValue)
//            text = it
//            onSearch(it)
        },
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) }
    )
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused && text.isEmpty()
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}