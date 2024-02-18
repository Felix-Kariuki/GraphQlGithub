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
package com.flexcode.graphql.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flexcode.githubgraphql.domain.models.SingleFollower
import com.flexcode.graphql.designsystem.compossables.GithubProfilePicture
import com.flexcode.graphql.designsystem.compossables.GithubText
import com.flexcode.graphql.designsystem.previews.GithubPreviews
import com.flexcode.graphql.designsystem.theme.GithubGraphQlTheme
import com.flexcode.graphql.designsystem.theme.spacing
import com.flexcode.graphql.designsystem.utils.noRippleClick

@Composable
fun FollowersComponent(
    modifier: Modifier = Modifier,
    onClick: (SingleFollower) -> Unit,
    user: SingleFollower?,
) {
    Row(
        modifier
            .fillMaxWidth()
            .noRippleClick {
                user?.let {
                    onClick(it)
                }
            }
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GithubProfilePicture(url = user?.avatarUrl, size = 60.dp)

        Column(
            modifier.padding(start = spacing.medium),
            verticalArrangement = Arrangement.spacedBy(1.dp),
        ) {
            GithubText(
                style = MaterialTheme.typography.titleMedium,
                text = user?.name ?: "",
                fontSize = 15.sp,
            )
            GithubText(
                fontSize = 12.sp,
                text = user?.login ?: "",
                style = MaterialTheme.typography.bodyLarge,
                textColor = MaterialTheme.colorScheme.outline,
            )
            GithubText(
                fontSize = 13.sp,
                text = user?.bio ?: "",
                style = MaterialTheme.typography.bodyLarge,
                textColor = MaterialTheme.colorScheme.outline,
            )
        }
    }
}

@GithubPreviews
@Composable
fun FollowersComponentPreview() {
    GithubGraphQlTheme {
        FollowersComponent(
            user = sampleFollower,
            onClick = {},
        )
    }
}

val sampleFollower = SingleFollower(
    avatarUrl = null,
    bio = "Mobile engineer",
    login = "Felix-kariuki",
    name = "John Doe",
)
