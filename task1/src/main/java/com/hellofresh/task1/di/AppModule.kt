package com.hellofresh.task1.di

import com.hellofresh.task1.data.repository.MenuRepository
import com.hellofresh.task1.data.repository.MenuRepositoryImpl
import com.hellofresh.task1.domain.usecase.GetMenuUseCase
import com.hellofresh.task1.presentation.viewmodel.MenuViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/*
    * This is a module that provides dependencies for the app.
    * @param ioDispatcher The dispatcher that provides access to the IO thread.
    * @param recipeRepository The repository that provides access to recipes.
    * @param getRecipesUseCase The use case that provides a list of recipes.
    * @param menuViewModel The view model that provides access to recipes.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideRecipeRepository(): MenuRepository {
        return MenuRepositoryImpl()
    }

    @Provides
    fun provideGetRecipesUseCase(menuRepository: MenuRepository): GetMenuUseCase {
        return GetMenuUseCase(menuRepository)
    }

    @Provides
    fun provideMenuViewModel(
        getMenuUseCase: GetMenuUseCase,
        ioDispatcher: CoroutineDispatcher
    ): MenuViewModel {
        return MenuViewModel(getMenuUseCase, ioDispatcher)
    }
}