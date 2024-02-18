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

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.flexcode.graphql.designsystem.previews.GithubPreviews
import com.flexcode.graphql.designsystem.theme.GithubGraphQlTheme

@Composable
fun GithubText(
    modifier: Modifier = Modifier,
    text: String = "Github",
    fontSize: TextUnit = 16.sp,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        text = text,
        style = style,
        fontSize = fontSize,
        color = textColor,
        maxLines = maxLines,
        modifier = modifier,
    )
}

@GithubPreviews
@Composable
fun GithubTextPreview() {
    GithubGraphQlTheme {
        GithubText()
    }
}
