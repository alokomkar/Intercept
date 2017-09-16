package com.alokomkar.intercept

import android.database.Cursor

/**
 * Created by Alok Omkar on 2017-09-16.
 */

interface SMSView : BaseView {
    fun onSuccess( smsListMap : HashMap<String, ArrayList<SMS>> )
    fun onFailure( error: String )
}
