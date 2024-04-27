package com.hellofresh.task1.data.repository

import com.hellofresh.task1.data.model.Menu
import com.hellofresh.task1.domain.helper.MenuModelHelper
import javax.inject.Inject

/**
 * This is a repository interface that provides access to recipes.
 */
open class MenuRepositoryImpl @Inject constructor() : MenuRepository {
    override fun getMenu(): Menu? {
        return MenuModelHelper.createDummyMenu()
    }
}