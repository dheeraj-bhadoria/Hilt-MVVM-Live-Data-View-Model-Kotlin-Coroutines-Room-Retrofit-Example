package com.app.postdemo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.postdemo.models.FavoritesPost
import com.app.postdemo.models.Post
import com.app.postdemo.models.PostComment
import com.app.postdemo.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesPostViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {


    val favoritesPost : LiveData<List<FavoritesPost>>
        get() = repository.favoritesPost

    fun saveFavoritesPost(post: FavoritesPost){
        viewModelScope.launch {
            repository.saveFavoritesPost(post)
        }

    }


    fun getFavoritePostList(): LiveData<List<FavoritesPost>>{
        viewModelScope.launch {
            repository.getFavoritesPost()
        }
        return favoritesPost
    }

}
