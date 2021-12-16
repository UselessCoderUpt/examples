package com.examples.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.examples.presentation.PawnItemCard
import com.examples.presentation.PawnOsListViewModel

@Composable
fun PawnOsListScreen(
    navController: NavController,
    viewModel: PawnOsListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "OUTSTANDING LIST",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            Log.d("Rj LAZY", state.items.size.toString())
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