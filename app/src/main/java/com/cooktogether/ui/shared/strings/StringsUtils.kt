package com.cooktogether.ui.shared.strings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.intl.Locale
import cafe.adriel.lyricist.LanguageTag
import cafe.adriel.lyricist.Lyricist
import cafe.adriel.lyricist.ProvideStrings

val cookTogetherStrings: Map<LanguageTag, Strings> =
    mapOf(
        Locales.PT to PtStrings,
    )

val LocalStrings: ProvidableCompositionLocal<Strings> =
    staticCompositionLocalOf { cookTogetherStrings[Locale.current.toLanguageTag()] ?: PtStrings }

@Composable
internal fun rememberCookTogetherStrings(
    defaultLanguageTag: LanguageTag = Locales.PT,
    currentLanguageTag: LanguageTag = Locale.current.toLanguageTag(),
): Lyricist<Strings> =
    cafe.adriel.lyricist.rememberStrings(
        cookTogetherStrings,
        defaultLanguageTag,
        currentLanguageTag,
    )

@Composable
internal fun ProvideCookTogetherStrings(
    lyricist: Lyricist<Strings> = rememberCookTogetherStrings(),
    content: @Composable () -> Unit,
) {
    ProvideStrings(lyricist, LocalStrings, content)
}

@Composable
internal fun rememberStrings(): Strings {
    val lyricist = rememberCookTogetherStrings()
    val state by lyricist.state.collectAsState()

    return remember(state) {
        state.strings
    }
}