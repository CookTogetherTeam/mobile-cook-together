package com.cooktogether.ui.flows.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.cooktogether.R
import com.cooktogether.ui.flows.onboarding.resources.onboardingStepStrings
import com.cooktogether.ui.theme.CTColor

internal class OnboardingStep : Screen {
    @Composable
    override fun Content() {
        val scrollState = rememberScrollState()
        val context = LocalContext.current

        Column(
            Modifier
                .background(color = CTColor.Background.color)
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {
            Image(
                painter = painterResource(id = R.drawable.onboarding_image),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier =
                Modifier
                    .width(393.dp)
                    .height(318.dp),
            )
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = onboardingStepStrings.header,
                        style =
                            TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 36.sp,
                                fontWeight = FontWeight(700),
                                color = CTColor.Dark.color,
                                textAlign = TextAlign.Center,
                            ),
                    )
                    Text(
                        text = onboardingStepStrings.subtitle,
                        style =
                            TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 26.sp,
                                fontWeight = FontWeight(700),
                                color = CTColor.Dark.color,
                                textAlign = TextAlign.Center,
                            ),
                    )
                }
                IconRow(
                    icon = R.drawable.ic_recipe,
                    text = onboardingStepStrings.firstTopic,
                )
                IconRow(
                    icon = R.drawable.ic_share,
                    text = onboardingStepStrings.secondTopic,
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    CustomButton(
                        text = onboardingStepStrings.continueButton,
                        backgroundColor = CTColor.PrimaryButton.color,
                        onClick = {
                            (context as? OnboardingActivity)?.navigateToTabNavigationActivity()
                        },
                    )
                    CustomButton(
                        text = onboardingStepStrings.loginButton,
                        backgroundColor = CTColor.SecondaryButton.color,
                        onClick = {  },
                    )
                }
            }
        }
    }

    @Composable
    private fun IconRow(
        icon: Int,
        text: String,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(17.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier =
                Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .background(
                        color = CTColor.PrimaryOnColor.color,
                        shape = RoundedCornerShape(size = 30.dp),
                    )
                    .padding(start = 15.dp, top = 15.dp, end = 15.dp, bottom = 15.dp),
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                )
            }
            Text(
                text = text,
                style =
                    TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(500),
                        color = CTColor.Dark.color,
                    ),
            )
        }
    }
}

@Composable
fun CustomButton(
    text: String,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text = text, fontSize = 18.sp, color = Color.Black)
    }
}

@Composable
@Preview
fun OnboardingStepPreview() {
    OnboardingStep().Content()
}