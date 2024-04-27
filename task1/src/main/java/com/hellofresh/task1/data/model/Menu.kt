package com.hellofresh.task1.data.model

/* This is a data class that represents a menu.
 * @param recipes The list of recipes.
 * @param subscription The subscription.
 */
data class Menu(
    val recipes: List<Recipe>?,
    val subscription: Subscription?
)
