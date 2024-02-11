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
package com.flexcode.githubgraphql.di

import com.flexcode.domain.repositories.GetFollowersRepository
import com.flexcode.githubgraphql.domain.repositories.GetFollowingRepository
import com.flexcode.githubgraphql.domain.repositories.GetGithubUserRepository
import com.flexcode.githubgraphql.domain.repositories.GetRepositoriesRepository
import com.flexcode.graphql.data.repositories.GetFollowersRepositoryImpl
import com.flexcode.graphql.data.repositories.GetFollowingRepositoryImpl
import com.flexcode.graphql.data.repositories.GetGithubUserRepositoryImpl
import com.flexcode.graphql.data.repositories.GetRepositoriesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun providesGetGithubUserRepository(
        repositoryImpl: GetGithubUserRepositoryImpl,
    ): GetGithubUserRepository

    @Binds
    @Singleton
    abstract fun providesGetFollowersRepository(
        repositoryImpl: GetFollowersRepositoryImpl,
    ): GetFollowersRepository

    @Binds
    @Singleton
    abstract fun providesGetFollowingRepository(
        repositoryImpl: GetFollowingRepositoryImpl,
    ): GetFollowingRepository

    @Binds
    @Singleton
    abstract fun providesGetRepositoriesRepository(
        repositoryImpl: GetRepositoriesRepositoryImpl,
    ): GetRepositoriesRepository
}
