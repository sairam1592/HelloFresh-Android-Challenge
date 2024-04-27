package domain.usecase

import com.hellofresh.task2.data.model.Recipe
import com.hellofresh.task2.data.repository.RecipeRepository
import domain.usecase.model.DataResult
import javax.inject.Inject

/**
 * This is a use case that gets recipes.
 */
class GetRecipesUseCase @Inject constructor(private val repository: RecipeRepository) {
    suspend operator fun invoke(): DataResult<List<Recipe>> {
        return repository.getRecipes()
    }
}