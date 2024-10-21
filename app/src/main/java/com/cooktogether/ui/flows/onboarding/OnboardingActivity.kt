package com.cooktogether.ui.flows.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import com.cooktogether.ui.flows.tabNavigation.TabNavigationActivity

class OnboardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigator(
                screen = OnboardingStep(),
            )
        }
    }

    fun navigateToTabNavigationActivity() {
        val intent = Intent(this, TabNavigationActivity::class.java)
        startActivity(intent)
        finish()
    }
}