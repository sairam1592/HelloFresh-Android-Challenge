package com.hellofresh.task1.data.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class MenuTest {

    @Test
    fun `test menu model`() {
        val recipe1 = Recipe(1, "Spaghetti Carbonara", listOf("italian", "pasta", "creamy"))
        val recipe2 = Recipe(2, "Chicken Curry", listOf("indian", "spicy", "chicken"))
        val recipes = listOf(recipe1, recipe2)
        val subscription = Subscription(1, "Monday", true)

        val menu = Menu(recipes, subscription)

        assertEquals(recipes, menu.recipes)
        assertEquals(subscription, menu.subscription)
    }
}