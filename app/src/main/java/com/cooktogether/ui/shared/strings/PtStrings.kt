package com.cooktogether.ui.shared.strings

import cafe.adriel.lyricist.LyricistStrings
import com.cooktogether.ui.flows.onboarding.resources.OnboardingStepStrings
import com.cooktogether.ui.flows.onboarding.resources.onboardingStepStrings
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
        onboardingStep = OnboardingStepStrings(
            header = "Diga adeus para o seu caderno de receitas",
            subtitle = "O CookTogether facilita sua vida na cozinha.",
            firstTopic = "Acesse um catálogo diversificado de receitas em um só lugar",
            secondTopic = "Compartilhe suas receitas favoritas",
            continueButton = "Continuar",
            loginButton = "Login/Cadastre-se"
        )
    )