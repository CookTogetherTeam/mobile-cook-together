package com.cooktogether.ui.flows.details.step

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cooktogether.R
import com.cooktogether.common.utils.RequestResult
import com.cooktogether.ui.flows.details.IngredientState
import com.cooktogether.ui.flows.details.RecipeDetailsState
import com.cooktogether.ui.flows.details.resources.recipeDetailsStrings
import com.cooktogether.ui.flows.details.viewmodels.RecipeDetailsViewModel
import com.cooktogether.ui.flows.tabNavigation.tabs.custom.CookTogetherTab
import com.cooktogether.ui.flows.tabNavigation.tabs.ui.resources.tabNavigationStrings
import com.cooktogether.ui.shared.components.steps.error.ErrorView
import com.cooktogether.ui.shared.components.steps.error.errorViewStrings
import com.cooktogether.ui.shared.components.steps.loading.LoadingView
import com.cooktogether.ui.shared.components.steps.loading.loadingViewStrings

class RecipeDetailsScreen(
    val id: Int,
) : CookTogetherTab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(ImageVector.vectorResource(R.drawable.ic_home))
            val option =
                TabOptions(
                    index = 0u,
                    title = tabNavigationStrings.home,
                    icon = icon,
                )
            return remember { option }
        }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(innerPadding: PaddingValues) {
        val viewModel: RecipeDetailsViewModel = hiltViewModel()
        val recipesState by viewModel.recipeDetailsState.observeAsState()
        val navigator = LocalNavigator.current

        LaunchedEffect(Unit) {
            viewModel.fetchDetails(id)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navigator?.pop()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "Voltar"
                            )
                        }
                    }
                )
            },
            content = { padding ->
                when (val state = recipesState) {
                    is RequestResult.Loading -> {
                        LoadingView(loadingViewStrings.getDetails)
                    }

                    is RequestResult.Success<RecipeDetailsState> -> {
                        RecipeDetailsStep(
                            state = state.data,
                            modifier = Modifier.padding(padding)
                        )
                    }

                    is RequestResult.Error -> {
                        ErrorView(state.exception.localizedMessage)
                    }

                    null -> ErrorView(errorViewStrings.unknownError)
                }
            }
        )
    }
}
@Composable
fun RecipeDetailsStep(
    modifier: Modifier = Modifier,
    state: RecipeDetailsState
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            RecipeImage(state.imageUrl)
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = state.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Text(
                text = state.author,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            IngredientsSection(state.ingredients)
            Spacer(modifier = Modifier.height(16.dp))
            PreparationStepsSection(state.steps)
        }
    }
}

@Composable
fun RecipeImage(imageUrl: String?) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = "Imagem da Receita",
        placeholder = painterResource(id = R.drawable.noimage),
        error = painterResource(id = R.drawable.noimage),
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    )
}

@Composable
fun IngredientsSection(ingredients: List<IngredientState>) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = recipeDetailsStrings.ingredients,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        ingredients.forEach { ingredient ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = ingredient.quantity.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                )
                Text(
                    text = ingredient.unit,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(2f)
                )
                Spacer(
                    modifier = Modifier.weight(0.5f)
                )
                Text(
                    text = ingredient.name,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(3f),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Composable
fun PreparationStepsSection(steps: List<String>) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = recipeDetailsStrings.preparationSteps,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        steps.forEach { step ->
            ElevatedCard(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = step,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRecipeDetailsScreen() {
    val mockState = RecipeDetailsState(
        imageUrl = "https://via.placeholder.com/150",
        title = "Torta de Maçã",
        author = "Chef Angela",
        ingredients = listOf(
            IngredientState(quantity = 10.0, unit = "xícaras", name = "Farinha"),
            IngredientState(quantity = 3.0, unit = "unidades", name = "Maçã"),
            IngredientState(quantity = 1.0, unit = "colher de sopa", name = "Canela")
        ),
        steps = listOf(
            "Misture os ingredientes secos.",
            "Adicione as maçãs e misture.",
            "Coloque no forno por 40 minutos."
        )
    )

    RecipeDetailsStep(
        state = mockState,
        modifier = Modifier.fillMaxSize()
    )
}
