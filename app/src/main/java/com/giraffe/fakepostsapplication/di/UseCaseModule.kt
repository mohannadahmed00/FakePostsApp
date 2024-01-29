package com.giraffe.fakepostsapplication.di

import com.giraffe.fakepostsapplication.domain.repository.Repository
import com.giraffe.fakepostsapplication.domain.usecase.GetAllPostsUseCase
import com.giraffe.fakepostsapplication.domain.usecase.GetPostDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetAllPostsUseCase(repository: Repository): GetAllPostsUseCase {
        return GetAllPostsUseCase(repository)
    }

    @Provides
    fun provideGetPostDetailsUseCase(repository: Repository): GetPostDetailsUseCase {
        return GetPostDetailsUseCase(repository)
    }
}
