package com.hellofresh.task1.data.repository

import com.hellofresh.task1.data.model.Menu

/**
 * This is a repository interface that provides access to recipes.
 */
interface MenuRepository {
    fun getMenu(): Menu?
}