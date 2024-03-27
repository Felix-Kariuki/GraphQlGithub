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
package com.flexcode.graphql.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.flexcode.graphql.designsystem.previews.GithubPreviews
import com.flexcode.graphql.designsystem.theme.GithubGraphQlTheme
import com.flexcode.graphql.designsystem.theme.spacing
import com.flexcode.graphql.presentation.components.UserProfileExtras
import com.flexcode.graphql.presentation.components.UserprofileBottomItem
import com.flexcode.graphql.presentation.state.GithubState

@Composable
fun GithubUserProfileScreen(
    modifier: Modifier = Modifier,
    onFollowersClick: () -> Unit,
    onFollowingClick: () -> Unit,
    onSeeAllClick: () -> Unit,
    state: GithubState,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize(),
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier=modifier.testTag("profile_loader"))
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
            ) {
                item {
                    UserProfileExtras(
                        user = state.githubUser,
                        onFollowingClick = onFollowingClick,
                        onFollowersClick = onFollowersClick,
                    )
                }

                item {
                    Spacer(modifier = modifier.height(spacing.large))
                    UserprofileBottomItem(state = state, onSeeAllClick = onSeeAllClick)
                }
            }
        }
    }
}

@GithubPreviews
@Composable
fun GithubUserProfileScreenPreview() {
    GithubGraphQlTheme {
        GithubUserProfileScreen(
            state = GithubState(),
            onFollowersClick = {},
            onFollowingClick = {},
            onSeeAllClick = {},
        )
    }
}
