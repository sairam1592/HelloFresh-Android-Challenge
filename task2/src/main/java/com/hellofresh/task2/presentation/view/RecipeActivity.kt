package com.hellofresh.task2.presentation.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.ExperimentalComposeUiApi
import com.hellofresh.task2.databinding.ActivityRecipeBinding
import com.hellofresh.task2.presentation.view.compose.RecipeScreen
import com.hellofresh.task2.presentation.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * This is an activity that displays a list of recipes.
 */
@AndroidEntryPoint
class RecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeBinding
    private val viewModel: RecipeViewModel by viewModels()

    @ExperimentalCoroutinesApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeScreen(recipeViewModel = viewModel)
        }
    }
}