package com.alokomkar.intercept

import android.provider.Telephony.Sms
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import java.lang.Exception


/**
 * Created by Alok Omkar on 2017-09-16.
 */

class SMSPresenter(private val mSmsView: SMSView, val contentResolver: ContentResolver ) {

    init {
        getAllSmS()
    }

    private fun getAllSmS() {
        SMSTask().execute()
    }

    inner class SMSTask() : AsyncTask<Void, Void, HashMap<String, ArrayList<SMS>>>() {

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
            mSmsView.updateProgress(values[0].toString())
        }

        override fun onPreExecute() {
            super.onPreExecute()
            mSmsView.showProgress("Loading")
        }

        override fun onPostExecute(result: HashMap<String, ArrayList<SMS>>?) {
            super.onPostExecute(result)
            if (result != null) {
                mSmsView.onSuccess(result)
                mSmsView.dismissProgress()
            }
        }

        override fun doInBackground(vararg void: Void?): HashMap<String, ArrayList<SMS>> {
            return getAllSms()
        }

    }

    private val TAG: String = "SMSPresenter"

    fun getAllSms(): HashMap<String, ArrayList<SMS>> {
        val smsMap = HashMap<String, ArrayList<SMS>>()
        try {

            val lstSms = ArrayList<SMS>()
            var objSms : SMS ?= null
            val message = Uri.parse("content://sms/")
            val cr = contentResolver

            val c = cr.query(message, null, null, null, null)
            //startManagingCursor(c)
            val totalSMS = c!!.getCount()
            Log.d(TAG, "SMS : " + totalSMS)

            if (c.moveToFirst()) {
                for (i in 0..1000) {

                    objSms = SMS()
                    objSms.smsId = (c.getString(c.getColumnIndexOrThrow("_id")))
                    objSms.smsAddress = (c.getString(c
                            .getColumnIndexOrThrow("address")))
                    objSms.smsMessage = (c.getString(c.getColumnIndexOrThrow("body")))
                    objSms.readState = (c.getString(c.getColumnIndex("read")))
                    objSms.time = (c.getString(c.getColumnIndexOrThrow("date")))
                    if (c.getString(c.getColumnIndexOrThrow("type")).contains("1")) {
                        objSms.folderName = ("inbox")
                    } else {
                        objSms.folderName = ("sent")
                    }

                    lstSms.add(objSms)

                    if( smsMap.containsKey(objSms.smsAddress) ) {
                        smsMap.get(objSms.smsAddress)!!.add(objSms)
                    }
                    else {
                        smsMap.put(objSms.smsAddress, ArrayList())
                        smsMap.get(objSms.smsAddress)!!.add(objSms)
                    }
                    Log.d(TAG, "SMS : " + objSms)
                    //objSms.save{}
                    c.moveToNext()
                }
            }
            // else {
            // throw new RuntimeException("You have no SMS");
            // }
            c.close()

            return smsMap
        } catch ( e : Exception ) {
            e.printStackTrace()
            return smsMap
        }

    }

    private fun startManagingCursor(c: Cursor?) {
        mSmsView.startManagingCursor(c)
    }
}
