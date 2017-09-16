package com.alokomkar.intercept

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.ArrayList

/**
 * Created by Alok Omkar on 2017-09-16.
 */

class SMSRecyclerAdapter(private val smsArrayList: ArrayList<SMS>) : RecyclerView.Adapter<SMSRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sms, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.smsTextView!!.text = smsArrayList[position].smsMessage
    }

    override fun getItemCount(): Int {
        return smsArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var smsTextView : TextView? = null
        init {
            smsTextView = itemView.findViewById(R.id.smsTextView)
        }
    }
}
