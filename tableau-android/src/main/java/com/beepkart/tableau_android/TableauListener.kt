package com.beepkart.tableau_android

import android.graphics.Bitmap
import okhttp3.ResponseBody

/**
 * Created on : September 26, 2023
 *
 * Author     : Sai Sukesh
 */
interface TableauListener {
    fun onTableauSignResponse(isSuccessful: Boolean, response: String)
    fun onTableauImageResponse(bitmap: Bitmap?)
    fun onTableauTokenResponse(response: ResponseBody?)
}