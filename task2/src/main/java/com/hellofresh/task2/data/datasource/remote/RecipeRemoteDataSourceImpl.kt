package com.hellofresh.task2.data.datasource.remote

import com.hellofresh.task2.common.AppConstants.ERROR_API_FAILED
import com.hellofresh.task2.data.model.Recipe
import com.hellofresh.task2.data.remote.RecipeService
import domain.usecase.model.DataResult
import javax.inject.Inject

/**
 * This is a data source implementation that provides access to recipes.
 */
class RecipeRemoteDataSourceImpl @Inject constructor(private val recipeService: RecipeService) :
    RecipeRemoteDataSource {
    override suspend fun getRecipes(): DataResult<List<Recipe>> {
        return try {
            val response = recipeService.getRecipes()
            if (response.isSuccessful) {
                DataResult.Success(response.body() ?: emptyList())
            } else {
                DataResult.Error(Exception(ERROR_API_FAILED))
            }
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }
}