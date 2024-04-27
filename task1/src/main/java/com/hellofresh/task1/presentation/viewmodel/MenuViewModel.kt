package com.hellofresh.task1.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellofresh.task1.common.AppConstants
import com.hellofresh.task1.data.model.Recipe
import com.hellofresh.task1.domain.usecase.GetMenuUseCase
import com.hellofresh.task1.presentation.view.states.MenuUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This is a view model that provides access to recipes.
 * @param getMenuUseCase The use case that provides a list of recipes.
 */
@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getMenuUseCase: GetMenuUseCase,
    private val ioDispatcher: CoroutineDispatcher
) :
    ViewModel() {

    private val maxSelectedRecipesForSingleUser = 3
    var isFamilySubscription = false
    private val maxSelectedRecipesForFamily = 5

    val _menuUIState = MutableStateFlow(MenuUIState())
    val menuUIState = _menuUIState.asStateFlow()

    init {
        fetchMenu()
    }

    /**
     * This function fetches the menu.
     */
    fun fetchMenu() {
        viewModelScope.launch(ioDispatcher) {
            runCatching {
                val menu = getMenuUseCase.execute()
                menu?.subscription?.let { subscription ->
                    isFamilySubscription = subscription.isForFamily
                }
                _menuUIState.update {
                    it.copy(
                        menu = menu
                    )
                }
            }.onFailure {
                _menuUIState.update {
                    it.copy(
                        error = AppConstants.ERROR_FAILED_TO_FETCH_RECIPES
                    )
                }
            }
        }
    }


    /**
     * This function marks a recipe as selected.
     * @param recipeId The ID of the recipe to be selected.
     */
    fun markSingleRecipeAsSelected(recipeId: Int) {
        val maxSelectedRecipes =
            if (isFamilySubscription) maxSelectedRecipesForFamily else maxSelectedRecipesForSingleUser
        if (_menuUIState.value.selectedRecipeIds.size < maxSelectedRecipes) {
            val newSelectedRecipeIds = _menuUIState.value.selectedRecipeIds.toMutableSet().apply {
                add(recipeId)
            }
            _menuUIState.value = _menuUIState.value.copy(selectedRecipeIds = newSelectedRecipeIds)
        }
    }

    /**
     * This function marks multiple recipes as selected.
     * @param recipeIds The list of recipe IDs to be selected.
     */
    fun markMultipleRecipesAsSelected(recipeIds: List<Int>) {
        val remainingSlots =
            if (isFamilySubscription) maxSelectedRecipesForFamily - _menuUIState.value.selectedRecipeIds.size
            else maxSelectedRecipesForSingleUser - _menuUIState.value.selectedRecipeIds.size
        val recipesToAdd = recipeIds.take(remainingSlots)
        val newSelectedRecipeIds = _menuUIState.value.selectedRecipeIds.toMutableSet().apply {
            addAll(recipesToAdd)
        }
        _menuUIState.value = _menuUIState.value.copy(selectedRecipeIds = newSelectedRecipeIds)
    }

    /**
     * This function marks a recipe as unselected.
     * @param recipeId The ID of the recipe to unselect.
     */
    fun markSingleRecipeAsUnselected(recipeId: Int) {
        val newSelectedRecipeIds = _menuUIState.value.selectedRecipeIds.toMutableSet().apply {
            remove(recipeId)
        }
        _menuUIState.value = _menuUIState.value.copy(selectedRecipeIds = newSelectedRecipeIds)
    }

    /**
     * This function marks multiple recipes as unselected.
     * @param recipeIds The list of recipe IDs to unselect.
     */
    fun markMultipleRecipesAsUnselected(recipeIds: List<Int>) {
        val newSelectedRecipeIds = _menuUIState.value.selectedRecipeIds.toMutableSet().apply {
            removeAll(recipeIds.toSet())
        }
        _menuUIState.value = _menuUIState.value.copy(selectedRecipeIds = newSelectedRecipeIds)
    }

    /**
     * This function returns the count of selected recipes.
     * @return The count of selected recipes.
     */
    fun getSelectedRecipesCount(): Int {
        return _menuUIState.value.selectedRecipeIds.size
    }

    /**
     * This function returns the list of selected recipes.
     * @return The list of selected recipes.
     */
    fun getSelectedRecipes(): List<Recipe>? {
        return _menuUIState.value.menu?.recipes?.filter { it.id in _menuUIState.value.selectedRecipeIds }
    }

    /**
     * This function returns the list of recipes by tag.
     * @param tag The tag of the recipe.
     * @return The list of recipes by tag.
     */
    fun getRecipesByTag(tag: String): List<Recipe>? {
        return _menuUIState.value.menu?.recipes?.filter { it.tags.contains(tag) }
    }
}