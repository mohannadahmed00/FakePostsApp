package com.giraffe.fakepostsapplication.data.datasource.remote.response

data class PostDetailsResponse(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)