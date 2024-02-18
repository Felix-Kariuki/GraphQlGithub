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
import com.apollographql.apollo3.api.Optional
import com.flexcode.GetRepositoriesQuery
import com.flexcode.domain.utils.Dispatcher
import com.flexcode.domain.utils.DispatcherProvider
import com.flexcode.githubgraphql.domain.models.GithubRepositories
import com.flexcode.githubgraphql.domain.repositories.GetRepositoriesRepository
import com.flexcode.githubgraphql.domain.utils.ResultWrapper
import com.flexcode.graphql.data.mappers.toRepositories
import com.flexcode.graphql.data.remote.flowSafeCall
import com.flexcode.type.RepositoryVisibility
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoriesRepositoryImpl @Inject constructor(
    private val apolloGithubClient: ApolloClient,
    @Dispatcher(DispatcherProvider.IO) private val dispatcher: CoroutineDispatcher,
) : GetRepositoriesRepository {

    override suspend fun getRepos(userName: String): Flow<ResultWrapper<GithubRepositories?>> =
        flowSafeCall(dispatcher) {
            apolloGithubClient.query(
                GetRepositoriesQuery(
                    Optional.present(RepositoryVisibility.PUBLIC),
                    Optional.present(100),
                ),
            )
                .execute()
                .data
                ?.viewer
                ?.repositories
                ?.toRepositories()
        }
}
