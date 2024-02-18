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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flexcode.githubgraphql.domain.models.SingleFollower
import com.flexcode.graphql.designsystem.compossables.GithubText
import com.flexcode.graphql.designsystem.previews.GithubPreviews
import com.flexcode.graphql.designsystem.theme.GithubGraphQlTheme
import com.flexcode.graphql.designsystem.theme.spacing
import com.flexcode.graphql.presentation.components.FollowersComponent
import com.flexcode.graphql.presentation.state.GithubState

@Composable
fun FollowersScreen(
    modifier: Modifier = Modifier,
    onClick: (SingleFollower) -> Unit,
    state: GithubState,
) {
    Scaffold(
        topBar = {
            Surface(shadowElevation = 2.dp) {
                TopItemComponent(name = state.userName)
            }
        },
    ) { padding ->
        Box(contentAlignment = Alignment.Center) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn(
                    modifier
                        .padding(padding)
                        .padding(
                            start = spacing.medium,
                            end = spacing.medium,
                            top = spacing.medium,
                        ),
                    verticalArrangement = Arrangement.spacedBy(spacing.extraMedium),
                ) {
                    state.followers?.nodes?.size?.let {
                        items(it) { index ->
                            FollowersComponent(
                                user = state?.followers?.nodes!![index],
                                onClick = onClick,
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopItemComponent(modifier: Modifier = Modifier, name: String, text: String = "Followers") {
    TopAppBar(title = {
        Column(
            modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
        ) {
            GithubText(
                style = MaterialTheme.typography.titleMedium,
                text = name,
                fontSize = 17.sp,
            )
            Spacer(modifier = modifier.height(spacing.extraSmall))
            GithubText(
                fontSize = 14.sp,
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                textColor = MaterialTheme.colorScheme.outline,
            )
        }
    },)
}

@GithubPreviews
@Composable
fun TopItemPreview() {
    GithubGraphQlTheme {
        TopItemComponent(name = "Felix Kariuki")
    }
}

@GithubPreviews
@Composable
fun FollowersScreenPreview() {
    GithubGraphQlTheme {
        FollowersScreen(state = GithubState(), onClick = {})
    }
}
