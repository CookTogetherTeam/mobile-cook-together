package com.cooktogether.ui.flows.addrecipes.steps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.cooktogether.data.models.Ingredient
import com.cooktogether.ui.flows.addrecipes.viewmodels.AddRecipeViewModel

@Composable
fun AddIngredientsStep(
    onNext: () -> Unit,
    viewModel: AddRecipeViewModel
) {
    val ingredients = remember { mutableStateListOf<Ingredient>() }
    val focusManager = LocalFocusManager.current
    val focusRequesters = remember { mutableStateListOf<FocusRequester>() }

    LaunchedEffect(Unit) {
        if (ingredients.isEmpty()) {
            ingredients.add(Ingredient("", 0.0, ""))
        }
    }

    RecipeStepScreen(
        title = "Adicione os ingredientes",
        buttonText = "Próximo",
        onButtonClick = {
            viewModel.setIngredients(ingredients)
            focusManager.clearFocus()
            onNext()
        },
        content = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ingredients.forEachIndexed { index, ingredient ->
                    val focusRequester = remember { FocusRequester() }

                    if (focusRequesters.size <= index) {
                        focusRequesters.add(focusRequester)
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        TextField(
                            value = ingredient.name,
                            onValueChange = { newValue ->
                                ingredients[index] = ingredient.copy(name = newValue)
                            },
                            label = { Text("Ingrediente") },
                            modifier = Modifier
                                .focusRequester(focusRequesters[index]),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusRequesters.getOrNull(index + 1)?.requestFocus()
                                }
                            ),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                                unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            )
                        )
                        TextField(
                            value = if (ingredient.quantity == 0.0) "" else ingredient.quantity.toString(),
                            onValueChange = { newValue ->
                                val parsedQuantity = newValue.toDoubleOrNull() ?: 0.0
                                ingredients[index] = ingredient.copy(quantity = parsedQuantity)
                            },
                            label = { Text("Quantidade") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusRequesters.getOrNull(index + 1)?.requestFocus()
                                }
                            ),
                            modifier = Modifier
                                .focusRequester(focusRequesters[index]),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                                unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            )
                        )
                        TextField(
                            value = ingredient.unit,
                            onValueChange = { newValue ->
                                ingredients[index] = ingredient.copy(unit = newValue)
                            },
                            label = { Text("Unidade") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                }
                            ),
                            modifier = Modifier
                                .focusRequester(focusRequesters[index]),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                                unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            )
                        )
                    }
                }

                Button(
                    onClick = {
                        ingredients.add(Ingredient("", 0.0, ""))
                        focusRequesters.lastOrNull()?.requestFocus()
                    },
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    Text("+ Adicionar Ingrediente")
                }
            }
        }
    )
}
