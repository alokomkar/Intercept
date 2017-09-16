package com.alokomkar.intercept

/**
 * Created by Alok Omkar on 2017-09-16.
 */

interface BaseView {
    fun showProgress(message: String)
    fun updateProgress(message: String)
    fun dismissProgress()
}
