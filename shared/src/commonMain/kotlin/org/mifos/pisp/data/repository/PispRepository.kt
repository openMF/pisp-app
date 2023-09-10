package org.mifos.pisp.data.repository

import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import org.mifos.pisp.data.datasources.network.Amount
import org.mifos.pisp.data.datasources.network.CredentialRegistrationResponse
import org.mifos.pisp.data.datasources.network.LinkingAccountList
import org.mifos.pisp.data.datasources.network.LinkingApi
import org.mifos.pisp.data.datasources.network.LinkingAuthenticateResponse
import org.mifos.pisp.data.datasources.network.LinkingConsentResponse
import org.mifos.pisp.data.datasources.network.LinkingProviderSuccess
import org.mifos.pisp.data.datasources.network.Payee
import org.mifos.pisp.data.datasources.network.Payer
import org.mifos.pisp.data.datasources.network.TransactionType
import org.mifos.pisp.data.datasources.network.TransferApproveResponse
import org.mifos.pisp.data.datasources.network.TransferPartyLookupResponse
import org.mifos.pisp.data.datasources.network.TransferTransactionResponse
import org.mifos.pisp.data.datasources.network.TransfersApi
import org.mifos.pisp.data.fakesource.FakeNetworkDataSource

class PispRepository(
    private val httpClient: HttpClient,
    private val networkDataSource: FakeNetworkDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    // PISP Linking: Pre-linking
    suspend fun getDFSPList(): Flow<LinkingProviderSuccess> {
        return LinkingApi(httpClient).fetchDFSPList()
            .flowOn(ioDispatcher)
            .catch {
                networkDataSource.getDFSPList()
            }
    }

    // PISP Linking: Discovery
    suspend fun getUserAccountList(
        fspId: String,
        userId: String
    ): Flow<List<LinkingAccountList>> {
        return LinkingApi(httpClient).fetchUserAccountList(fspId, userId)
            .flowOn(ioDispatcher)
            .catch {
                networkDataSource.getUserAccountList()
            }
    }

    // PISP Linking: Request consent (OTP)
    suspend fun initiateConsentRequest(
        consentRequestId: String,
        toParticipantId: String,
        accounts: List<LinkingAccountList>,
        actions: List<String>,
        userId: String,
        callbackUri: String
    ): Flow<LinkingConsentResponse> {
        return LinkingApi(httpClient).createConsentRequest(
            consentRequestId,
            toParticipantId,
            accounts,
            actions,
            userId,
            callbackUri
        )
            .flowOn(ioDispatcher)
            .catch {
                networkDataSource.initiateConsentRequest()
            }
    }

    //  PISP Linking: Authentication (OTP)
    suspend fun authenticateWithOTP(
        consentRequestId: String,
        authToken: String
    ): Flow<LinkingAuthenticateResponse> {
        return LinkingApi(httpClient).initiateAuthenticationWithOTP(consentRequestId, authToken)
            .flowOn(ioDispatcher)
            .catch {
                networkDataSource.authenticateWithOTP()
            }
    }

    // PISP Linking: Credential registration (verification)
    suspend fun registerCredentialForConsent(
        consentRequestId: String,
        requestBody: String
    ): Flow<CredentialRegistrationResponse> {
        return LinkingApi(httpClient).createCredential(consentRequestId, requestBody)
            .flowOn(ioDispatcher)
            .catch {
                networkDataSource.registerCredentialForConsent()
            }
    }

    // PISP Transfers: Party Lookup
    suspend fun initiatePartyLookup(
        transactionRequestId: String,
        payee: Payee
    ): Flow<TransferPartyLookupResponse> {
        return TransfersApi(httpClient).fetchPartyInformation(transactionRequestId, payee)
            .flowOn(ioDispatcher)
            .catch {
                networkDataSource.initiatePartyLookup()
            }
    }

    // PISP Transfers: Initiate Transfer Transaction
    suspend fun initiateTransferTransaction(
        transactionRequestId: String,
        consentId: String,
        payee: Payee,
        payer: Payer,
        amountType: String,
        amount: Amount,
        transactionType: TransactionType,
        expiration: String
    ): Flow<TransferTransactionResponse> {
        return TransfersApi(httpClient).processTransferTransaction(
            transactionRequestId,
            consentId,
            payee,
            payer,
            amountType,
            amount,
            transactionType,
            expiration
        )
            .flowOn(ioDispatcher)
            .catch {
                networkDataSource.initiateTransferTransaction()
            }
    }

    // PISP Transfers: Approve Transfer Transaction
    suspend fun approveTransferTransaction(
        transactionRequestId: String,
        consentId: String,
        payee: Payee,
        payer: Payer,
        amountType: String,
        amount: Amount,
        transactionType: TransactionType,
        expiration: String
    ): Flow<TransferApproveResponse> {
        return TransfersApi(httpClient).processTransferApprove(
            transactionRequestId = transactionRequestId,
            consentId = consentId,
            payee = payee,
            payer = payer,
            amountType = amountType,
            amount = amount,
            transactionType = transactionType,
            expiration = expiration
        )
            .flowOn(ioDispatcher)
            .catch {
                networkDataSource.approveTransferTransaction()
            }
    }
}
