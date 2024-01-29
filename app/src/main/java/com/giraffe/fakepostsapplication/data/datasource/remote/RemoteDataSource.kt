package com.giraffe.fakepostsapplication.data.datasource.remote

import com.giraffe.fakepostsapplication.data.datasource.remote.response.AllPostsResponse
import com.giraffe.fakepostsapplication.data.datasource.remote.response.PostDetailsResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getAllPosts(): Flow<AllPostsResponse>
    suspend fun getPostDetails(id: Int): Flow<PostDetailsResponse>
}