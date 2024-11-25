package com.cooktogether.ui.flows.explore.steps

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.cooktogether.R
import com.cooktogether.common.utils.RequestResult
import com.cooktogether.ui.flows.explore.steps.state.ExploreRecipesState
import com.cooktogether.ui.flows.explore.viewmodels.ExploreCardsViewModel
import com.cooktogether.ui.flows.tabNavigation.tabs.custom.CookTogetherTab
import com.cooktogether.ui.flows.tabNavigation.tabs.ui.resources.tabNavigationStrings
import com.cooktogether.ui.shared.components.steps.CTRecipeCardComponentStep
import com.cooktogether.ui.shared.components.steps.error.ErrorView
import com.cooktogether.ui.shared.components.steps.error.errorViewStrings
import com.cooktogether.ui.shared.components.steps.loading.LoadingView
import com.cooktogether.ui.shared.components.steps.loading.loadingViewStrings
import com.cooktogether.ui.theme.CTColor

data class ExploreListCardsScreen(
    val categoryId: Int? = null,
    val categoryTitle: String? = null
) : CookTogetherTab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(ImageVector.vectorResource(R.drawable.ic_search))
            val option =
                TabOptions(
                    index = 1u,
                    title = tabNavigationStrings.search,
                    icon = icon,
                )
            return remember { option }
        }

    @Composable
    override fun Content(innerPadding: PaddingValues) {
        ExploreListCardsStep(
            categoryId = categoryId,
            categoryTitle = categoryTitle,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ExploreListCardsStep(
    modifier: Modifier = Modifier,
    categoryId: Int? = null,
    categoryTitle: String? = null,
    viewModel: ExploreCardsViewModel = hiltViewModel()
) {
    val recipesState by viewModel.categoryRecipesState.observeAsState()
    val searchRecipesState by viewModel.searchRecipesState.observeAsState()
    val currentSearchQuery by viewModel.currentSearchQuery.observeAsState()
    val inputValue = remember { mutableStateOf(currentSearchQuery.orEmpty()) }

    LaunchedEffect(categoryId) {
        if (categoryId != null) {
            viewModel.fetchRecipesByCategory(categoryId, categoryTitle)
        }
    }

    if (categoryId == null || categoryTitle == null) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            SearchField(inputValue)
            LaunchedEffect(inputValue.value) {
                if (inputValue.value.isNotBlank()) {
                    viewModel.fetchRecipesByName(inputValue.value)
                } else {
                    viewModel.clearSearchResults()
                }
            }
            when (val state = searchRecipesState) {
                is RequestResult.Loading -> {
                    LoadingView(loadingViewStrings.getRecipes)
                }
                is RequestResult.Success<ExploreRecipesState> -> {
                    if (state.data.list.isEmpty()) {
                        EmptySearchView()
                    } else {
                        RecipeCardGrid(state = state.data)
                    }
                }
                is RequestResult.Error -> {
                    ErrorView(state.exception.localizedMessage)
                }
                null -> {
                    EmptySearchView()
                }
            }
        }
    } else {
        when (val state = recipesState) {
            is RequestResult.Loading -> {
                LoadingView(loadingViewStrings.getCategoryRecipes)
            }
            is RequestResult.Success<ExploreRecipesState> -> {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    SearchField(inputValue)
                    Spacer(modifier = Modifier.height(16.dp))

                    if (inputValue.value.isBlank()) {
                        EmptySearchView()
                    } else {
                        RecipeCardGrid(state = state.data)
                    }
                }
            }
            is RequestResult.Error -> {
                ErrorView(state.exception.localizedMessage)
            }
            null -> {
                ErrorView(errorViewStrings.unknownError)
            }
        }
    }
}

@Composable
private fun RecipeCardGrid(
    state: ExploreRecipesState,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            items(state.list.size) { index ->
                CTRecipeCardComponentStep(
                    model = state.list[index]
                )
            }
        }
    )
}

@Composable
private fun SearchField(inputValue: MutableState<String>) {
    TextField(
        value = inputValue.value,
        onValueChange = { inputValue.value = it },
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = rememberVectorPainter(ImageVector.vectorResource(R.drawable.ic_search)),
                contentDescription = "Ícone de pesquisa",
                modifier = Modifier.padding(end = 8.dp)
            )
        },
        textStyle = TextStyle(
            fontSize = 20.sp,
            lineHeight = 26.sp,
            fontWeight = FontWeight(700),
            color = CTColor.Dark.color,
            textAlign = TextAlign.Start,
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x0F808080),
                ambientColor = Color(0x0F808080)
            )
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x1A808080),
                ambientColor = Color(0x1A808080)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFF2F2F2),
                shape = RoundedCornerShape(size = 16.dp)
            )
            .background(color = Color(0xFFF8F8F8), shape = RoundedCornerShape(size = 16.dp))
    )
}

@Composable
private fun EmptySearchView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.no_results),
            contentDescription = "Empty state",
            modifier = Modifier.size(96.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Comece sua pesquisa!",
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Bold,
                color = CTColor.Dark.color
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ExploreListCardsStepPreview() {
    ExploreListCardsScreen(1, "Destaques")
}