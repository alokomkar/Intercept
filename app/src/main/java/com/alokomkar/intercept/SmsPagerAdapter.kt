package com.alokomkar.intercept

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by Alok Omkar on 2017-09-16.
 */

class SmsPagerAdapter(fm: FragmentManager, val smsListMap: HashMap<String, ArrayList<SMS>>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return SMSConversationFragment.newInstance(smsListMap.get(getPageTitle(position)))
    }

    override fun getPageTitle(position: Int): CharSequence {
        return smsListMap.keys.elementAt(position)
    }

    override fun getCount(): Int {
        return smsListMap.size
    }
}
