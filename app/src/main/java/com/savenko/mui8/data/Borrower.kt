package com.savenko.mui8.data

data class Borrower(
    val credentials : Credentials,
    val debt : Debt
){
    data class Debt(val amount: Float, val currency: Currency)
    data class Credentials(val name: String, val surname: String)

    fun isSamePerson(borrower: Borrower) : Boolean{
        return this.credentials == borrower.credentials
    }
}