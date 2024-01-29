package com.giraffe.fakepostsapplication.data.datasource.remote

import kotlinx.coroutines.flow.flow

class RemoteDataSourceImp(
    private val apiServices: ApiServices
) : RemoteDataSource {
    override suspend fun getAllPosts() = flow { emit(apiServices.getAllPosts() ) }
    override suspend fun getPostDetails(id: Int) = flow { emit(apiServices.getPostDetails(id)) }

}