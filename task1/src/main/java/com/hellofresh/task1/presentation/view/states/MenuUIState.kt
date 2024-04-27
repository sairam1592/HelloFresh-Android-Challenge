package com.hellofresh.task1.presentation.view.states

import com.hellofresh.task1.data.model.Menu

/**
 * This is a UI state that provides access to the menu.
 * @param menu The menu.
 * @param selectedRecipeIds The selected recipe IDs.
 * @param error The error message.
 */
data class MenuUIState(
    val menu: Menu? = null,
    val selectedRecipeIds: Set<Int> = emptySet(),
    val error: String? = null
)