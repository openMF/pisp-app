package org.mifos.openbanking.data.datasources.network

class NetworkDataSource(
    private val clientApi: ClientApi = ClientApi(),
    private val banksApi: BanksApi = BanksApi(),
    private val transactionApi: TransactionApi = TransactionApi(),
    private val cardApi: CardApi = CardApi()
) {

    fun getClientApi(): ClientApi {
        return clientApi
    }

    fun getBankApi(): BanksApi {
        return banksApi
    }

    fun getTransactionApi(): TransactionApi {
        return transactionApi
    }

    fun getCardApi(): CardApi {
        return cardApi
    }

}
