package com.majority.assignment.data.remote

import okhttp3.ResponseBody


/**
 * It's a Resource class which holds the Retrofit Success, Failure data.
 * Also responsible to send the trigger to UI to show/hide progressbar.
 */
sealed class Resource<out T> {


    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int? = null,
        val errorBody: ResponseBody? = null,
        val errorMessage: String? = null
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()


}