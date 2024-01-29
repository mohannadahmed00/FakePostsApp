package com.giraffe.fakepostsapplication.domain.usecase

import com.giraffe.fakepostsapplication.domain.repository.Repository

class GetAllPostsUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.getAllPosts()
}