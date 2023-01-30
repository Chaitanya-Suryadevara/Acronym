package com.android.acromine.data

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
data class Result<out T : Any>(
    val state: Status,
    val data: T?,
    val exception: Exception?,
    val progress: Int?
) {
    companion object {
        fun <T : Any> Success(data: T?): Result<T> {
            return Result(
                state = Status.SUCCESS,
                data = data,
                exception = null,
                progress = null
            )
        }

        fun <T : Any> Error(exception: Exception): Result<T> {
            return Result(
                state = Status.ERROR,
                data = null,
                exception = exception,
                progress = null
            )
        }

        fun <T : Any> Loading(progress: Int): Result<T> {
            return Result(
                state = Status.LOADING,
                data = null,
                exception = null,
                progress = progress
            )
        }

    }
}