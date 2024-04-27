package com.hellofresh.task2.data.repository

import com.hellofresh.task2.data.datasource.remote.RecipeRemoteDataSourceImpl
import com.hellofresh.task2.data.model.Recipe
import domain.usecase.model.DataResult
import javax.inject.Inject

/**
 * This is a repository interface that provides access to recipes.
 */
class RecipeRepositoryImpl @Inject constructor(private val recipeRemoteDataSourceImpl: RecipeRemoteDataSourceImpl) :
    RecipeRepository {
    override suspend fun getRecipes(): DataResult<List<Recipe>> {
        return recipeRemoteDataSourceImpl.getRecipes()
    }
}