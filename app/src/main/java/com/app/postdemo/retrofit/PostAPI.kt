package com.app.postdemo.retrofit

import com.app.postdemo.models.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import android.R.string.no
import com.app.postdemo.models.PostComment


interface PostAPI {

    @GET("posts")
    suspend fun getPost() : Response<List<Post>>

    @GET("posts/{post_id}/comments")
    suspend fun getPostComment(@Path("post_id") post_id: Int) : Response<List<PostComment>>

}