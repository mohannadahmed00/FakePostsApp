package com.giraffe.fakepostsapplication.presentation.allposts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giraffe.fakepostsapplication.domain.entity.AllPostsEntity
import com.giraffe.fakepostsapplication.domain.usecase.GetAllPostsUseCase
import com.giraffe.fakepostsapplication.utils.Resource
import com.giraffe.fakepostsapplication.utils.safeCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllPostsViewModel @Inject constructor(
    private val getAllPostsUseCase: GetAllPostsUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<Resource<AllPostsEntity>> =
        MutableStateFlow(Resource.Loading)
    val state: StateFlow<Resource<AllPostsEntity>> = _state.asStateFlow()
    init {
        getAllPosts()
    }
    private fun getAllPosts() {
        viewModelScope.launch {
            _state.emit(safeCall { getAllPostsUseCase() })
        }
    }
}