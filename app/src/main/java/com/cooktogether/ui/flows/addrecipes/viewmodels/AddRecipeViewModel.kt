package com.cooktogether.ui.flows.addrecipes.viewmodels

import androidx.lifecycle.*
import com.cooktogether.data.mappers.RecipeResponseMapper.toRecipeDomain
import com.cooktogether.data.models.Ingredient
import com.cooktogether.data.models.RecipeRequest
import com.cooktogether.data.network.RecipesService
import com.cooktogether.domain.models.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor(
    private val recipesService: RecipesService
) : ViewModel() {

    private val _recipe = MutableLiveData<Recipe?>()
    val recipe: LiveData<Recipe?> get() = _recipe

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    private val _recipeSaved = MutableLiveData<Boolean>()
    val recipeSaved: LiveData<Boolean> = _recipeSaved

    private var recipeName: String? = null
    private var ingredients: List<Ingredient> = emptyList()
    private var steps: List<String>? = null
    private var imageBase64: String? = null

    fun setRecipeName(name: String) {
        recipeName = name
    }

    fun setIngredients(ingredientList: List<Ingredient>) {
        ingredients = ingredientList
    }

    fun setSteps(recipeSteps: List<String>) {
        steps = recipeSteps
    }

    fun setImageBase64(image: String) {
        imageBase64 = image
    }

    fun addRecipe() {
        if (recipeName.isNullOrEmpty() || ingredients.isEmpty() || steps.isNullOrEmpty()) {
            _error.postValue("Todos os campos devem ser preenchidos")
            return
        }

        val recipeRequest = RecipeRequest(
            title = recipeName ?: "",
            category = 1,
            ingredients = ingredients.map { Ingredient(it.name, it.quantity, it.unit) },
            textArea = steps ?: emptyList(),
            imageBase64 = imageBase64
        )
        
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    recipesService.createRecipe(recipeRequest)
                }
                _recipe.postValue(response.toRecipeDomain())
                _error.postValue(null)
                _recipeSaved.postValue(true)
            } catch (e: Exception) {
                _error.postValue("Erro ao adicionar a receita: ${e.message}")
                _recipe.postValue(null)
                _recipeSaved.postValue(false)
            }
        }
    }
}
