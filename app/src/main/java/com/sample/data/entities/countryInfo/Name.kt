package com.sample.data.entities.countryInfo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Name(
    var common: String,
    var official: String
) : Parcelable