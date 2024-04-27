package com.hellofresh.task1.domain.helper

import com.hellofresh.task1.data.model.Menu
import com.hellofresh.task1.data.model.Recipe
import com.hellofresh.task1.data.model.Subscription

/**
 * This is a helper class that provides a dummy list of recipes.
 */
object MenuModelHelper {
    fun createDummyMenu(): Menu {
        val dummyRecipes = listOf(
            Recipe(1, "Sphagetti", listOf("italian", "pasta", "creamy")),
            Recipe(2, "Briyani", listOf("indian", "spicy", "chicken")),
            Recipe(3, "Curry", listOf("indian", "spicy", "vege")),
            Recipe(4, "Salad", listOf("salad", "healthy", "vege")),
            Recipe(5, "Apple", listOf("fruit", "healthy", "sweet")),
            Recipe(6, "fries", listOf("fastfood", "crunchy", "oily")),
            Recipe(7, "Pizza", listOf("italian", "cheesy", "bread")),
            Recipe(8, "Tacos", listOf("mexican", "spicy", "meat")),
            Recipe(9, "Burger", listOf("fastfood", "juicy", "meat")),
            Recipe(10, "Pancakes", listOf("breakfast", "sweet", "healthy")),
            Recipe(11, "Sushi", listOf("japanese", "fish", "rice")),
            Recipe(12, "Soup", listOf("soup", "hot", "vege")),
            Recipe(13, "Sandwich", listOf("lunch", "bread", "healthy")),
            Recipe(14, "Cake", listOf("dessert", "sweet", "baked")),
            Recipe(15, "Smoothie", listOf("drink", "healthy", "fruit"))
        )

        val dummySubscription = Subscription(
            1,
            "Monday",
            true
        ) // we can modify isForFamily boolean to restrict the access to the recipes
        return Menu(dummyRecipes, dummySubscription)
    }
}