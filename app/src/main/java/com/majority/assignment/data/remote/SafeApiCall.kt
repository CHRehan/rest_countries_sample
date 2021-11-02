package com.majority.assignment.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * Wrapper Class  to wrap the API responses.
 * If there is all good this class will send the data as it to ViewModel.
 * If there is any error this class is parse the error and send the appropriate message.
 *
 * Note: This is my own personal approach it's provide us a lot of ease and save our development time
 * when we have around 100 network calls in a project.
 * It may or may not be good. If you found any issue please must share
 * your honest feedback I am open to modify this.
 */
interface SafeApiCall {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
               throwable.printStackTrace()
                when (throwable) {
                    is HttpException -> {
                        Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    is UnknownHostException -> {
                        Resource.Failure(false, errorMessage = throwable.message)
                    }

                    else -> {
                        Resource.Failure(false, errorMessage = "Something went wrong")
                    }
                }
            } finally {
                Resource.Failure(false, errorMessage = "Something went wrong")

            }
        }
    }
}