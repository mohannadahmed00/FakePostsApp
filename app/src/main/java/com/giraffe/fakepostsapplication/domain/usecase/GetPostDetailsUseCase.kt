package com.giraffe.fakepostsapplication.domain.usecase

import com.giraffe.fakepostsapplication.domain.repository.Repository

class GetPostDetailsUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(id:Int) = repository.getPostDetails(id)
}