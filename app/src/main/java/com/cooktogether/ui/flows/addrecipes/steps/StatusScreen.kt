package com.cooktogether.ui.flows.addrecipes.steps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

@Composable
fun StatusScreen(
    title: String,
    message: String,
    buttonText: String = "",
    onButtonClick: (() -> Unit)? = null,
    isSuccess: Boolean = true
) {
    val navigator = LocalNavigator.currentOrThrow

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = MaterialTheme.shapes.medium,
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(if (isSuccess) Color.Green.copy(alpha = 0.1f) else Color.Red.copy(alpha = 0.1f))
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.displaySmall,
                        color = if (isSuccess) Color.Green else Color.Red
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodySmall
                    )

                    if (onButtonClick != null) {
                        Spacer(modifier = Modifier.height(32.dp))

                        Button(onClick = {
                            onButtonClick()
                            navigator.pop()
                        }) {
                            Text(text = buttonText)
                        }
                    }
                }
            }
        }
    }
}

class RecipeSavedScreen : Screen {
    @Composable
    override fun Content() {
        StatusScreen(
            title = "Receita salva com sucesso!",
            message = "Sua receita foi adicionada com sucesso à coleção.",
            isSuccess = true
        )
    }
}

class RecipeSaveErrorScreen(
    private val onButtonClick: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        StatusScreen(
            title = "Erro ao salvar a receita",
            message = "Ocorreu um erro ao tentar salvar sua receita. Tente novamente.",
            buttonText = "Tentar novamente",
            onButtonClick = onButtonClick,
            isSuccess = false
        )
    }
}
