package com.cooktogether.ui.flows.addrecipes.steps

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cooktogether.ui.flows.addrecipes.viewmodels.AddRecipeViewModel

@Composable
fun RecipeNameStep(
    onNext: (String) -> Unit,
    viewModel: AddRecipeViewModel
) {
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    RecipeStepScreen(
        title = "Qual o nome da sua receita?",
        buttonText = "Próximo",
        onButtonClick = {
            focusManager.clearFocus()
            viewModel.setRecipeName(text)
            onNext(text)
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .clickable { focusManager.clearFocus() }
            ) {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Digite aqui...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(FocusRequester()),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                )
            }
        }
    )
}
