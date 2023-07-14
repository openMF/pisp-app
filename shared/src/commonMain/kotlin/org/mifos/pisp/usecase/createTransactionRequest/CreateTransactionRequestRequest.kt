package org.mifos.pisp.usecase.createTransactionRequest

import org.mifos.pisp.usecase.base.BaseRequest

class CreateTransactionRequestRequest(
    val token: String,
    val sourceBankId: String,
    val sourceAccountId: String,
    val destinationBankId: String,
    val destinationAccountId: String,
    val currency: String,
    val amount: Double,
    val description: String
) :
    BaseRequest {

    override fun validate(): Boolean {
        return true
    }

}