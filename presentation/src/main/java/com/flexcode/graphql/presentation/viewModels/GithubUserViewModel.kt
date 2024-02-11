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
package com.flexcode.graphql.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexcode.githubgraphql.domain.usecase.GetFollowersUseCase
import com.flexcode.githubgraphql.domain.usecase.GetFollowingUseCase
import com.flexcode.githubgraphql.domain.usecase.GetGithubUserUseCase
import com.flexcode.githubgraphql.domain.usecase.GetRepositoriesUseCase
import com.flexcode.githubgraphql.domain.utils.ResultWrapper
import com.flexcode.graphql.presentation.event.GithubEvent
import com.flexcode.graphql.presentation.state.GithubState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUserViewModel @Inject constructor(
    private val getGithubUserUseCase: GetGithubUserUseCase,
    private val getFollowingUseCase: GetFollowingUseCase,
    private val getFollowersUseCase: GetFollowersUseCase,
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(GithubState())
    val state = _state.asStateFlow()

    fun onEvent(event: GithubEvent) {
        when (event) {
            is GithubEvent.OnUserNameChange -> {
                _state.update {
                    makeNetworkCalls(event.value)
                    it.copy(userName = event.value)
                }
            }
        }
    }

    private fun makeNetworkCalls(userName: String = state.value.userName) {
        getGithubUser(userName)
        getFollowing(userName)
        getFollowers(userName)
        getRepositories(userName)
    }

    init {
        makeNetworkCalls()
    }

    fun getGithubUser(userName: String = state.value.userName) {
        viewModelScope.launch {
            getGithubUserUseCase.invoke(userName).collectLatest { response ->
                _state.update {
                    when (response) {
                        is ResultWrapper.Success -> {
                            it.copy(isLoading = false, githubUser = response.data)
                        }

                        is ResultWrapper.Loading -> {
                            it.copy(isLoading = true)
                        }

                        is ResultWrapper.Error -> {
                            it.copy(isLoading = false, errorMessage = response.errorMessage)
                        }
                    }
                }
            }
        }
    }

    fun getRepositories(userName: String = state.value.userName) {
        viewModelScope.launch {
            getRepositoriesUseCase.invoke(userName).collectLatest { response ->
                _state.update {
                    when (response) {
                        is ResultWrapper.Success -> {
                            it.copy(isLoading = false, repositories = response.data)
                        }

                        is ResultWrapper.Loading -> {
                            it.copy(isLoading = true)
                        }

                        is ResultWrapper.Error -> {
                            it.copy(isLoading = false, errorMessage = response.errorMessage)
                        }
                    }
                }
            }
        }
    }

    fun getFollowers(userName: String = state.value.userName) {
        viewModelScope.launch {
            getFollowersUseCase.invoke(userName).collectLatest { response ->
                _state.update {
                    when (response) {
                        is ResultWrapper.Success -> {
                            it.copy(isLoading = false, followers = response.data)
                        }

                        is ResultWrapper.Loading -> {
                            it.copy(isLoading = true)
                        }

                        is ResultWrapper.Error -> {
                            it.copy(isLoading = false, errorMessage = response.errorMessage)
                        }
                    }
                }
            }
        }
    }

    fun getFollowing(userName: String = state.value.userName) {
        viewModelScope.launch {
            getFollowingUseCase.invoke(userName).collectLatest { response ->
                _state.update {
                    when (response) {
                        is ResultWrapper.Success -> {
                            it.copy(isLoading = false, following = response.data)
                        }

                        is ResultWrapper.Loading -> {
                            it.copy(isLoading = true)
                        }

                        is ResultWrapper.Error -> {
                            it.copy(isLoading = false, errorMessage = response.errorMessage)
                        }
                    }
                }
            }
        }
    }
}
