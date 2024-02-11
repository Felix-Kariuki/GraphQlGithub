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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flexcode.graphql.designsystem.theme.spacing
import com.flexcode.githubgraphql.domain.models.SingleFollower
import com.flexcode.graphql.presentation.components.FollowersComponent
import com.flexcode.graphql.presentation.state.GithubState

@Composable
fun FollowingScreen(
    modifier: Modifier = Modifier,
    onClick: (SingleFollower) -> Unit,
    state: GithubState,
) {
    Scaffold(
        topBar = {
            Surface(shadowElevation = 2.dp) {
                TopItemComponent(name = state.userName, text = "Following")
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
                    state.following?.nodes?.size?.let {
                        items(it) { index ->
                            FollowersComponent(
                                user = state.following.nodes!![index],
                                onClick = onClick,
                            )
                        }
                    }
                }
            }
        }
    }
}
