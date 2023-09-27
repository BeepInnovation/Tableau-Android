package com.beepkart.tableau_android

/**
 * Created on : September 26, 2023
 *
 * Author     : Sai Sukesh
 */
class Tableau {

    companion object {
        fun with(listener: TableauListener): TableauManager {
            return TableauManager(listener)
        }
    }
}

