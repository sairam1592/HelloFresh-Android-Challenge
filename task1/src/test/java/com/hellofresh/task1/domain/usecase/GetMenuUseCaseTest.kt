package com.hellofresh.task1.domain.usecase

import com.hellofresh.task1.data.model.Menu
import com.hellofresh.task1.data.repository.MenuRepository
import com.hellofresh.task1.domain.helper.MenuModelHelper
import org.junit.Assert.assertEquals
import org.junit.Test

class GetMenuUseCaseTest {

    @Test
    fun `test execute`() {
        val expectedMenu = MenuModelHelper.createDummyMenu()
        val menuRepository = FakeMenuRepository(expectedMenu)
        val getMenuUseCase = GetMenuUseCase(menuRepository)
        val result = getMenuUseCase.execute()
        assertEquals(expectedMenu, result)
    }

    class FakeMenuRepository(private val menu: Menu) : MenuRepository {
        override fun getMenu(): Menu {
            return menu
        }
    }
}