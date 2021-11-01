package com.majority.assignment.di

import com.majority.assignment.api.CountryService
import com.majority.assignment.data.remote.CountryClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideCountryAPI(
        countryClient: CountryClient,
    ): CountryService {
        return countryClient.buildApi(CountryService::class.java)
    }


}