package com.hellofresh.task2.di

import com.hellofresh.task2.common.AppConstants
import com.hellofresh.task2.data.datasource.remote.RecipeRemoteDataSource
import com.hellofresh.task2.data.datasource.remote.RecipeRemoteDataSourceImpl
import com.hellofresh.task2.data.remote.RecipeService
import com.hellofresh.task2.data.repository.RecipeRepository
import com.hellofresh.task2.data.repository.RecipeRepositoryImpl
import com.hellofresh.task2.presentation.viewmodel.RecipeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import domain.usecase.GetRecipesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * This is a module that provides dependencies for the app.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideRecipeService(retrofit: Retrofit): RecipeService {
        return retrofit.create(RecipeService::class.java)
    }

    @Provides
    @Singleton
    fun provideRecipeRemoteDataSource(recipeService: RecipeService): RecipeRemoteDataSource {
        return RecipeRemoteDataSourceImpl(recipeService)
    }

    @Provides
    fun provideRecipeRepository(recipeRemoteDataSourceImpl: RecipeRemoteDataSourceImpl): RecipeRepository {
        return RecipeRepositoryImpl(recipeRemoteDataSourceImpl)
    }

    @Provides
    fun provideGetRecipesUseCase(repository: RecipeRepository): GetRecipesUseCase {
        return GetRecipesUseCase(repository)
    }

    @Provides
    fun provideRecipeViewModel(
        getRecipesUseCase: GetRecipesUseCase,
        ioDispatcher: CoroutineDispatcher
    ): RecipeViewModel {
        return RecipeViewModel(getRecipesUseCase, ioDispatcher)
    }
}