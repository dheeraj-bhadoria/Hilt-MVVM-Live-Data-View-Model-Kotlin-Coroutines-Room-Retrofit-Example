package com.app.postdemo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.postdemo.Application.MyApplication
import com.app.postdemo.db.PostDB
import com.app.postdemo.models.FavoritesPost
import com.app.postdemo.models.Post
import com.app.postdemo.models.PostComment
import com.app.postdemo.retrofit.PostAPI
import com.app.postdemo.utils.AppUtils
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

    /**
     *  Get the list of posts from API or database
     */
    suspend fun getPost(){
        if(AppUtils.isInterConnectionIsAvailable(MyApplication.getAppContext())){
            val result = PostAPI.getPost()
            if(result.isSuccessful && result.body() != null){
                PostDB.getPostDAO().addPosts(result.body()!!)
                _posts.postValue(result.body())
            }
        }else{
            if(PostDB.getPostDAO().getPosts()!=null){
                _posts.postValue(PostDB.getPostDAO().getPosts())
            }
        }
    }

    /**
     * Get the list of Post comments form API or database
     */
    suspend fun getPostComments(post_id: Int){
        if(AppUtils.isInterConnectionIsAvailable(MyApplication.getAppContext())){
            val result = PostAPI.getPostComment(post_id)
            if(result.isSuccessful && result.body() != null){
                PostDB.getPostDAO().addPostComment(result.body()!!)
                _postComments.postValue(result.body())
            }
        }else{
            if(PostDB.getPostDAO().getPostComment()!=null){
                _postComments.postValue(PostDB.getPostDAO().getPostComment())
            }
        }
    }

    /**
     * Save favorites post to database
     */
    suspend fun saveFavoritesPost(post: FavoritesPost){
        PostDB.getPostDAO().addFavoritesPost(post)
    }

    /**
     * Get favorites post form database
     */
    suspend fun getFavoritesPost(){
        _favoritesPost.postValue(PostDB.getPostDAO().getFavoritesPost())
    }
}