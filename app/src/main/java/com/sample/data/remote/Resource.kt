package com.sample.data.remote

import okhttp3.ResponseBody
/**
 * It's a Resource class which holds the Retrofit Success, Failure data.
 * Also responsible to send the trigger to UI to show/hide progressbar.
 *
 * The sealed classes have some distinct feature,
 * their constructors are private by default.
 * A sealed class is implicitly abstract and hence it cannot be instantiated.
 *
 * As the word sealed suggests, sealed classes conform to restricted or
 * bounded class hierarchies. A sealed class defines a set of subclasses
 * within it. It is used when it is known in advance that a type will
 * conform to one of the subclass types. Sealed classes ensure type-safety
 * by restricting the types to be matched at compile-time rather than at
 * runtime.
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