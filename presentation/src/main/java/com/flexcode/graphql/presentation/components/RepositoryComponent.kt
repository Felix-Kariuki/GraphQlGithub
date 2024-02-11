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
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flexcode.graphql.designsystem.compossables.GithubText
import com.flexcode.graphql.designsystem.previews.GithubPreviews
import com.flexcode.graphql.designsystem.theme.GithubGraphQlTheme
import com.flexcode.graphql.designsystem.theme.spacing
import com.flexcode.githubgraphql.domain.models.GithubCount
import com.flexcode.githubgraphql.domain.models.SingleRepository
import com.flexcode.graphql.designsystem.R.drawable as AppDrawable

@Composable
fun RepositoryComponent(
    modifier: Modifier = Modifier,
    singleRepository: SingleRepository?,
) {
    Column(
        modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(spacing.small),
    ) {
        GithubText(
            fontSize = 17.sp,
            style = MaterialTheme.typography.titleSmall,
            text = singleRepository?.name ?: "",
        )
        if (singleRepository?.description?.isNotEmpty() == true) {
            GithubText(
                fontSize = 15.sp,
                text = singleRepository.description ?: "",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.small),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = AppDrawable.ic_outline_star),
                contentDescription = null,
                modifier = modifier.size(18.dp),
                tint = MaterialTheme.colorScheme.onBackground,
            )
            GithubText(fontSize = 14.sp, text = "${singleRepository?.stargazers?.totalCount ?: 0}")
        }
    }
}

@Composable
fun ProfileRepositoryComponent(
    modifier: Modifier = Modifier,
    singleRepository: SingleRepository?,
) {
    Column(
        modifier
            .background(MaterialTheme.colorScheme.background)
            .border(.5.dp, Color.Gray.copy(.1f), RoundedCornerShape(8.dp))
            .widthIn(260.dp, 260.dp).padding(spacing.medium).heightIn(70.dp, 70.dp),
        verticalArrangement = Arrangement.spacedBy(spacing.extraSmall),
    ) {
        GithubText(
            fontSize = 17.sp,
            style = MaterialTheme.typography.titleSmall,
            text = singleRepository?.name ?: "",
        )
        if (singleRepository?.description?.isNotEmpty() == true) {
            GithubText(
                fontSize = 15.sp,
                text = singleRepository.description ?: "",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                maxLines = 1,
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.small),
        ) {
            Icon(
                painter = painterResource(id = AppDrawable.ic_star),
                contentDescription = null,
                modifier = modifier.size(20.dp),
                tint = Color.Yellow,
            )
            GithubText(fontSize = 14.sp, text = "${singleRepository?.stargazers?.totalCount ?: 0}")
        }
    }
}

@GithubPreviews
@Composable
fun ProfileRepositoryComponentPreView() {
    GithubGraphQlTheme {
        ProfileRepositoryComponent(singleRepository = sampleRepo)
    }
}

@GithubPreviews
@Composable
fun RepositoryComponentpreView() {
    GithubGraphQlTheme {
        RepositoryComponent(singleRepository = sampleRepo)
    }
}

val sampleRepo = SingleRepository(
    description = "Test description for repo with two lines lets see ",
    name = "Yummy",
    pushedAt = null,
    stargazers = GithubCount(90),
)
