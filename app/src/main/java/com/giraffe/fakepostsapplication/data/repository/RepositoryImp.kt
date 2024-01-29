package com.giraffe.fakepostsapplication.data.repository

import com.giraffe.fakepostsapplication.data.datasource.remote.RemoteDataSource
import com.giraffe.fakepostsapplication.domain.repository.Repository
import kotlinx.coroutines.flow.map

class RepositoryImp(
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override suspend fun getAllPosts() = remoteDataSource.getAllPosts().map {
        it.toEntity()
    }

    override suspend fun getPostDetails(id: Int) = remoteDataSource.getPostDetails(id).map {
        it.toEntity()
    }
}