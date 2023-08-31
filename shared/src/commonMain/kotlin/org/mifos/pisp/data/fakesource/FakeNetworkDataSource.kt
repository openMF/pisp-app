package org.mifos.pisp.data.fakesource

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.json.Json
import org.mifos.pisp.data.datasources.network.CredentialRegistrationResponse
import org.mifos.pisp.data.datasources.network.LinkingAccountList
import org.mifos.pisp.data.datasources.network.LinkingAuthenticateResponse
import org.mifos.pisp.data.datasources.network.LinkingConsentResponse
import org.mifos.pisp.data.datasources.network.LinkingProviderSuccess

/**
 * [FakeNetworkDataSource] implementation that provides static Mojaloop Thirdparty Outbound SDK API resources to aid development
 */
class FakeNetworkDataSource(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    // PISP Linking: Pre-linking
    suspend fun getDFSPList(): Flow<LinkingProviderSuccess> {
        return flow<LinkingProviderSuccess> {
            val jsonData = """
            {
              "providers": [
                "dfspa",
                "dfspb",
                "applebank"
              ],
              "currentState": "providersLookupSuccess"
            }
            """.trimIndent()

            emit(Json.decodeFromString(jsonData))
        }.flowOn(ioDispatcher)
    }

    // PISP Linking: Discovery
    suspend fun getUserAccountList(): Flow<List<LinkingAccountList>> {
        return flow<List<LinkingAccountList>> {
            val jsonData = """
        [
          {
            "accountNickname": "Chequing Account",
            "id": "dfspa.username.1234",
            "currency": "ZAR"
          },
          {
            "accountNickname": "Everyday Spend",
            "id": "dfspa.username.5678",
            "currency": "USD"
          }
        ]
        """.trimIndent()

            emit(Json.decodeFromString(jsonData))
        }.flowOn(ioDispatcher)
    }

    // PISP Linking: Request consent (OTP)
    suspend fun createConsentRequest(): Flow<LinkingConsentResponse> {
        return flow<LinkingConsentResponse> {
            val jsonData = """
        {
          "channelResponse": {
            "consentRequestId": "6ab43b0-71cc-49f9-b763-2ac3f05ac8c1",
            "scopes": [
              {
                "accountId": "dfspa.username.1234",
                "actions": [
                  "accounts.getBalance",
                  "accounts.transfer"
                ]
              },
              {
                "accountId": "dfspa.username.5678",
                "actions": [
                  "accounts.getBalance",
                  "accounts.transfer"
                ]
              }
            ],
            "authChannels": [
              "OTP"
            ],
            "callbackUri": "pisp-app://callback..."
          },
          "currentState": "OTPAuthenticationChannelResponseReceived"
        }
    """.trimIndent()

            emit(Json.decodeFromString(jsonData))
        }.flowOn(ioDispatcher)
    }

    // PISP Linking: Authentication (OTP)
    suspend fun authenticateWithOTP(): Flow<LinkingAuthenticateResponse> {
        return flow<LinkingAuthenticateResponse> {
            val jsonData = """
                {
                  "consentId": "22222222-0000-0000-0000-000000000000",
                  "consentRequestId": "11111111-0000-0000-0000-000000000000",
                  "status": "ISSUED",
                  "scopes": [
                    { "accountId": "dfsp.username.1234", "actions": [ "ACCOUNTS_TRANSFER" ] },
                    { "accountId": "dfsp.username.5678", "actions": [ "ACCOUNTS_TRANSFER" ] }
                  ]
                }
                """.trimIndent()
            emit(Json.decodeFromString(jsonData))
        }.flowOn(ioDispatcher)
    }


    // PISP Linking: Grant consent
    suspend fun registerCredentialForConsent(): Flow<CredentialRegistrationResponse> {
        return flow<CredentialRegistrationResponse> {
            val jsonData = """
    {
      "credential": {
        "status": "VERIFIED"
      },
      "currentState": "accountsLinked"
    }
    """.trimIndent()
            emit(Json.decodeFromString(jsonData))
        }.flowOn(ioDispatcher)
    }
}