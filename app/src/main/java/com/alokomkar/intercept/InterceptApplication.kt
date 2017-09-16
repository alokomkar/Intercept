package com.alokomkar.intercept

import android.app.Application

import java.util.ArrayList

import co.uk.rushorm.android.AndroidInitializeConfig
import co.uk.rushorm.core.Rush
import co.uk.rushorm.core.RushCore

/**
 * Created by Alok Omkar on 2017-09-16.
 */

class InterceptApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val classes = ArrayList<Class<out Rush>>()
        classes.add(SMS::class.java)


        val config = AndroidInitializeConfig(applicationContext, classes)
        RushCore.initialize(config)
    }
}
