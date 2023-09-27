package com.beepkart.tableau_android.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created on : September 26, 2023
 *
 * Author     : Sai Sukesh
 */
object TableauNetwork {

    private const val tableauURL = "https://prod-apnortheast-a.online.tableau.com/"

    private val okHttpClient by lazy {
        val okHttp = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)

        okHttp.build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(tableauURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val tableauService: TableauService by lazy {
        retrofit.create(TableauService::class.java)
    }
}