package com.giraffe.fakepostsapplication.presentation.postdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giraffe.fakepostsapplication.domain.entity.AllPostsEntity
import com.giraffe.fakepostsapplication.domain.entity.PostDetailsEntity
import com.giraffe.fakepostsapplication.domain.usecase.GetPostDetailsUseCase
import com.giraffe.fakepostsapplication.utils.Resource
import com.giraffe.fakepostsapplication.utils.safeCall
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val getPostDetailsUseCase: GetPostDetailsUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<Resource<PostDetailsEntity>> =
        MutableStateFlow(Resource.Loading)
    val state: StateFlow<Resource<PostDetailsEntity>> = _state.asStateFlow()
    fun getPostDetails(postId: Int) {
        viewModelScope.launch {
            _state.emit(safeCall { getPostDetailsUseCase(postId) })
        }
    }
}