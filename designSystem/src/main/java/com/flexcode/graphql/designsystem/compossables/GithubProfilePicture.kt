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
package com.flexcode.graphql.designsystem.compossables

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.flexcode.graphql.designsystem.R
import com.flexcode.graphql.designsystem.previews.GithubPreviews
import com.flexcode.graphql.designsystem.theme.GithubGraphQlTheme

const val URL_PIC = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.cleanpng.com%2Fpng" +
    "-computer-icons-logo-portable-network-graphics-clip-6362014%2F&psig=AOvVaw02D6PHD" +
    "uXSCEY1933b1Ws9&ust=1707658101519000&source=images&cd=vfe&opi=89978449&ved=0CBMQjRxqF" +
    "woTCICNqKbwoIQDFQAAAAAdAAAAABAE"

@Composable
fun GithubProfilePicture(url: String?, size: Dp = 100.dp) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url ?: URL_PIC)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = stringResource(R.string.profile_image),
        contentScale = ContentScale.Crop,
        modifier = Modifier.clip(CircleShape).size(size),
    )
}

@GithubPreviews
@Composable
fun PicturePreview() {
    GithubGraphQlTheme {
        GithubProfilePicture(url = URL_PIC)
    }
}
