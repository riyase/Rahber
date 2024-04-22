package com.rahbertheadvisor.android.common.model

sealed class Result<out T: Any> {

    object Idle: Result<Nothing>()
    object Loading: Result<Nothing>()
    data class Success<out T: Any>(val data: T): Result<T>()
    data class Error(val exception: Exception, val message: String): Result<Nothing>()
}