package com.majority.assignment.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * Wrapper to wrap the API responses.
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