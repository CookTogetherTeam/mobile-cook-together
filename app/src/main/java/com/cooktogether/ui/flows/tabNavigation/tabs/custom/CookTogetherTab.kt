package com.cooktogether.ui.flows.tabNavigation.tabs.custom

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator

@Composable
fun CurrentCookTogetherTab(innerPadding: PaddingValues) {
    val tabNavigator = LocalTabNavigator.current

    tabNavigator.saveableState("currentTab") {
        FadeTabTransition(
            navigator = tabNavigator,
            modifier = Modifier,
            content = { tab ->
                (tab as CookTogetherTab).Content(innerPadding)
            },
        )
    }
}

interface CookTogetherTab : Tab {
    @Composable
    fun Content(innerPadding: PaddingValues)

    @Composable
    override fun Content() {
        error("Called Content without arguments")
    }
}

typealias TabTransitionContent = @Composable AnimatedVisibilityScope.(Tab) -> Unit

@Composable
fun TabTransition(
    navigator: TabNavigator,
    transition: AnimatedContentTransitionScope<Tab>.() -> ContentTransform,
    modifier: Modifier = Modifier,
    content: TabTransitionContent,
) {
    AnimatedContent(
        targetState = navigator.current,
        transitionSpec = transition,
        modifier = modifier,
        label = "",
    ) { tab ->
        navigator.saveableState("transition", tab) {
            content(tab)
        }
    }
}

@Composable
fun FadeTabTransition(
    navigator: TabNavigator,
    modifier: Modifier = Modifier,
    animationSpec: FiniteAnimationSpec<Float> = spring(stiffness = Spring.StiffnessMediumLow),
    content: TabTransitionContent,
) {
    TabTransition(
        navigator = navigator,
        modifier = modifier,
        content = content,
        transition = { fadeIn(animationSpec = animationSpec) togetherWith fadeOut(animationSpec = animationSpec) },
    )
}