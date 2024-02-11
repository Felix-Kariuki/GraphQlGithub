/**
 *
 * Copyright (c) 2024 Felix Kariuki.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package com.flexcode.graphql.data.remote

import com.apollographql.apollo3.exception.ApolloHttpException
import com.flexcode.githubgraphql.domain.utils.ErrorResponse
import com.flexcode.githubgraphql.domain.utils.ResultWrapper
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import okio.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.reflect.KClass

private val networkExceptions: List<KClass<out IOException>> =
    listOf(
        SocketTimeoutException::class,
        ConnectException::class,
        UnknownHostException::class,
    )

fun parseErrorBody(throwable: ApolloHttpException): ErrorResponse? = try {
    throwable.localizedMessage?.toString()?.let {
        val moshi = Moshi.Builder()
            .build()
        val jsonAdapter = moshi.adapter(
            ErrorResponse::class.java,
        ).lenient()
        jsonAdapter.fromJson(it)
    }
} catch (e: Exception) {
    e.printStackTrace()
    null
}

fun <T> flowSafeCall(
    dispatcher: CoroutineDispatcher,
    block: suspend () -> T,
): Flow<ResultWrapper<T>> = flow {
    emit(ResultWrapper.Loading())
    emit(ResultWrapper.Success(block.invoke()))
}.catch { throwable ->
    if (throwable is CancellationException) {
        throw throwable
    }
    when (throwable) {
        is ApolloHttpException -> {
            val errorResponse = parseErrorBody(throwable)
            emit(
                ResultWrapper.Error(
                    error = errorResponse?.message.toString(),
                ),
            )
        }

        else -> {
            if (throwable::class in networkExceptions) {
                emit(
                    ResultWrapper.Error(
                        error = "Check your connection and try again",
                    ),
                )
            } else {
                emit(
                    ResultWrapper.Error(
                        error = throwable.message ?: "An error occurred. Try again",
                    ),
                )
            }
        }
    }
}.flowOn(dispatcher)

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T,
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            if (throwable is CancellationException) {
                throw throwable
            }
            when (throwable) {
                is ApolloHttpException -> {
                    val errorResponse = parseErrorBody(throwable)
                    ResultWrapper.Error(errorResponse?.message.toString())
                }

                else -> {
                    if (throwable::class in networkExceptions) {
                        ResultWrapper.Error(
                            error = "Check your connection and try again",
                        )
                    } else {
                        ResultWrapper.Error(
                            error = throwable.message ?: "An error occurred. Try again",
                        )
                    }
                }
            }
        }
    }
}
