package com.cooktogether.ui.flows.home.viewmodels

import RecipesMapper.toRecipesHomeState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cooktogether.common.utils.RequestResult
import com.cooktogether.data.mappers.RecipeResponseMapper.toDomain
import com.cooktogether.data.network.RecipesService
import com.cooktogether.ui.flows.home.steps.state.RecipesHomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesHomeViewModel @Inject constructor(
    private val service: RecipesService
) : ViewModel() {

    private val _recipesHomeState = MutableLiveData<RequestResult<RecipesHomeState>>()
    val recipesHomeState: LiveData<RequestResult<RecipesHomeState>> = _recipesHomeState

    fun fetchRecipes() {
        _recipesHomeState.postValue(RequestResult.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val allRecipes = service.fetchAllRecipes()

                val state = allRecipes.toDomain().toRecipesHomeState()
                _recipesHomeState.postValue(RequestResult.Success(state))

            } catch (e: Exception) {
                _recipesHomeState.postValue(RequestResult.Error(e))
            }
        }
    }
}

