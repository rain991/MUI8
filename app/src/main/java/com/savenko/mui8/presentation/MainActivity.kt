package com.savenko.mui8.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.savenko.mui8.data.borrowersList
import com.savenko.mui8.data.currenciesList
import com.savenko.mui8.presentation.ui.theme.MUI8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MUI8Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BorrowersScreen(
                        paddingValues = innerPadding,
                        borrowersList = borrowersList,
                        currenciesList = currenciesList
                    )
                }
            }
        }
    }
}
