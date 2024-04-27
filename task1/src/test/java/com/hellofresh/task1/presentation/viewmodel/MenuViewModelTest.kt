import com.hellofresh.task1.common.AppConstants
import com.hellofresh.task1.common.AppConstants.TAG_FRUIT
import com.hellofresh.task1.data.model.Menu
import com.hellofresh.task1.data.model.Recipe
import com.hellofresh.task1.domain.helper.MenuModelHelper
import com.hellofresh.task1.domain.usecase.GetMenuUseCase
import com.hellofresh.task1.presentation.view.states.MenuUIState
import com.hellofresh.task1.presentation.viewmodel.MenuViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MenuViewModelTest {

    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private lateinit var getMenuUseCase: GetMenuUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        getMenuUseCase = mockk(relaxed = true)
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun `test fetchMenu success`() {

        val viewModel = MenuViewModel(getMenuUseCase, dispatcher)
        val menu = MenuModelHelper.createDummyMenu()

        coEvery { getMenuUseCase.execute() } returns menu

        viewModel.fetchMenu()
        assert(viewModel.menuUIState.value.menu == menu)
        assert(viewModel.menuUIState.value.error == null)
    }

    @Test
    fun `test fetchMenu failure`() {

        val viewModel = MenuViewModel(getMenuUseCase, dispatcher)
        val errorMessage = AppConstants.ERROR_FAILED_TO_FETCH_RECIPES

        coEvery { getMenuUseCase.execute() } throws Exception(errorMessage)

        viewModel.fetchMenu()
        assertEquals(
            MenuUIState(menu = Menu(null, null), error = errorMessage),
            viewModel.menuUIState.value
        )
    }


    @Test
    fun `test markSingleRecipeAsSelected for family subscription below max`() {

        val viewModel = MenuViewModel(getMenuUseCase, dispatcher)
        viewModel.isFamilySubscription = true
        viewModel._menuUIState.value = MenuUIState(selectedRecipeIds = setOf(1, 2, 3))
        val recipeId = 4

        viewModel.markSingleRecipeAsSelected(recipeId)

        assertEquals(setOf(1, 2, 3, 4), viewModel.menuUIState.value.selectedRecipeIds)
    }

    @Test
    fun `test markSingleRecipeAsSelected for non-family subscription below max`() {

        val viewModel = MenuViewModel(getMenuUseCase, dispatcher)
        viewModel.isFamilySubscription = false
        viewModel._menuUIState.value = MenuUIState(selectedRecipeIds = setOf(1, 2))
        val recipeId = 3

        viewModel.markSingleRecipeAsSelected(recipeId)

        assertEquals(setOf(1, 2, 3), viewModel.menuUIState.value.selectedRecipeIds)
    }

    @Test
    fun `test markSingleRecipeAsSelected max reached`() {

        val viewModel = MenuViewModel(getMenuUseCase, dispatcher)
        viewModel.isFamilySubscription = false
        viewModel._menuUIState.value = MenuUIState(selectedRecipeIds = setOf(1, 2, 3))
        val recipeId = 4

        viewModel.markSingleRecipeAsSelected(recipeId)

        assertEquals(setOf(1, 2, 3), viewModel.menuUIState.value.selectedRecipeIds)
    }

    @Test
    fun `test markMultipleRecipesAsSelected for family subscription below max`() {
        val viewModel = MenuViewModel(getMenuUseCase, dispatcher)
        viewModel.isFamilySubscription = true
        viewModel._menuUIState.value = MenuUIState(selectedRecipeIds = setOf(1, 2))
        val recipeIds = listOf(3, 4, 5)

        viewModel.markMultipleRecipesAsSelected(recipeIds)

        assertEquals(setOf(1, 2, 3, 4, 5), viewModel.menuUIState.value.selectedRecipeIds)
    }

    @Test
    fun `test markMultipleRecipesAsSelected for non-family subscription below max`() {
        val viewModel = MenuViewModel(getMenuUseCase, dispatcher)
        viewModel.isFamilySubscription = false
        viewModel._menuUIState.value = MenuUIState(selectedRecipeIds = setOf(1, 2, 3))
        val recipeIds = listOf(4, 5)

        viewModel.markMultipleRecipesAsSelected(recipeIds)

        assertEquals(setOf(1, 2, 3), viewModel.menuUIState.value.selectedRecipeIds)
    }

    @Test
    fun `test markMultipleRecipesAsSelected for family subscription max reached`() {
        val viewModel = MenuViewModel(getMenuUseCase, dispatcher)
        viewModel.isFamilySubscription = true
        viewModel._menuUIState.value = MenuUIState(selectedRecipeIds = setOf(1, 2, 3, 4, 5))
        val recipeIds = listOf(6, 7)

        viewModel.markMultipleRecipesAsSelected(recipeIds)

        assertEquals(setOf(1, 2, 3, 4, 5), viewModel.menuUIState.value.selectedRecipeIds)
    }

    @Test
    fun `test markMultipleRecipesAsSelected for non-family subscription max reached`() {
        val viewModel = MenuViewModel(getMenuUseCase, dispatcher)
        viewModel.isFamilySubscription = false
        viewModel._menuUIState.value = MenuUIState(selectedRecipeIds = setOf(1, 2, 3))
        val recipeIds = listOf(4, 5, 6)

        viewModel.markMultipleRecipesAsSelected(recipeIds)

        assertEquals(setOf(1, 2, 3), viewModel.menuUIState.value.selectedRecipeIds)
    }


    @Test
    fun `test markSingleRecipeAsUnselected`() {
        val viewModel = MenuViewModel(getMenuUseCase, dispatcher)
        val recipeIdToRemove = 1
        val initialState = MenuUIState(selectedRecipeIds = setOf(1, 2, 3))

        every { getMenuUseCase.execute() } returns MenuModelHelper.createDummyMenu()
        viewModel.fetchMenu()

        viewModel._menuUIState.value = initialState

        viewModel.markSingleRecipeAsUnselected(recipeIdToRemove)

        val expectedState =
            initialState.copy(selectedRecipeIds = initialState.selectedRecipeIds - recipeIdToRemove)
        assert(viewModel.menuUIState.value == expectedState)
    }

    @Test
    fun `test markMultipleRecipesAsUnselected`() {
        val viewModel = MenuViewModel(getMenuUseCase, dispatcher)
        val initialState =
            MenuUIState(selectedRecipeIds = setOf(1, 2, 3, 4, 5)) // Initial selected recipes
        viewModel._menuUIState.value = initialState

        viewModel.markMultipleRecipesAsUnselected(listOf(2, 4)) // Unselect recipes 2 and 4

        val expectedState = initialState.copy(selectedRecipeIds = setOf(1, 3, 5))
        assertEquals(expectedState, viewModel.menuUIState.value)
    }

    @Test
    fun `test getSelectedRecipesCount`() {
        val viewModel = MenuViewModel(getMenuUseCase, dispatcher)
        val initialState =
            MenuUIState(selectedRecipeIds = setOf(1, 2, 3, 4, 5)) // Initial selected recipes
        viewModel._menuUIState.value = initialState

        val selectedRecipesCount = viewModel.getSelectedRecipesCount()

        assertEquals(5, selectedRecipesCount)
    }


    @Test
    fun `test getSelectedRecipes`() {
        val viewModel = MenuViewModel(getMenuUseCase, dispatcher)
        val selectedRecipeIds = setOf(1, 3, 5)

        val menu = MenuModelHelper.createDummyMenu()
        val initialState = MenuUIState(selectedRecipeIds = selectedRecipeIds, menu = menu)
        viewModel._menuUIState.value = initialState

        val selectedRecipes = viewModel.getSelectedRecipes()

        val expectedSelectedRecipes = listOf(
            Recipe(1, "Sphagetti", listOf("italian", "pasta", "creamy")),
            Recipe(3, "Curry", listOf("indian", "spicy", "vege")),
            Recipe(5, "Apple", listOf("fruit", "healthy", "sweet")),
        )

        assertEquals(expectedSelectedRecipes, selectedRecipes)
    }

    @Test
    fun `test getRecipesByTag`() {
        val viewModel = MenuViewModel(getMenuUseCase, dispatcher)
        val tag = TAG_FRUIT

        val menu = MenuModelHelper.createDummyMenu()
        val initialState = MenuUIState(menu = menu)
        viewModel._menuUIState.value = initialState

        val recipesWithTag = viewModel.getRecipesByTag(tag)

        val expectedRecipesWithTag = listOf(
            Recipe(5, "Apple", listOf("fruit", "healthy", "sweet")),
            Recipe(15, "Smoothie", listOf("drink", "healthy", "fruit")),
        )

        assertEquals(expectedRecipesWithTag, recipesWithTag)
    }
}