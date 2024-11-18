package com.cooktogether.ui.flows.addrecipes.steps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cooktogether.ui.flows.addrecipes.viewmodels.AddRecipeViewModel

@Composable
fun StepByStepScreen(
    onNext: () -> Unit,
    viewModel: AddRecipeViewModel = hiltViewModel()
) {
    val steps = remember { mutableStateListOf("") }
    val focusManager = LocalFocusManager.current
    val focusRequesters = remember { mutableStateListOf<FocusRequester>() }

    LaunchedEffect(steps.size) {
        focusRequesters.lastOrNull()?.requestFocus()
    }

    RecipeStepScreen(
        title = "Qual o Passo a Passo da sua receita?",
        buttonText = "Próximo",
        onButtonClick = {
            focusManager.clearFocus()
            viewModel.setSteps(steps)
            onNext()
        },
        content = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                steps.forEachIndexed { index, step ->
                    val focusRequester = remember { FocusRequester() }

                    if (focusRequesters.size <= index) {
                        focusRequesters.add(focusRequester)
                    }

                    TextField(
                        value = step,
                        onValueChange = { newValue -> steps[index] = newValue },
                        label = { Text("Passo ${index + 1}") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequesters[index]),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusRequesters.getOrNull(index + 1)?.requestFocus()
                            }
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        )
                    )
                }

                Button(
                    onClick = {
                        steps.add("")
                        focusRequesters.lastOrNull()?.requestFocus()
                    },
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    Text("+ Adicionar Passos")
                }
            }
        }
    )
}
