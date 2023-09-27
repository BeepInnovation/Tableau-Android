package com.beepkart.tableau_android.models

data class TableauAuth(val tsResponse: TsResponse)

data class TsResponse(val credentials: Credentials)

data class Credentials(
    val token: String,
    val estimatedTimeToExpiration: String,
    val site: Site,
    val user: User
)

data class Site(
    val id: String,
    val contentUrl: String
)

data class User(val id: String)