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
package com.flexcode.githubgraphql.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.flexcode.graphql.presentation.event.GithubEvent
import com.flexcode.graphql.presentation.viewModels.GithubUserViewModel
import com.flexcode.graphql.presentation.views.FollowersScreen
import com.flexcode.graphql.presentation.views.FollowingScreen
import com.flexcode.graphql.presentation.views.GithubUserProfileScreen
import com.flexcode.graphql.presentation.views.RepositoriesScreen

@Composable
fun GithubNavHost(
    navHostController: NavHostController,
    viewModel: GithubUserViewModel,
) {
    NavHost(navController = navHostController, startDestination = Routes.PROFILE_SCREEN) {
        composable(Routes.FOLLOWERS_SCREEN) {
            val state by viewModel.state.collectAsState()
            FollowersScreen(state = state, onClick = {
                it.login?.let { value ->
                    viewModel.onEvent(GithubEvent.OnUserNameChange(value))
                    navHostController.navigate(Routes.PROFILE_SCREEN)
                }
            },)
        }
        composable(Routes.FOLLOWING_SCREEN) {
            val state by viewModel.state.collectAsState()
            FollowingScreen(state = state, onClick = {
                it.login?.let { value ->
                    viewModel.onEvent(GithubEvent.OnUserNameChange(value))
                    navHostController.navigate(Routes.PROFILE_SCREEN)
                }
            },)
        }

        composable(Routes.REPOSITORIES_SCREEN) {
            val state by viewModel.state.collectAsState()
            RepositoriesScreen(state = state) {
                navHostController.navigateUp()
            }
        }

        composable(Routes.PROFILE_SCREEN) {
            val state by viewModel.state.collectAsState()

            GithubUserProfileScreen(
                state = state,
                onFollowersClick = {
                    navHostController.navigate(Routes.FOLLOWERS_SCREEN)
                },
                onFollowingClick = {
                    navHostController.navigate(Routes.FOLLOWING_SCREEN)
                },
                onSeeAllClick = {
                    navHostController.navigate(Routes.REPOSITORIES_SCREEN)
                },
            )
        }
    }
}
