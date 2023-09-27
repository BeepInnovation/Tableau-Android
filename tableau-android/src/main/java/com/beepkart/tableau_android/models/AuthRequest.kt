package com.beepkart.tableau_android.models

data class AuthRequest(
    val credentials: AuthCredentials
) {
    data class AuthCredentials(
        val personalAccessTokenName: String,
        val personalAccessTokenSecret: String,
        val site: AuthSite
    ) {
        data class AuthSite(
            val contentUrl: String
        )
    }
}
