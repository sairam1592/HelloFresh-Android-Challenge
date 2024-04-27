package com.hellofresh.task1.data.model

/*
    * This is a data class that represents a recipe.
    * @param id The id of the recipe.
    * @param title The title of the recipe.
    * @param tags The tags of the recipe.
 */
data class Recipe(val id: Int, val title: String, val tags: List<String>)
