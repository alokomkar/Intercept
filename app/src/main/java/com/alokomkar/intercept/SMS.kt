package com.alokomkar.intercept

import android.os.Parcel
import android.os.Parcelable
import co.uk.rushorm.core.RushObject

/**
 * Created by Alok Omkar on 2017-09-16.
 */
data class SMS(var smsId: String = "",
               var smsAddress: String = "",
               var smsMessage: String = "",
               var readState: String = "",
               var time: String = "",
               var folderName: String = "") : RushObject(), Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(smsId)
        writeString(smsAddress)
        writeString(smsMessage)
        writeString(readState)
        writeString(time)
        writeString(folderName)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<SMS> = object : Parcelable.Creator<SMS> {
            override fun createFromParcel(source: Parcel): SMS = SMS(source)
            override fun newArray(size: Int): Array<SMS?> = arrayOfNulls(size)
        }
    }
}