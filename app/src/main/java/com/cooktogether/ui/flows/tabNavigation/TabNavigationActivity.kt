package com.cooktogether.ui.flows.tabNavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.cooktogether.ui.flows.tabNavigation.tabs.custom.CurrentCookTogetherTab
import com.cooktogether.ui.flows.tabNavigation.tabs.ui.AddRecipeTab
import com.cooktogether.ui.flows.tabNavigation.tabs.ui.HomeTab
import com.cooktogether.ui.flows.tabNavigation.tabs.ui.MyRecipesTab
import com.cooktogether.ui.flows.tabNavigation.tabs.ui.ProfileTab
import com.cooktogether.ui.flows.tabNavigation.tabs.ui.SearchTab
import com.cooktogether.ui.shared.strings.ProvideCookTogetherStrings
import com.cooktogether.ui.theme.CookTogetherTheme

class TabNavigationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CookTogetherTheme {
                ProvideCookTogetherStrings {
                    Content()
                }
            }
        }
    }

    @Composable
    fun Content() {
        TabNavigator(
            HomeTab,
        ) { tabNavigator ->
            Scaffold(
                content = { padding ->
                    CurrentCookTogetherTab(padding)
                },
                bottomBar = {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.background,
                    ) {
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(SearchTab)
                        TabNavigationItem(AddRecipeTab)
                        TabNavigationItem(MyRecipesTab)
                        TabNavigationItem(ProfileTab)
                    }
                },
            )
        }
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current
        NavigationBarItem(
            selected = tabNavigator.current.key == tab.key,
            onClick = { tabNavigator.current = tab },
            icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) },
            colors =
                NavigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    selectedIndicatorColor = MaterialTheme.colorScheme.background,
                    unselectedIconColor = MaterialTheme.colorScheme.secondary,
                    unselectedTextColor = MaterialTheme.colorScheme.secondary,
                    disabledIconColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.38f),
                    disabledTextColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.38f),
                ),
            label = {
                Text(
                    text = tab.options.title,
                    style =
                        TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(500),
                            textAlign = TextAlign.Center,
                        ),
                )
            },
        )
    }
}