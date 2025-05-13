package com.savenko.mui8.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.savenko.mui8.data.Borrower

@Composable
fun BorrowersList(dataSet: List<Borrower>, onBorrowerSelected: (Borrower) -> Unit) {
    val state = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp), state = state
    ) {
        items(count = dataSet.size) { itemIndex ->
            val currentItem = dataSet[itemIndex]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable { onBorrowerSelected(currentItem) }) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)) {
                    Text(
                        text = currentItem.credentials.name + " " + currentItem.credentials.surname,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    )
                    if(currentItem.debt.amount > 0.0f){
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${currentItem.debt.amount} ${currentItem.debt.currency.ticker}",
                            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary),
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                        )
                    }
                }

            }
        }
    }
}