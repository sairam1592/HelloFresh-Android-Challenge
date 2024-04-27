package com.hellofresh.task2.data.datasource.remote

import com.hellofresh.task2.data.model.Recipe
import domain.usecase.model.DataResult

/**
 * This is a data source interface that provides access to recipes.
 */
interface RecipeRemoteDataSource {
    suspend fun getRecipes(): DataResult<List<Recipe>>
}