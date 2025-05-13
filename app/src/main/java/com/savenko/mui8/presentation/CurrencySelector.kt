package com.savenko.mui8.presentation

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.savenko.mui8.data.Currency


@Composable
fun CurrencySelector(selectedCurrency : Currency, onCurrencyChange : () -> Unit){
    TextButton(onClick = { onCurrencyChange() }, modifier = Modifier.wrapContentWidth()) {
        Text(text = selectedCurrency.ticker, style = MaterialTheme.typography.bodyMedium)
    }
}