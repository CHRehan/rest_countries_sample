package com.sample.api

import com.sample.data.entities.countryInfo.CountryInfo
import retrofit2.http.*

interface CountryService {

    @GET("all")
    suspend fun getAllCountries(): List<CountryInfo>

}