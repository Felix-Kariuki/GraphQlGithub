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
package com.flexcode.graphql.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Spacing for entire app to ensure consistency
 * @property [none] default spacing which is 0.dp
 * @property [extraSmall] the smalled type spacing 4.dp
 * @property [small] 8.dp spacing
 * @property[bigSmall] 12.dp
 * @property [medium] 16.dp spacing used for app's edge to edge (start,end,top,bottom) spacing
 * for holding container columns or surface
 * @property [extraMedium] 24.dp
 * @property [extraLarge]  64.dp spacing
 * @property [large]  32.dp spacing
 */
data class Spacing(
    val none: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val bigSmall: Dp = 12.dp,
    val medium: Dp = 16.dp,
    val extraMedium: Dp = 24.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 64.dp,
)

/**
 * composition local to allow passing down of spacing
 */
val LocalSpacing = compositionLocalOf { Spacing() }

/**
 * Retrieves the current [Spacing] at the call site's position in the hierarchy.
 */
val spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current

/**
 * function that returns [Spacing]
 * @return [Spacing] top avoid repetition of MaterialTheme.Spacing
 * @see spacing
 */

@Composable
fun spacing(): Spacing {
    return spacing
}
