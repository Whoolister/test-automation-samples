package com.whoolister.seleniumstarter

import co.touchlab.kermit.Logger
import jakarta.validation.constraints.PositiveOrZero

const val TAG: String = "Waits"
const val DEFAULT_TIMEOUT_IN_MILLIS: Long = 5_000L
const val DEFAULT_BACKOFF_IN_MILLIS: Long = 500L

fun <T> untilSuccess(
    @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS,
    @PositiveOrZero backoffInMillis: Long = DEFAULT_BACKOFF_IN_MILLIS,
    block: () -> T,
): Result<T> {
    val endTime = System.currentTimeMillis() + timeoutInMillis
    var lastThrowable: Throwable? = null

    do {
        runCatching(block)
            .onSuccess { return Result.success(it) }
            .onFailure {
                lastThrowable = it

                Logger.w(TAG) { "Block failed with exception: ${it.message}" }
            }

        backoff(backoffInMillis)
    } while (System.currentTimeMillis() < endTime)

    // If we reach here, it means the block did not succeed within the timeout, due to one or more exceptions.
    return Result.failure(lastThrowable!!)
}

fun untilTrue(
    @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS,
    @PositiveOrZero backoffInMillis: Long = DEFAULT_BACKOFF_IN_MILLIS,
    condition: () -> Boolean,
): Boolean = untilSuccess(
    timeoutInMillis = timeoutInMillis,
    backoffInMillis = backoffInMillis,
    block = condition,
).getOrDefault(false)

// TODO: Change this implementation to use a more sophisticated backoff strategy
fun backoff(@PositiveOrZero backoffInMillis: Long = DEFAULT_BACKOFF_IN_MILLIS) {
    Logger.i(TAG) { "Backing off for $backoffInMillis ms" }
    Thread.sleep(backoffInMillis)
}