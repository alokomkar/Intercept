package com.alokomkar.intercept

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Build
import android.provider.Settings
import java.util.concurrent.TimeUnit

/**
 * Created by Alok Omkar on 2017-09-16.
 */


inline fun supportsLollipop(func: () -> Unit) =
        supportsVersion(Build.VERSION_CODES.LOLLIPOP, func)

fun isLollipopOrBellow(): Boolean = (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.LOLLIPOP)

inline fun supportsVersion(ver: Int, func: () -> Unit) {
    if (Build.VERSION.SDK_INT >= ver) {
        func.invoke()
    }
}


