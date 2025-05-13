package com.savenko.mui8.data

data class Borrower(
    val credentials : Credentials,
    val debt : Debt
){
    data class Debt(val amount: Float, val currency: Currency)
    data class Credentials(val name: String, val surname: String)
}