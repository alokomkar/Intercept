package com.alokomkar.intercept

import android.Manifest
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SMSView {

    private val PERMISSIONS_REQUEST_CODE_SMS: Int = 111

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val permissionList = arrayListOf<String>(Manifest.permission.READ_SMS)
        if (!handleMultiplePermission(this@MainActivity, permissionList)) {
            requestPermission(AppPermission.READ_SMS)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if( grantResults.size == 1 )
            SMSPresenter(this, contentResolver)
        else Toast.makeText(this@MainActivity, R.string.permission_denied, Toast.LENGTH_LONG).show()
    }

    override fun showProgress(message: String) {
        progressLayout.visibility = View.VISIBLE
    }

    override fun onSuccess(smsListMap : HashMap<String, ArrayList<SMS>>) {
        dismissProgress()
        smsViewPager.setAdapter(SmsPagerAdapter(supportFragmentManager, smsListMap))
        smsTabLayout.setupWithViewPager(smsViewPager)
        smsViewPager.offscreenPageLimit = 3
    }

    override fun updateProgress(message: String) {
        loadingTextView.text = "Progress :  $message"
    }

    override fun onFailure(error: String) {
        loadingTextView.text = "Error : $error"
        dismissProgress()
    }

    override fun dismissProgress() {
        progressLayout.visibility = View.GONE
    }

    override fun startManagingCursor(c: Cursor?) {
        startManagingCursor(c)
    }
}
