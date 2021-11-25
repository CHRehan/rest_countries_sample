package com.sample.data.entities.countryInfo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CurrencyDetail(
    val name: String?,
    val symbol: String?
) :Parcelable