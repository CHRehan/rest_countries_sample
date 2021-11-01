package com.majority.assignment.api

import com.majority.assignment.data.entities.countryInfo.CountryInfo
import retrofit2.Response
import retrofit2.http.*

interface CountryService {

    @GET("all")
    suspend fun getAllCountries(): List<CountryInfo>

}