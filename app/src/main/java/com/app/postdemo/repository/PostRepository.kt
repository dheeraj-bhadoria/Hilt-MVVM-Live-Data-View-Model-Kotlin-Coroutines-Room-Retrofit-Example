package com.app.postdemo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.postdemo.db.PostDB
import com.app.postdemo.models.FavoritesPost
import com.app.postdemo.models.Post
import com.app.postdemo.models.PostComment
import com.app.postdemo.retrofit.PostAPI
import javax.inject.Inject

class PostRepository @Inject constructor(private val PostAPI: PostAPI, private val PostDB: PostDB) {

    private val _posts = MutableLiveData<List<Post>>()
    val postList: LiveData<List<Post>>
        get() = _posts

    private val _postComments = MutableLiveData<List<PostComment>>()
    val comments: LiveData<List<PostComment>>
        get() = _postComments

    private val _favoritesPost = MutableLiveData<List<FavoritesPost>>()
    val favoritesPost: LiveData<List<FavoritesPost>>
        get() = _favoritesPost

    suspend fun getProducts(){
        val result = PostAPI.getPost()
        if(result.isSuccessful && result.body() != null){
            PostDB.getPostDAO().addPosts(result.body()!!)
            _posts.postValue(result.body())
        }
    }

    suspend fun getPostComments(post_id: Int){
        val result = PostAPI.getPostComment(post_id)
        if(result.isSuccessful && result.body() != null){
            PostDB.getPostDAO().addPostComment(result.body()!!)
            _postComments.postValue(result.body())
        }
    }

    suspend fun saveFavoritesPost(post: FavoritesPost){
        PostDB.getPostDAO().addFavoritesPost(post)
    }

    suspend fun getFavoritesPost(){
        _favoritesPost.postValue(PostDB.getPostDAO().getFavoritesPost())
    }
}