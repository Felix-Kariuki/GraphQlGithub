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

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.flexcode.graphql.designsystem.R

// Set of Material typography styles to start with
// val Typography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    )
//    /* Other default text styles to override
//    titleLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 22.sp,
//        lineHeight = 28.sp,
//        letterSpacing = 0.sp
//    ),
//    labelSmall = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Medium,
//        fontSize = 11.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.5.sp
//    )
//    */
//
//
// )
val Typography =
    Typography(
        bodyLarge =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.notosansregular)),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.5.sp,
            lineHeight = 20.sp,
        ),
        bodyMedium =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.notosanslight)),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.5.sp,
            lineHeight = 20.sp,
        ),
        bodySmall =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.notosansextralight)),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.5.sp,
            lineHeight = 20.sp,
        ),
        labelMedium =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.notosansthin)),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.5.sp,
            lineHeight = 20.sp,
        ),
        titleLarge =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.notosansmedium)),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            letterSpacing = 0.sp,
            lineHeight = 20.sp,
        ),
        titleMedium =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.notosansmedium)),
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            letterSpacing = 0.sp,
            lineHeight = 20.sp,
        ),
        titleSmall =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.notosansregular)),
            fontWeight = FontWeight.ExtraLight,
            fontSize = 18.sp,
            letterSpacing = 0.sp,
            lineHeight = 20.sp,
        ),
        labelSmall =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.notosansthin)),
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 0.5.sp,
            lineHeight = 20.sp,
        ),
    )
