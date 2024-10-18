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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.cooktogether.R
import com.cooktogether.ui.theme.CTColor

internal class OnboardingStep : Screen {
    @Composable
    override fun Content() {
        Column(
            Modifier
                .background(color = CTColor.Background.color)
                .fillMaxSize(),
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
                        text = "Diga adeus para o seu caderno de receitas",
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
                        text = "O CookTogether facilita sua vida na cozinha.",
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
                    text = "Acesse um catálogo diversificado de receitas em um só lugar",
                )
                IconRow(
                    icon = R.drawable.ic_search,
                    text = "Compartilhe suas receitas favoritas",
                )
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
                        ).padding(start = 15.dp, top = 15.dp, end = 15.dp, bottom = 15.dp),
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
@Preview
fun OnboardingStepPreview() {
    OnboardingStep().Content()
}