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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flexcode.githubgraphql.domain.models.GithubUser
import com.flexcode.graphql.designsystem.compossables.GithubText
import com.flexcode.graphql.designsystem.previews.GithubPreviews
import com.flexcode.graphql.designsystem.theme.GithubGraphQlTheme
import com.flexcode.graphql.designsystem.theme.spacing
import com.flexcode.graphql.designsystem.utils.noRippleClick
import com.flexcode.graphql.designsystem.R.drawable as AppDrawable

@Composable
fun UserProfileExtras(
    modifier: Modifier = Modifier,
    user: GithubUser?,
    onFollowersClick: () -> Unit,
    onFollowingClick: () -> Unit,
) {
    Column(
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(space = spacing.small),
    ) {
        UserProfileComponent(user = user)

        Spacer(modifier = modifier.height(spacing.small))
        GithubText(
            text = user?.bio ?: "",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 15.sp,
        )

        Spacer(modifier = modifier.height(spacing.extraSmall))
        if (user?.location?.isNotEmpty() == true) {
            TextWithIcon(text = user.location ?: "")
        }
        if (user?.websiteUrl?.isNotEmpty() == true) {
            TextWithIcon(text = user.websiteUrl ?: "", icon = AppDrawable.ic_link)
        }
        if (user?.email?.isNotEmpty() == true) {
            TextWithIcon(text = user.email ?: "", icon = AppDrawable.ic_email)
        }
        if (user?.twitterUsername?.isNotEmpty() == true) {
            TextWithIcon(text = user.twitterUsername ?: "", icon = AppDrawable.ic_twitter)
        }

        FollowersFollowing(
            followers = "${user?.followers?.totalCount ?: 0}",
            following = "${user?.following?.totalCount ?: 0}",
            onFollowingClick = onFollowingClick,
            onFollowersClick = onFollowersClick,
        )
    }
}

@Composable
fun TextWithIcon(
    icon: Int = AppDrawable.ic_location,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.outline,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(spacing.small),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 2.dp),
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = text,
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier.size(20.dp),
        )

        GithubText(
            text = text,
            textColor = textColor,
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
        )
    }
}

@Composable
fun FollowersFollowing(
    icon: Int = AppDrawable.ic_person,
    followers: String,
    following: String,
    textColor: Color = MaterialTheme.colorScheme.outline,
    onFollowersClick: () -> Unit,
    onFollowingClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(spacing.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = followers,
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier.size(24.dp),
        )

        Row(
            modifier = Modifier.noRippleClick { onFollowersClick() }.testTag("followers_text"),
        ) {
            GithubText(
                text = followers,
                textColor = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge,

            )
            GithubText(
                text = " followers",
                textColor = textColor,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W700),
            )
        }

        Row(
            modifier = Modifier.noRippleClick { onFollowingClick() }.testTag("following_text"),
        ) {
            GithubText(
                text = following,
                textColor = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge,
            )
            GithubText(
                text = " following",
                textColor = textColor,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W700),
            )
        }
    }
}

@GithubPreviews
@Composable
fun UserProfileExtrasPreview() {
    GithubGraphQlTheme {
        UserProfileExtras(user = sampleUser, onFollowingClick = {}, onFollowersClick = {})
    }
}

@GithubPreviews
@Composable
fun FollowersFollowingPreview() {
    GithubGraphQlTheme {
        FollowersFollowing(
            followers = "12",
            following = "23",
            onFollowersClick = {},
            onFollowingClick = {},
        )
    }
}

@GithubPreviews
@Composable
fun TextWithIconPreview() {
    GithubGraphQlTheme {
        TextWithIcon(text = "Location")
    }
}
