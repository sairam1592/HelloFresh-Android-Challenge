package com.hellofresh.task1.domain.usecase

import com.hellofresh.task1.data.model.Menu
import com.hellofresh.task1.data.repository.MenuRepository
import javax.inject.Inject

/**
 * This is a use case that provides a list of recipes.
 * @param menuRepository The repository that provides access to recipes.
 */
class GetMenuUseCase @Inject constructor(private val menuRepository: MenuRepository) {
    fun execute(): Menu? {
        return menuRepository.getMenu()
    }
}