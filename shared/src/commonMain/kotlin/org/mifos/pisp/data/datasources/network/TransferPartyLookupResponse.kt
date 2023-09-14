package org.mifos.pisp.data.datasources.network

import kotlinx.serialization.Serializable

@Serializable
data class TransferPartyLookupResponse(
    val currentState: String,
    val party: Party
)

@Serializable
data class Party(
    val partyIdInfo: PartyIdInfo,
    val name: String
)

@Serializable
data class PartyIdInfo(
    val partyIdType: String,
    val partyIdentifier: String,
    val fspId: String
)

