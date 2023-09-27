package com.beepkart.tableau_android

import com.beepkart.tableau_android.models.Credentials
import com.beepkart.tableau_android.network.TableauRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created on : September 26, 2023
 *
 * Author     : Sai Sukesh
 */
class TableauManager(private val listener: TableauListener) {

    private lateinit var tokenName: String
    private lateinit var tokenSecret: String
    private lateinit var siteUrl: String

    fun loginWithToken(tokenName: String, tokenSecret: String, siteUrl: String): TableauDashboard {
        this.tokenName = tokenName
        this.tokenSecret = tokenSecret
        this.siteUrl = siteUrl
        return TableauDashboard()
    }

    @Warning("Method not implemented yet")
    fun loginWithUserAndPassword(): TableauDashboard {
        return TableauDashboard()
    }

    inner class TableauDashboard {

        private suspend fun tableauSignIn(): Credentials? {
            return TableauRepository().tableauSignIn(tokenName, tokenSecret, siteUrl)
        }

        fun getDashboardImage(viewId: String, filters: Map<String, String>) {
            CoroutineScope(Dispatchers.IO).launch {
                tableauSignIn()?.let {
                    withContext(Dispatchers.Main) {
                        listener.onTableauSignResponse(true, "Authentication Successful")
                    }
                    val bitmap = TableauRepository().getTableauImage(it.token, it.site.id, viewId, filters)
                    withContext(Dispatchers.Main) {
                        listener.onTableauImageResponse(bitmap)
                    }
                } ?: run {
                    withContext(Dispatchers.Main) {
                        listener.onTableauSignResponse(false, "Authentication Failed")
                    }
                }
            }
        }
    }
}