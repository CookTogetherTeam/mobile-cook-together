package com.cooktogether.ui.flows.addrecipes.steps

import android.content.Context
import android.net.Uri
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

@Composable
fun RecipeImageStep(onFinish: () -> Unit) {
    val focusManager = LocalFocusManager.current
    var imagePath by remember { mutableStateOf<String?>(null) }

    RecipeStepScreen(
        title = "Adicione uma imagem para sua receita",
        buttonText = "Finalizar",
        onButtonClick = {
            focusManager.clearFocus()
            onFinish()
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                imagePath?.let {
                    Text("Imagem Selecionada: $it")
                } ?: run {
                    ImagePicker(onImageSelected = { path ->
                        imagePath = path
                    })
                }
            }
        }
    )
}


@Composable
fun ImagePicker(onImageSelected: (String) -> Unit) {
    val context = LocalContext.current
    val imageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            val imagePath = uriToBase64(it, context)
            onImageSelected(imagePath)
        }
    }

    Button(onClick = { imageLauncher.launch("image/*") }) {
        Text("Escolher Imagem")
    }
}

fun uriToBase64(uri: Uri, context: Context): String {
    val inputStream = context.contentResolver.openInputStream(uri)
    val bytes = inputStream?.readBytes()
    return Base64.encodeToString(bytes, Base64.DEFAULT)
}