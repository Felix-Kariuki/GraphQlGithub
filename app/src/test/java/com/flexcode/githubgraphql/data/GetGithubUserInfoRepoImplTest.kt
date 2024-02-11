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
package com.flexcode.githubgraphql.data

import app.cash.turbine.test
import com.apollographql.apollo3.ApolloClient
import com.flexcode.GetUserProfileQuery
import com.flexcode.githubgraphql.domain.repositories.GetGithubUserRepository
import com.flexcode.githubgraphql.domain.utils.ResultWrapper
import com.flexcode.graphql.data.mappers.toGithubUser
import com.flexcode.graphql.data.repositories.GetGithubUserRepositoryImpl
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetGithubUserInfoRepoImplTest {

    @MockK
    val apolloClient = mockk<ApolloClient>()
    private lateinit var repository: GetGithubUserRepository

    @Before
    fun setUp() {
        repository = GetGithubUserRepositoryImpl(
            apolloGithubClient = apolloClient,
            dispatcher = UnconfinedTestDispatcher(),
        )
    }

    @Test
    fun `test  loading state triggered first`() {
        coEvery {
            apolloClient.query(GetUserProfileQuery("Felix-Kariuki"))
                .execute()
                .data
                ?.repositoryOwner
                ?.onUser
                ?.toGithubUser()
        } returns fakeGithubUser

        runTest {
            repository.getGithubUser("Felix-Kariuki")
                .test {
                    awaitItem().also { result ->
                        Truth.assertThat(result).isInstanceOf(ResultWrapper.Loading::class.java)
                    }
                    cancelAndIgnoreRemainingEvents()
                }
        }
    }

//    @Test
//    fun `test get git user returns correctly mapped data`() {
//        coEvery {
//            apolloClient.query(GetUserProfileQuery("Felix-Kariuki"))
//                .execute()
//                .data
//                ?.repositoryOwner
//                ?.onUser
//                ?.toGithubUser()
//        } returns fakeGithubUser
//
//
//        runTest {
//            val githubUser =
//                apolloClient.query(GetUserProfileQuery("Felix-Kariuki"))
//                    .execute()
//                    .data
//                    ?.repositoryOwner
//                    ?.onUser
//
//            val result =
//                repository.getGithubUser("Felix-Kariuki")
//
//            Truth.assertThat(result).isInstanceOf(ResultWrapper.Success::class.java)
//
//        }
//
//
//    }
}

val fakeGithubUser = GithubUser(
    avatarUrl = null,
    bio = null,
    email = null,
    followers = null,
    following = null,
    location = null,
    login = null,
    name = "Felix-Kariuki",
    starredRepositories = null,
    twitterUsername = null,
    websiteUrl = null,
)
