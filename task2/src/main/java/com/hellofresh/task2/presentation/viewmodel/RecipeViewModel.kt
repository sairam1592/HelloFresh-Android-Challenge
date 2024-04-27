package com.hellofresh.task2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellofresh.task2.common.AppConstants.ERROR_MESSAGE
import com.hellofresh.task2.presentation.view.states.RecipeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.usecase.GetRecipesUseCase
import domain.usecase.model.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This is a view model that provides access to recipes.
 * @param getRecipesUseCase The use case that provides a list of recipes.
 */
@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val ioDispatcher: CoroutineDispatcher
) :
    ViewModel() {

    private val _recipeState = MutableStateFlow<RecipeViewState>(RecipeViewState.Loading)
    val recipeState: StateFlow<RecipeViewState> = _recipeState.asStateFlow()

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        viewModelScope.launch(ioDispatcher) {
            _recipeState.value = RecipeViewState.Loading
            val result = runCatching { getRecipesUseCase() }
            result.onSuccess { dataResult ->
                when (dataResult) {
                    is DataResult.Success -> _recipeState.value =
                        RecipeViewState.Success(dataResult.data)

                    is DataResult.Error -> _recipeState.value =
                        RecipeViewState.Error(dataResult.exception.message ?: ERROR_MESSAGE)
                }
            }
            result.onFailure { error ->
                _recipeState.value = RecipeViewState.Error(error.message ?: ERROR_MESSAGE)
            }
        }
    }
}