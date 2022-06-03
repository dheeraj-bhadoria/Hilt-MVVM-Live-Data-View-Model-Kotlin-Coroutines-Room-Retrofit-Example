package com.app.postdemo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.postdemo.models.Post
import com.app.postdemo.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    val postListLiveData : LiveData<List<Post>>
    get() = repository.postList

    init {
            viewModelScope.launch {
                repository.getProducts()
        }
    }



}
