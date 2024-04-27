package com.hellofresh.task1.data.repository

import com.hellofresh.task1.data.model.Menu
import com.hellofresh.task1.domain.helper.MenuModelHelper.createDummyMenu
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MenuRepositoryImplTest {

    @Test
    fun `test getMenu`() {
        val expectedMenu = createDummyMenu()
        val menuRepository = MenuRepositoryImpl()

        val result = menuRepository.getMenu()

        assertEquals(expectedMenu, result)
    }

    @Test(expected = RuntimeException::class)
    fun `test getMenu when repository fails`() {
        val menuRepository = FailingMenuRepositoryImpl()

        menuRepository.getMenu()

        // Then
        // We expect an exception to be thrown when the repository fails
    }

    class FailingMenuRepositoryImpl : MenuRepositoryImpl() {
        override fun getMenu(): Menu {
            throw RuntimeException("Failed to retrieve menu")
        }
    }
}