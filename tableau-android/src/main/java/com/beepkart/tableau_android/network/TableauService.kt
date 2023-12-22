package com.beepkart.tableau_android.network

import com.beepkart.tableau_android.models.AuthRequest
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface TableauService {

    @Headers("Content-Type: application/json")
    @POST("api/3.20/auth/signin")
    suspend fun tableauSignIn(@Body param: AuthRequest): ResponseBody

    @GET("api/3.20/sites/{site-id}/views/{view-id}/image?resolution=high&maxAge=60")
    suspend fun getDashboardImage(
        @Header("X-Tableau-Auth") token: String,
        @Path("site-id") siteId: String,
        @Path("view-id") viewId: String,
        @QueryMap filters: Map<String, String>
    ): ResponseBody

    @GET
    suspend fun generateToken(
        @Url url: String,
        @Header("authorization") authToken: String,
        @Header("appname") appName: String,
        @Header("userid") userId: String
    ): ResponseBody?
}