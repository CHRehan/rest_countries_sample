package com.sample.data.remote

import com.sample.BuildConfig
import com.sample.BuildConfig.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 *OKHttp3 & Retrofit 2.0 client to make an APIs call.
 *It's a generic class and provide the client of the desired API service.
 */

class CountryClient @Inject constructor() {


    fun <Api> buildApi(
        api: Class<Api>
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getRetrofitClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

    private fun getRetrofitClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .also { client ->
                if (BuildConfig.DEBUG) {
                    client.addInterceptor(HttpLoggingInterceptor()
                        .apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                }
            }.build()
    }
}


