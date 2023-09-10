package org.mifos.pisp.data.datasources.network

const val OUTBOUND_SERVER = "http://localhost:15000"

const val LINKING_PROVIDERS_PATH = "/linking/providers"
const val LINKING_REQUEST_CONSENT_PATH = "/linking/request-consent"

fun createLinkingAccountsPath(fspId: String, userId: String): String {
    return "/linking/accounts/$fspId/$userId"
}

fun createAuthenticationPath(consentRequestId: String): String {
    return "/linking/request-consent/$consentRequestId/authenticate"
}

fun createCredentialPath(consentRequestId: String): String {
    return "/linking/request-consent/$consentRequestId/pass-credential"
}

const val PARTY_LOOKUP_PATH = "/thirdpartyTransaction/partyLookup"

fun createTransferTransactionPath(transactionRequestId: String): String {
    return "/thirdpartyTransaction/$transactionRequestId/initiate"
}

fun createTransferApprovePath(transactionRequestId: String): String {
    return "/thirdpartyTransaction/$transactionRequestId/approve"
}