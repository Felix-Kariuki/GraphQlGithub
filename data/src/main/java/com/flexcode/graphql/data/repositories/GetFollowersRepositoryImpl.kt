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
package com.flexcode.graphql.data.repositories

import com.apollographql.apollo3.ApolloClient
import com.flexcode.GetFollowersQuery
import com.flexcode.domain.repositories.GetFollowersRepository
import com.flexcode.domain.utils.Dispatcher
import com.flexcode.domain.utils.DispatcherProvider
import com.flexcode.githubgraphql.domain.models.GithubFollowers
import com.flexcode.githubgraphql.domain.utils.ResultWrapper
import com.flexcode.graphql.data.mappers.toFollowers
import com.flexcode.graphql.data.remote.flowSafeCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFollowersRepositoryImpl @Inject constructor(
    private val apolloGithubClient: ApolloClient,
    @Dispatcher(DispatcherProvider.IO) private val dispatcher: CoroutineDispatcher,
) : GetFollowersRepository {
    override suspend fun getFollowers(userName: String): Flow<ResultWrapper<GithubFollowers?>> =
        flowSafeCall(dispatcher) {
            apolloGithubClient.query(GetFollowersQuery(userName))
                .execute()
                .data
                ?.user
                ?.followers
                ?.toFollowers()
        }
}
