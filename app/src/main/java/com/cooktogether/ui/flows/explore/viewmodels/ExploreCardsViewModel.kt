package com.cooktogether.ui.flows.explore.viewmodels

import RecipesMapper.toExploreState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cooktogether.common.utils.RequestResult
import com.cooktogether.data.mappers.RecipeResponseMapper.toDomain
import com.cooktogether.data.network.RecipesService
import com.cooktogether.ui.flows.explore.steps.state.ExploreRecipesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreCardsViewModel @Inject constructor(
    private val service: RecipesService
) : ViewModel() {

    private val _categoryRecipesState = MutableLiveData<RequestResult<ExploreRecipesState>>()
    val categoryRecipesState: LiveData<RequestResult<ExploreRecipesState>> = _categoryRecipesState

    private val _searchRecipesState = MutableLiveData<RequestResult<ExploreRecipesState>>()
    val searchRecipesState: LiveData<RequestResult<ExploreRecipesState>> = _searchRecipesState

    private val _currentSearchQuery = MutableLiveData<String?>()
    val currentSearchQuery: LiveData<String?> = _currentSearchQuery

    fun fetchRecipesByCategory(categoryId: Int, title: String? = null) {
        _categoryRecipesState.postValue(RequestResult.Loading)
        _currentSearchQuery.postValue(title)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val recipesByCategory = service.getRecipesByCategory(categoryId, title)
                val state = recipesByCategory.toDomain().toExploreState()
                _categoryRecipesState.postValue(RequestResult.Success(state))
            } catch (e: Exception) {
                _categoryRecipesState.postValue(RequestResult.Error(e))
            }
        }
    }

    fun fetchRecipesByName(title: String) {
        _searchRecipesState.postValue(RequestResult.Loading)
        _currentSearchQuery.postValue(title)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val recipesByName = service.getRecipesByName(title)
                val state = recipesByName.toDomain().toExploreState()
                _searchRecipesState.postValue(RequestResult.Success(state))
            } catch (e: Exception) {
                _searchRecipesState.postValue(RequestResult.Error(e))
            }
        }
    }

    fun clearSearchResults() {
        _searchRecipesState.postValue(null)
    }
}