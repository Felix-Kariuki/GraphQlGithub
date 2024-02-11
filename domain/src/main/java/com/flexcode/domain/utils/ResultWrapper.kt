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
package com.flexcode.githubgraphql.domain.utils

/**
 * A generic  class representing the state of an operation with optional data, error, and loading indicators.
 * @property data The data result of the operation.
 * @property errorMessage The error that occurred during the operation.
 */
sealed class ResultWrapper<T>(val data: T? = null, val errorMessage: String? = null) {
    /**
     * Creates a success state with the specified data.
     * @param data The data result.
     * @return A Resource instance representing the success state with data.
     */
    class Success<T>(data: T) : ResultWrapper<T>(data)

    /**
     * Creates an error state with the specified error.
     * @param error The error that occurred.
     * @return A Resource instance representing the error state with the error and  null data
     */
    class Error<T>(error: String, data: T? = null) : ResultWrapper<T>(data, error)

    /**
     * Creates a loading state.
     * @return A Resource instance representing the loading state.
     */
    class Loading<T>(data: T? = null) : ResultWrapper<T>(data)
}

data class ErrorResponse(
    val message: String,
)
