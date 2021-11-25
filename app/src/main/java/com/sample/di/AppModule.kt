package com.sample.di

import com.sample.api.CountryService
import com.sample.data.remote.CountryClient
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