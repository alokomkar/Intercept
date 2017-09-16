package com.alokomkar.intercept

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SMSView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SMSPresenter(this, contentResolver)
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
