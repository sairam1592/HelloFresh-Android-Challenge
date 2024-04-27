package com.hellofresh.task2.presentation.view.states

import com.hellofresh.task2.data.model.Recipe

sealed class RecipeViewState {
    object Loading : RecipeViewState()
    data class Success(val recipes: List<Recipe>) : RecipeViewState()
    data class Error(val message: String) : RecipeViewState()
}