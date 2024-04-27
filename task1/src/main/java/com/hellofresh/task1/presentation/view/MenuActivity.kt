package com.hellofresh.task1.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hellofresh.task1.common.AppConstants.TAG_HEALTHY
import com.hellofresh.task1.databinding.ActivityMenuBinding
import com.hellofresh.task1.presentation.view.states.MenuUIState
import com.hellofresh.task1.presentation.viewmodel.MenuViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * This is an activity that displays a menu.
 */
@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private val viewModel: MenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            viewModel.menuUIState.collect { menu ->
                updateMenu(menu)
            }
        }

        // Play around with the following functions to see how the UI updates when the MenuUIState state changes
        markSingleRecipeAsSelected()
        markMultipleRecipesAsSelected()
        markSingleRecipeAsUnselected()
        markMultipleRecipesAsUnselected()

        // Request list of selected recipes, print @selectedRecipes to get selected recipes
        val selectedRecipes = viewModel.getSelectedRecipes()

        // Request list of recipes with a certain tag, print @recipesWithTag to get list of recipes with a tag of your choice
        val recipesWithTag = viewModel.getRecipesByTag(TAG_HEALTHY)
    }

    /**
     * This function updates the menu UI.
     * @param menuUIState The UI state that provides access to Menu.
     */
    private fun updateMenu(menuUIState: MenuUIState) {

        //Update UI with recipes list , print @recipes to get list of recipes
        val recipes = menuUIState.menu?.recipes

        // Update UI with selected Recipe IDs, print @selectedRecipeIds to get selected recipe ids when state changes
        val selectedRecipeIds = menuUIState.selectedRecipeIds

        // Update UI with selected Recipe IDs count, print @selectedRecipesCount to get selected recipe count when state changes
        val selectedRecipesCount = viewModel.getSelectedRecipesCount()
    }

    /**
     * This function marks a recipe as selected.
     */
    private fun markSingleRecipeAsSelected() {
        val recipeIdToSelect = 10
        viewModel.markSingleRecipeAsSelected(recipeIdToSelect)
    }

    /**
     * This function marks a recipe as unselected.
     */
    private fun markSingleRecipeAsUnselected() {
        val recipeIdToUnselect = 10
        viewModel.markSingleRecipeAsUnselected(recipeIdToUnselect)
    }

    /**
     * This function marks multiple recipes as selected.
     */
    private fun markMultipleRecipesAsSelected() {
        val recipeIdsToSelect = listOf(1, 2, 3, 4, 5)
        viewModel.markMultipleRecipesAsSelected(recipeIdsToSelect)
    }

    /**
     * This function marks multiple recipes as unselected.
     */
    private fun markMultipleRecipesAsUnselected() {
        val recipeIdsToUnselect = listOf(1, 2, 3, 4, 5)
        viewModel.markMultipleRecipesAsUnselected(recipeIdsToUnselect)
    }
}