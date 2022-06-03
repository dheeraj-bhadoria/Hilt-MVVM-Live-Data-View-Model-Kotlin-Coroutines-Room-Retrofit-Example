package com.app.postdemo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.postdemo.models.Post
import com.app.postdemo.models.PostComment
import com.app.postdemo.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostCommentsViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    val postCommentsLiveData : LiveData<List<PostComment>>
        get() = repository.comments

    fun getPostComments(post_id: Int): LiveData<List<PostComment>>{
        viewModelScope.launch {
            repository.getPostComments(post_id)
        }
        return postCommentsLiveData
    }
}
