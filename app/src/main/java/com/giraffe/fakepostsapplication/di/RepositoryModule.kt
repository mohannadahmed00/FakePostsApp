package com.giraffe.fakepostsapplication.di

import com.giraffe.fakepostsapplication.data.datasource.remote.RemoteDataSource
import com.giraffe.fakepostsapplication.data.repository.RepositoryImp
import com.giraffe.fakepostsapplication.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource): Repository {
        return RepositoryImp(remoteDataSource)
    }
}
