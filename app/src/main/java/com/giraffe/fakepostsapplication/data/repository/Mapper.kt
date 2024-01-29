package com.giraffe.fakepostsapplication.data.repository

import com.giraffe.fakepostsapplication.data.datasource.remote.response.AllPostsResponse
import com.giraffe.fakepostsapplication.data.datasource.remote.response.PostDetailsResponse
import com.giraffe.fakepostsapplication.domain.entity.AllPostsEntity
import com.giraffe.fakepostsapplication.domain.entity.PostDetailsEntity

fun AllPostsResponse.toEntity(): AllPostsEntity {
    return AllPostsEntity(
        posts = this.map { it.toEntity() }
    )
}

fun PostDetailsResponse.toEntity(): PostDetailsEntity {
    return PostDetailsEntity(
        body = this.body,
        id = this.id,
        title = this.title
    )
}