package com.hellofresh.task2.data.remote

import com.hellofresh.task2.common.AppConstants.API_GET_PARAM
import com.hellofresh.task2.data.model.Recipe
import retrofit2.Response
import retrofit2.http.GET

/**
 * This is a service that provides access to recipes.
 */
interface RecipeService {
    @GET(API_GET_PARAM)
    suspend fun getRecipes(): Response<List<Recipe>>
}