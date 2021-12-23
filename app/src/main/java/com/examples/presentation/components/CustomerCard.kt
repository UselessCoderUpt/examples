package com.examples.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.examples.domain.data.Customer

@Composable
fun CustomerCard(
    customer: Customer,
    onItemClick: (Customer) -> Unit
) {

    Log.d("Rj CARD", "Inside card")

    Card(
        elevation = 3.dp,
        modifier = Modifier
            .fillMaxSize()
            //.padding(start = 5.dp, end = 5.dp, top = 7.dp)
        // .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(30.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                // .clickable { onItemClick(customer) }
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Mobile No: ${customer.MobileNo}. Name:${customer.Name} ",
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Visible,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}