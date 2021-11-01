package com.majority.assignment.data.entities.countryInfo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Flags(
    val png: String
) : Parcelable