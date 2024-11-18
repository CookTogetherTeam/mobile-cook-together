package com.cooktogether.ui.flows.details.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cooktogether.common.utils.RequestResult
import com.cooktogether.data.mappers.RecipeResponseMapper.toRecipeDomain
import com.cooktogether.data.network.RecipesService
import com.cooktogether.domain.mappers.RecipeDetailsMapper.toDetailsState
import com.cooktogether.ui.flows.details.RecipeDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val service: RecipesService
) : ViewModel() {

    private val _recipeDetailsState = MutableLiveData<RequestResult<RecipeDetailsState>>()
    val recipeDetailsState: LiveData<RequestResult<RecipeDetailsState>> = _recipeDetailsState

    fun fetchDetails(id: Int) {
        _recipeDetailsState.postValue(RequestResult.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val recipe = service.getRecipeById(id)

                val state = recipe.toRecipeDomain().toDetailsState()
                _recipeDetailsState.postValue(RequestResult.Success(state))

            } catch (e: Exception) {
                _recipeDetailsState.postValue(RequestResult.Error(e))
            }
        }
    }
}