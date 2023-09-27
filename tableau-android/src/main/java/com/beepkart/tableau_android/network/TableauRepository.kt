package com.beepkart.tableau_android.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.beepkart.tableau_android.models.AuthRequest
import com.beepkart.tableau_android.models.Credentials
import com.beepkart.tableau_android.models.TableauAuth
import com.google.gson.Gson
import fr.arnaudguyon.xmltojsonlib.XmlToJson

/**
 * Created on : September 26, 2023
 *
 * Author     : Sai Sukesh
 */
class TableauRepository {

    private val tableauService = TableauNetwork.tableauService

    suspend fun tableauSignIn(tokenName: String, tokenSecret: String, siteUrl: String): Credentials? {
        try {
            val response = tableauService.tableauSignIn(
                AuthRequest(
                    AuthRequest.AuthCredentials(
                        tokenName, tokenSecret, AuthRequest.AuthCredentials.AuthSite(siteUrl)
                    )
                )
            )

            val xmlToJson = XmlToJson.Builder(response.string()).build()
            val jsonObject = xmlToJson.toJson()
            val tableauAuth = Gson().fromJson(jsonObject.toString(), TableauAuth::class.java)

            return tableauAuth.tsResponse.credentials
        } catch (e: Exception) {
            return null
        }
    }

    suspend fun getTableauImage(authToken: String, siteId: String, viewId: String, filters: Map<String, String>): Bitmap? {
        try {
            val response = tableauService.getDashboardImage(authToken, siteId, viewId, filters)
            return BitmapFactory.decodeStream(response.byteStream())
        } catch (e: Exception) {
            return null
        }
    }
}