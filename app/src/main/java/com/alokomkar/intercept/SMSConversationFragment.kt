package com.alokomkar.intercept

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_sms_conversation.*

import java.util.ArrayList

/**
 * Created by Alok Omkar on 2017-09-16.
 */

class SMSConversationFragment : Fragment() {
    companion object {

        fun newInstance(smsArrayList: ArrayList<SMS>?): Fragment? {
            val smsConversationFragment = SMSConversationFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("smsList", smsArrayList)
            smsConversationFragment.arguments = bundle
            return smsConversationFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_sms_conversation, container, false)
    }
    lateinit var smsArrayList : ArrayList<SMS>

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        smsArrayList = arguments.getParcelableArrayList("smsList")
        setupRecyclerView(smsArrayList)
    }

    private fun setupRecyclerView(smsArrayList: ArrayList<SMS>?) {
        smsRecyclerView.layoutManager = LinearLayoutManager(context)
        smsRecyclerView.adapter = SMSRecyclerAdapter(smsArrayList!!)
    }
}
