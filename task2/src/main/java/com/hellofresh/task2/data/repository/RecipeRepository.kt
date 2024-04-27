package com.hellofresh.task2.data.repository

import com.hellofresh.task2.data.model.Recipe
import domain.usecase.model.DataResult

/**
 * This is a repository interface that provides access to recipes.
 */
interface RecipeRepository {
    suspend fun getRecipes(): DataResult<List<Recipe>>
}