package com.cooktogether.ui.shared.strings

import cafe.adriel.lyricist.LyricistStrings
import com.cooktogether.ui.flows.tabNavigation.tabs.ui.resources.TabNavigationStrings

@LyricistStrings(languageTag = Locales.PT, default = true)
internal val PtStrings =
    Strings(
        tabNavigation =
            TabNavigationStrings(
                home = "Home",
                search = "Explorar",
                addRecipe = "Nova Receita",
                myRecipes = "Minhas Receitas",
                profile = "Perfil",
            ),
    )