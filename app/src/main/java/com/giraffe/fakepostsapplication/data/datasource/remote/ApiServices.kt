package com.giraffe.fakepostsapplication.data.datasource.remote

import com.giraffe.fakepostsapplication.data.datasource.remote.response.AllPostsResponse
import com.giraffe.fakepostsapplication.data.datasource.remote.response.PostDetailsResponse
import com.giraffe.fakepostsapplication.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
    @GET(Constants.EndPoints.POSTS)
    suspend fun getAllPosts(): AllPostsResponse

    @GET(Constants.EndPoints.POST_DETAILS)
    suspend fun getPostDetails(@Path("id") id: Int): PostDetailsResponse
}