package org.mifos.pisp.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.put
import io.ktor.client.request.url
import io.ktor.util.InternalAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.mifos.pisp.data.datasources.network.CredentialRegistrationResponse
import org.mifos.pisp.data.datasources.network.LinkingAccountList
import org.mifos.pisp.data.datasources.network.LinkingAuthenticateResponse
import org.mifos.pisp.data.datasources.network.LinkingConsentResponse
import org.mifos.pisp.data.datasources.network.LinkingProviderSuccess
import org.mifos.pisp.data.fakesource.FakeNetworkDataSource

class PispRepository(
    private val httpClient: HttpClient,
    private val networkDataSource: FakeNetworkDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    // PISP Linking: Pre-linking
    suspend fun getDFSPList(): Flow<LinkingProviderSuccess> {
        return networkDataSource.getDFSPList().flowOn(ioDispatcher)
    }

    // PISP Linking: Discovery
    suspend fun getUserAccountList(fspId: String, userId: String): Flow<List<LinkingAccountList>> {
        return networkDataSource.getUserAccountList().flowOn(ioDispatcher)
    }

    // PISP Linking: Request consent (OTP)
    suspend fun createConsentRequest(
        consentRequestId: String,
        toParticipantId: String,
        accounts: List<LinkingAccountList>,
        actions: List<String>,
        userId: String,
        callbackUri: String
    ): Flow<LinkingConsentResponse> {
        return networkDataSource.createConsentRequest().flowOn(ioDispatcher)
    }

    //  PISP Linking: Authentication (OTP)
    suspend fun authenticateWithOTP(
        consentRequestId: String,
        authToken: String
    ): Flow<LinkingAuthenticateResponse> {
        return networkDataSource.authenticateWithOTP().flowOn(ioDispatcher)
    }

    // PISP Linking: Credential registration (verification)
    @OptIn(InternalAPI::class)
    suspend fun registerCredentialForConsent(
        consentId: String,
        requestBody: String
    ): Flow<CredentialRegistrationResponse> {
        val endpoint = "https://api.example.com/consents/$consentId"

        val requestBuilder = HttpRequestBuilder()
        requestBuilder.url(endpoint)
        requestBuilder.header("Content-Type", "application/json")
        requestBuilder.body = requestBody

        val putRequest = httpClient.put(requestBuilder)

        return networkDataSource.registerCredentialForConsent().flowOn(ioDispatcher)
    }
}
