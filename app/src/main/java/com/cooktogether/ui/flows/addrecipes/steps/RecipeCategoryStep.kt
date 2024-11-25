package com.cooktogether.ui.flows.addrecipes.steps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

enum class Category(val id: Int, val label: String) {
    Highlights(1, "Destaques"),
    Breakfast(2, "Café da Manhã"),
    Dessert(3, "Sobremesa"),
    Massas(4, "Massas")
}

@Composable
fun AddCategoryStep(onCategorySelected: (Category) -> Unit) {
    var isModalOpen by remember { mutableStateOf(false) }

    fun showCategoryModal() {
        isModalOpen = true
    }

    RecipeStepScreen(
        title = "Categoria",
        buttonText = "Próximo",
        onButtonClick = { showCategoryModal() },
        content = {
            Button(
                onClick = { showCategoryModal() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Selecione uma categoria", color = Color.White)
            }
        }
    )

    if (isModalOpen) {
        CategorySelectionModal(
            onDismiss = { isModalOpen = false },
            onCategorySelected = { category ->
                onCategorySelected(category)
                isModalOpen = false
            }
        )
    }
}

@Composable
fun CategorySelectionModal(
    onDismiss: () -> Unit,
    onCategorySelected: (Category) -> Unit) {

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Selecione uma categoria",
                    style = MaterialTheme.typography.bodyMedium
                )

                Category.values().forEach { category ->
                    Button(
                        onClick = {
                            onCategorySelected(category)
                            onDismiss()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(category.label)
                    }
                }

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text("Cancelar")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RecipeCategoryPreview() {
    AddCategoryStep(
        onCategorySelected = {}
    )
}