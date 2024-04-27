package com.hellofresh.task1.data.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class RecipeTest {

    @Test
    fun `test recipe model`() {
        val tags = listOf("italian", "pasta", "creamy")

        val recipe = Recipe(1, "Spaghetti Carbonara", tags)

        assertEquals(1, recipe.id)
        assertEquals("Spaghetti Carbonara", recipe.title)
        assertEquals(tags, recipe.tags)
    }
}