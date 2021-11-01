package com.majority.assignment.data.entities.countryInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Countries(
    val countries: List<CountryInfo>
)