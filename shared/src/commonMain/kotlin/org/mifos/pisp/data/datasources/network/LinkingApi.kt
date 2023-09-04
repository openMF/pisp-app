package org.mifos.pisp.data.datasources.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.url
import io.ktor.util.InternalAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LinkingApi(private val httpClient: HttpClient) {

    suspend fun fetchDFSPList(): Flow<LinkingProviderSuccess> {
        return flow {
            try {
                val request = HttpRequestBuilder()
                request.url(OUTBOUND_SERVER + LINKING_PROVIDERS_PATH)

                val response = httpClient.get(request)
                val result = response.body<LinkingProviderSuccess>()
                emit(result)
            } catch (exp: ClientRequestException) {
                // TODO: handle error
            } catch (exp: Exception) {
                // TODO: handle error
            }
        }
    }

    suspend fun fetchUserAccountList(
        fspId: String,
        userId: String
    ): Flow<List<LinkingAccountList>> {
        return flow {
            try {
                val request = HttpRequestBuilder()
                request.url(OUTBOUND_SERVER + createLinkingAccountsPath(fspId, userId))

                val response = httpClient.get(request)
                val result = response.body<List<LinkingAccountList>>()
                emit(result)
            } catch (exp: ClientRequestException) {
                // TODO: handle error
            } catch (exp: Exception) {
                // TODO: handle error
            }
        }
    }

    @OptIn(InternalAPI::class)
    suspend fun createConsentRequest(
        consentRequestId: String,
        toParticipantId: String,
        accounts: List<LinkingAccountList>,
        actions: List<String>,
        userId: String,
        callbackUri: String
    ): Flow<LinkingConsentResponse> {
        return flow {
            val endpoint = OUTBOUND_SERVER + LINKING_REQUEST_CONSENT_PATH
            val requestBody = LinkingConsentRequestBody(
                toParticipantId = toParticipantId,
                consentRequestId = consentRequestId,
                accounts = accounts,
                actions = actions,
                userId = userId,
                callbackUri = callbackUri
            )
            val requestBuilder = HttpRequestBuilder()
            requestBuilder.url(endpoint)
            requestBuilder.header("Content-Type", "application/json")
            requestBuilder.body = requestBody

            try {
                val postRequest = httpClient.post(requestBuilder)
                val linkingConsentResponse = postRequest.body<LinkingConsentResponse>()
                emit(linkingConsentResponse)
            } catch (exp: ClientRequestException) {
                // TODO: handle error
            } catch (exp: Exception) {
                // TODO: handle error
            }
        }
    }

    @OptIn(InternalAPI::class)
    suspend fun initiateAuthenticationWithOTP(
        consentRequestId: String,
        authToken: String
    ): Flow<LinkingAuthenticateResponse> {
        return flow {
            val endpoint = OUTBOUND_SERVER + createAuthenticationPath(consentRequestId)
            val requestBody = LinkingAuthenticateRequestBody(
                authToken = authToken
            )
            val requestBuilder = HttpRequestBuilder()
            requestBuilder.url(endpoint)
            requestBuilder.header("Content-Type", "application/json")
            requestBuilder.body = requestBody

            try {
                val postRequest = httpClient.post(requestBuilder)
                val linkingAuthenticateResponse = postRequest.body<LinkingAuthenticateResponse>()
                emit(linkingAuthenticateResponse)
            } catch (exp: ClientRequestException) {
                // TODO: handle error
            } catch (exp: Exception) {
                // TODO: handle error
            }
        }
    }

    @OptIn(InternalAPI::class)
    suspend fun createCredential(
        consentRequestId: String,
        requestBody: String
    ): Flow<CredentialRegistrationResponse> {
        return flow {
            val endpoint = createCredentialPath(consentRequestId)

            val requestBuilder = HttpRequestBuilder()
            requestBuilder.url(endpoint)
            requestBuilder.header("Content-Type", "application/json")
            requestBuilder.body = requestBody

            try {
                val putRequest = httpClient.put(requestBuilder)
                val credentialRegistration = putRequest.body<CredentialRegistrationResponse>()
                emit(credentialRegistration)
            } catch (exp: ClientRequestException) {
                // TODO: handle error
            } catch (exp: Exception) {
                // TODO: handle error
            }
        }
    }
}

