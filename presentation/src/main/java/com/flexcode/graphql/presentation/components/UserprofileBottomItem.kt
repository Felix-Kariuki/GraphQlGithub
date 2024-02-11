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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.flexcode.graphql.designsystem.compossables.GithubText
import com.flexcode.graphql.designsystem.previews.GithubPreviews
import com.flexcode.graphql.designsystem.theme.GithubGraphQlTheme
import com.flexcode.graphql.designsystem.theme.spacing
import com.flexcode.graphql.designsystem.utils.noRippleClick
import com.flexcode.graphql.presentation.state.GithubState
import com.flexcode.graphql.designsystem.R.drawable as AppDrawable

@Composable
fun UserprofileBottomItem(
    modifier: Modifier = Modifier,
    state: GithubState,
    onSeeAllClick: () -> Unit,
) {
    Column(
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
    ) {
//        Row(
//            modifier
//                .fillMaxWidth()
//                .noRippleClick { onSeeAllClick()},
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            GithubText(
//                text = "Repositories",
//                style = MaterialTheme.typography.titleSmall,
//                fontSize = 17.sp
//            )
//            GithubText(
//                text = "See All",
//                style = MaterialTheme.typography.titleSmall,
//                fontSize = 14.sp
//            )
//        }

//        Spacer(modifier = modifier.height(spacing.medium))
//        LazyRow(horizontalArrangement = Arrangement.spacedBy(spacing.bigSmall)) {
//            state.repositories?.nodes?.size?.let {
//                items(it) { index ->
//                    ProfileRepositoryComponent(singleRepository = state.repositories.nodes[index])
//                }
//            }
//        }

        Spacer(modifier = modifier.height(spacing.medium))

        RepoCountItem(
            count = "${state.githubUser?.repositories?.totalCount ?: 0}",
            icon = AppDrawable.ic_repositories,
            modifier = modifier.noRippleClick { onSeeAllClick() },
        )
        RepoCountItem(
            count = "${state.githubUser?.organizations?.totalCount ?: 0}",
            text = "Organizations",
            icon = AppDrawable.ic_house,
        )
        RepoCountItem(
            count = "${state.githubUser?.starredRepositories?.totalCount ?: 0}",
            text = "Starred",
        )
    }
}

@Composable
fun RepoCountItem(
    modifier: Modifier = Modifier,
    icon: Int = AppDrawable.ic_outline_star,
    text: String = "Repositories",
    count: String,
) {
    Row(
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = spacing.medium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.medium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = text,
                modifier = modifier.size(28.dp),
                tint = MaterialTheme.colorScheme.onBackground,
            )

            GithubText(text = text, style = MaterialTheme.typography.bodyLarge)
        }

        GithubText(text = count, style = MaterialTheme.typography.bodyLarge)
    }
}

@GithubPreviews
@Composable
fun RepoCountItemPreview() {
    GithubGraphQlTheme {
        RepoCountItem(count = "90")
    }
}

@GithubPreviews
@Composable
fun UserprofileBottomItemPreview() {
    GithubGraphQlTheme {
        UserprofileBottomItem(state = GithubState(), onSeeAllClick = {})
    }
}
