package com.savenko.mui8.presentation

import android.widget.Toast
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
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.savenko.mui8.data.Borrower
import com.savenko.mui8.data.Currency

@Composable
fun BorrowersScreen(
    paddingValues: PaddingValues,
    borrowersList: List<Borrower>,
    currenciesList: List<Currency>
) {
    val localContext= LocalContext.current
    var currentDataSet by remember {
        mutableStateOf(borrowersList)
    }
    var currentSelectedBorrower by remember { mutableStateOf<Borrower?>(null) }
    val selectedBorrowers = remember { mutableStateListOf<Borrower>() }
    var borrowingAmount by remember { mutableStateOf(Borrower.Debt(amount = 0.0f, currency = currenciesList.first())) }
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
                onClick = { currentDataSet = selectedBorrowers },
                header = "Ongoing loans",
                isSelected = currentDataSet == selectedBorrowers
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        BorrowersList(
            dataSet = currentDataSet,
            onBorrowerSelected = { currentSelectedBorrower = it
            borrowingAmount = it.debt})
        if (currentSelectedBorrower != null && currentDataSet != selectedBorrowers) {
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        AnimatedVisibility(visible = currentSelectedBorrower != null && currentDataSet != selectedBorrowers) {
            Column(modifier = Modifier.fillMaxWidth()) {
                BorrowerDetails(modifier = Modifier.wrapContentSize().align(Alignment.CenterHorizontally),borrower = currentSelectedBorrower!!)
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    LoanInputField(currentValue = borrowingAmount.amount){
                        borrowingAmount = borrowingAmount.copy(amount = it.toULong().toFloat())
                    }
                    CurrencySelector(selectedCurrency = borrowingAmount.currency, onCurrencyChange = {
                        val currentCurrencyIndex = currenciesList.indexOf(borrowingAmount.currency)
                        borrowingAmount = borrowingAmount.copy(currency = currenciesList[(currentCurrencyIndex + 1) % currenciesList.size]) })
                }
                Spacer(modifier = Modifier.height(4.dp))
                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        if(borrowingAmount.amount <= 0){
                            Toast.makeText(localContext, "Amount should be greater than 0", Toast.LENGTH_SHORT).show()
                        }
                        val newBorrower = currentSelectedBorrower?.copy(debt = borrowingAmount)
                        if(selectedBorrowers.any{ it.isSamePerson(newBorrower!!) }){
                            selectedBorrowers.remove(selectedBorrowers.find{ it.isSamePerson(newBorrower!!) })
                        }
                        selectedBorrowers.add(currentSelectedBorrower?.copy(debt = borrowingAmount)!!) }) {
                    Text("Add loan")
                }
            }

        }
    }
}