package com.giraffe.fakepostsapplication.domain.repository

import com.giraffe.fakepostsapplication.domain.entity.AllPostsEntity
import com.giraffe.fakepostsapplication.domain.entity.PostDetailsEntity
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getAllPosts():Flow<AllPostsEntity>
    suspend fun getPostDetails(id:Int):Flow<PostDetailsEntity>
}