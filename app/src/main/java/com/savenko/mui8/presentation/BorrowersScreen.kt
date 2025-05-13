package com.savenko.mui8.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.savenko.mui8.data.Borrower
import com.savenko.mui8.data.Currency

@Composable
fun BorrowersScreen(
    paddingValues: PaddingValues,
    borrowersList : List<Borrower>,
    currenciesList : List<Currency>
) {
    var currentDataSet by remember {
        mutableStateOf(borrowersList)
    }
    var currentSelectedBorrower by remember { mutableStateOf<Borrower?>(null) }
    val selectedBorrowers = remember { mutableStateListOf<Borrower>() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "iBorrowManager",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.W500)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            TabItem(
                onClick = { currentDataSet = borrowersList },
                header = "Clients",
                isSelected = currentDataSet == borrowersList
            )
            TabItem(
                onClick =  { currentDataSet = selectedBorrowers },
                header = "Ongoing loans",
                isSelected = currentDataSet == selectedBorrowers
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        BorrowersList(dataSet = currentDataSet, onBorrowerSelected = { currentSelectedBorrower = it })
        if (currentSelectedBorrower != null) {
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        AnimatedVisibility(visible = currentSelectedBorrower != null) {
            Column(modifier = Modifier.fillMaxWidth()) {
                BorrowerDetails(currentSelectedBorrower!!)
                Spacer(modifier = Modifier.height(4.dp))

                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = { selectedBorrowers.add(currentSelectedBorrower!!) }) {
                    Text("Add loan")
                }
            }
        }
    }
}