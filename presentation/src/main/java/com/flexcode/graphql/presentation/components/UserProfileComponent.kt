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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flexcode.githubgraphql.domain.models.GithubCount
import com.flexcode.githubgraphql.domain.models.GithubUser
import com.flexcode.graphql.designsystem.compossables.GithubProfilePicture
import com.flexcode.graphql.designsystem.compossables.GithubText
import com.flexcode.graphql.designsystem.previews.GithubPreviews
import com.flexcode.graphql.designsystem.theme.GithubGraphQlTheme
import com.flexcode.graphql.designsystem.theme.spacing

@Composable
fun UserProfileComponent(modifier: Modifier = Modifier, user: GithubUser?) {
    Row(
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GithubProfilePicture(url = user?.avatarUrl, size = 80.dp)

        Column(
            modifier.padding(start = spacing.medium),
        ) {
            GithubText(
                style = MaterialTheme.typography.titleMedium,
                text = user?.name ?: "",
                fontSize = 20.sp,
            )
            Spacer(modifier = modifier.height(spacing.extraSmall))
            GithubText(
                fontSize = 14.sp,
                text = user?.login ?: "",
                style = MaterialTheme.typography.bodyLarge,
                textColor = MaterialTheme.colorScheme.outline,
            )
        }
    }
}

@GithubPreviews
@Composable
fun UserProfileComponentPreview() {
    GithubGraphQlTheme {
        UserProfileComponent(
            user = sampleUser,
        )
    }
}
val sampleUser = GithubUser(
    avatarUrl = null,
    bio = "Mobile engineer",
    email = "test@gmail.com",
    followers = GithubCount(12),
    following = GithubCount(23),
    location = "London",
    login = "Felix-kariuki",
    name = "John Doe",
    starredRepositories = GithubCount(9),
    twitterUsername = "@felixkariuki_",
    websiteUrl = "https:example.com", organizations = null, repositories = null,

)
